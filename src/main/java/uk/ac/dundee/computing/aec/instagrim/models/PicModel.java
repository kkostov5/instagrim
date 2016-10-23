package uk.ac.dundee.computing.aec.instagrim.models;

/*
 * Expects a cassandra columnfamily defined as
 * use keyspace2;
 CREATE TABLE Tweets (
 user varchar,
 interaction_time timeuuid,
 tweet varchar,
 PRIMARY KEY (user,interaction_time)
 ) WITH CLUSTERING ORDER BY (interaction_time DESC);
 * To manually generate a UUID use:
 * http://www.famkruithof.net/uuid/uuidgen
 */
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.Bytes;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import static org.imgscalr.Scalr.*;
import org.imgscalr.Scalr.Method;

import uk.ac.dundee.computing.aec.instagrim.lib.*;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.Profile;
//import uk.ac.dundee.computing.aec.stores.TweetStore;

public class PicModel {

    Cluster cluster;

    public void PicModel() {

    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public void insertPic(byte[] b, String type, String name, String user, Boolean profile) {
        try {
            Convertors convertor = new Convertors();

            String types[] = Convertors.SplitFiletype(type);
            ByteBuffer buffer = ByteBuffer.wrap(b);
            int length = b.length;
            java.util.UUID picid = convertor.getTimeUUID();
            //The following is a quick and dirty way of doing this, will fill the disk quickly !
            Boolean success = (new File("/var/tmp/instagrim/")).mkdirs();
            FileOutputStream output = new FileOutputStream(new File("/var/tmp/instagrim/" + picid));
            System.out.println("Saving image ");
            output.write(b);
            byte[] thumbb = picresize(picid.toString(), types[1]);
            int thumblength = thumbb.length;
            ByteBuffer thumbbuf = ByteBuffer.wrap(thumbb);
            byte[] processedb = picdecolour(picid.toString(), types[1]);
            ByteBuffer processedbuf = ByteBuffer.wrap(processedb);
            int processedlength = processedb.length;
            Session session = cluster.connect("instagrim");
            if (profile == true) {
                PreparedStatement ps = session.prepare("update userprofiles set picid=? where login=?");
                BoundStatement boundStatement = new BoundStatement(ps);
                session.execute( // this is where the query is executed
                        boundStatement.bind( // here you are binding the 'boundStatement'
                                picid, user));
            }

            PreparedStatement psInsertPic = session.prepare("insert into pics ( picid, image,thumb,processed, user, interaction_time,imagelength,thumblength,processedlength,type,name) values(?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement psInsertPicToUser = session.prepare("insert into userpiclist ( picid, user, pic_added) values(?,?,?)");
            BoundStatement bsInsertPic = new BoundStatement(psInsertPic);
            BoundStatement bsInsertPicToUser = new BoundStatement(psInsertPicToUser);

            Date DateAdded = new Date();
            session.execute(bsInsertPic.bind(picid, buffer, thumbbuf, processedbuf, user, DateAdded, length, thumblength, processedlength, type, name));
            session.execute(bsInsertPicToUser.bind(picid, user, DateAdded));
            session.close();

        } catch (IOException ex) {
            System.out.println("Error --> " + ex);
        }
    }

    public byte[] picresize(String picid, String type) {
        try {
            BufferedImage BI = ImageIO.read(new File("/var/tmp/instagrim/" + picid));
            BufferedImage thumbnail = createThumbnail(BI);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(thumbnail, type, baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException et) {

        }
        return null;
    }

    public byte[] picdecolour(String picid, String type) {
        try {

            BufferedImage BI = ImageIO.read(new File("/var/tmp/instagrim/" + picid));
            BufferedImage processed = createProcessed(BI);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(processed, type, baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException et) {

        }
        return null;
    }

    public static BufferedImage createThumbnail(BufferedImage img) {
        img = resize(img, Method.SPEED, 250, OP_ANTIALIAS, OP_GRAYSCALE);
        // Let's add a little border before we return result.
        return pad(img, 2);
    }

    public static BufferedImage createProcessed(BufferedImage img) {
        int Width = img.getWidth() - 1;
        img = resize(img, Method.SPEED, Width, OP_ANTIALIAS, OP_GRAYSCALE);
        return pad(img, 4);
    }

    public java.util.LinkedList<Pic> getPicsForUser(String User) {
        java.util.LinkedList<Pic> Pics = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select picid from userpiclist where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        User));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                Pic pic = new Pic();
                java.util.UUID UUID = row.getUUID("picid");
                System.out.println("UUID" + UUID.toString());
                pic.setUUID(UUID);
                Pics.add(pic);

            }
            session.close();
        }
        return Pics;
    }

    public boolean isUserPicture(String username, java.util.UUID picid) {

        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select picid from userpiclist where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        session.close();
        if (rs.isExhausted()) {
            System.out.println("No Validation returned");
            return false;
        } else {
            for (Row row : rs) {

                java.util.UUID picture = row.getUUID("picid");
                if (picture.compareTo(picid) == 0) {
                    return true;
                }
            }

        }
        return false;
    }

    public void deletePicture(String username, java.util.UUID picid, boolean profile) {

        System.out.println("Starting deletion");
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("Delete From Pics where picid=?");
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        picid));
        System.out.println("Deleted picture");
        PreparedStatement ps1 = session.prepare("Select user,pic_added from userpiclist where picid=? ALLOW FIlTERING");
        BoundStatement boundStatement1 = new BoundStatement(ps1);
        ResultSet rs = null;
        rs = session.execute( // this is where the query is executed
                boundStatement1.bind( // here you are binding the 'boundStatement'
                        picid));
        System.out.println("Selected from profile");
        if (rs.isExhausted()) {
            System.out.println("No Deletion returned");
        } else {
            for (Row row : rs) {

                String name = row.getString("user");
                Date time = row.getTimestamp("pic_added");
                System.out.println(name + time);
                System.out.println(username);

                if (name.equals(username)) {
                    System.out.println(name + time);
                    PreparedStatement ps4 = session.prepare("Delete From userpiclist where pic_added=? and user=?");
                    BoundStatement boundStatement4 = new BoundStatement(ps4);
                    session.execute( // this is where the query is executed
                            boundStatement4.bind( // here you are binding the 'boundStatement'
                                    time, username));
                    System.out.println("Deleted from profile");
                }

            }

        }
        PreparedStatement ps5 = session.prepare("Delete from piccomments where picid=?");
        BoundStatement boundStatement5 = new BoundStatement(ps5);
        session.execute( // this is where the query is executed
                boundStatement5.bind( // here you are binding the 'boundStatement'
                        picid));
        System.out.println("Deleted comments");

        if (profile == true) {
            String remove = null;
            PreparedStatement ps3 = session.prepare("update userprofiles set picid=? where login=?");
            BoundStatement boundStatement3 = new BoundStatement(ps3);
            session.execute( // this is where the query is executed
                    boundStatement3.bind( // here you are binding the 'boundStatement'
                            remove, username));
            System.out.println("Updated profile");
        }
        session.close();
    }

    public Pic getPic(int image_type, java.util.UUID picid) {
        Session session = cluster.connect("instagrim");
        ByteBuffer bImage = null;
        String type = null;
        int length = 0;
        try {
            Convertors convertor = new Convertors();
            ResultSet rs = null;
            PreparedStatement ps = null;

            if (image_type == Convertors.DISPLAY_IMAGE) {

                ps = session.prepare("select image,imagelength,type from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_THUMB) {
                ps = session.prepare("select thumb,imagelength,thumblength,type from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                ps = session.prepare("select processed,processedlength,type from pics where picid =?");
            }
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
                    boundStatement.bind( // here you are binding the 'boundStatement'
                            picid));

            if (rs.isExhausted()) {
                System.out.println("No Images returned");
                return null;
            } else {
                for (Row row : rs) {
                    if (image_type == Convertors.DISPLAY_IMAGE) {
                        bImage = row.getBytes("image");
                        length = row.getInt("imagelength");
                    } else if (image_type == Convertors.DISPLAY_THUMB) {
                        bImage = row.getBytes("thumb");
                        length = row.getInt("thumblength");

                    } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                        bImage = row.getBytes("processed");
                        length = row.getInt("processedlength");
                    }

                    type = row.getString("type");

                }
            }
        } catch (Exception et) {
            System.out.println("Can't get Pic" + et);
            return null;
        }
        session.close();
        Pic p = new Pic();
        p.setPic(bImage, length, type);

        return p;

    }

    public java.util.List<Row> getComments(java.util.UUID picid) {
        Session session = cluster.connect("instagrim");

        System.out.println(picid);
        java.util.List<Row> results = null;
        try {

            ResultSet rs;
            PreparedStatement ps = null;
            ps = session.prepare("select user,comment from piccomments where picid =? ALLOW FILTERING");
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
                    boundStatement.bind( // here you are binding the 'boundStatement'
                            picid));
            session.close();

            if (rs.isExhausted()) {
                System.out.println("No Image returned");
                return null;
            } else {
                /*int i = 0;
                
                for (Row row : rs) {
                 comment[i] = row.getString("user");
                 i++;*/
                results = rs.all();
                //}
                /*
                java.util.List<Row> results = rs.all();
                Iterator numb = results.iterator();
                while(numb.hasNext()) {
                    Row row = (Row) numb.next();
                    System.out.println(row.getString("user"));
                    get[i]=row.getString("user");
                    //get[i][1] = row.getString("comment");
                    //System.out.println(row.getString("comment"));
                    i++;
                }
                return get;*/

            }
        } catch (Exception et) {
            System.out.println("Can't get Comments " + et);// goooooooes herrreeeeee WHHWHWHWHYWYWYWYYWYWYYWYWYWYWYYW
            return null;
        }
        return results;
        //session.close();

        //return null;
    }

    public void setComment(java.util.UUID picid, String username, String comment) {
        Session session = cluster.connect("instagrim");

        PreparedStatement ps = null;
        Date DateAdded = new Date();
        ps = session.prepare("Insert into piccomments (user,comment,picid,pic_added) values(?,?,?,?)");
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username, comment, picid, DateAdded));
        session.close();

    }
}
