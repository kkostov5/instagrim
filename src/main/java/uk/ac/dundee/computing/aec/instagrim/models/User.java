/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.*;

/**
 *
 * @author Administrator
 */

public class User {
    Cluster cluster;
    
    public User(){
        
    }
    
    public boolean RegisterUser(String firstname, String lastname, String email, String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login,password,first_name,last_name,email) Values(?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,firstname,lastname,email));
        //We are assuming this always works.  Also a transaction would be good here !
        session.close();
        return true;
    }
    
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
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
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
            
        }
    return false;  
    }
    
    public Set<String> getFollowing(String username){
        
        Session session = cluster.connect("instagrim");
        Set<String> names = null;
        PreparedStatement ps = session.prepare("select following from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        session.close();
        if (rs.isExhausted()) {
            System.out.println("No Validation returned");
            return null;
        } else {
            for (Row row : rs) {
               names = row.getSet("following", String.class);
               System.out.println(names);
                
            }
            return names;
        }
    
    }
    
    public void addFollowing(String username,String followee){
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("UPDATE userprofiles SET following = following + ? WHERE login = ?");
        BoundStatement boundStatement = new BoundStatement(ps);
        Set<String> toADD = new HashSet<>();
        boolean add;
        add = toADD.add(followee);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        toADD,username));
        session.close();
    
    }
    
    public void deleteFollowing(String username,String followee){
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("UPDATE userprofiles SET following = following - ? WHERE login = ?");
        Set<String> toDelete = new HashSet<>();
        boolean add;
        add = toDelete.add(followee);
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        toDelete,username));
        session.close();
    
    }
    
    public boolean IsExistingUser(String username){
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select login from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Validation returned");
            return false;
        } else {
            String test=null;
            for (Row row : rs) {
                
                    test = row.getString("login");
                    
            }
            session.close();
            if(test==null)return false;
             else  return true;  
        }
        
    }
    
    public boolean EditProfile(String login,String firstname, String lastname, String email){
       
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("update userprofiles set first_name=?,last_name=?,email=? where login=?");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        firstname,lastname,email,login));
        //We are assuming this always works.  Also a transaction would be good here !
        session.close();
        return true;
    }
    public String getFirstname(String username){
      /*  AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }*/
        String name =null;
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select first_name from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No firstname returned");
            return null;
        } else {
            for (Row row : rs) {
                 name = row.getString("first_name");
            }
            session.close();
            return name;
        }
    }
    public String getLastname(String username){
      /*  AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }*/
        String name =null;
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select last_name from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No lastname returned");
            return null;
        } else {
            for (Row row : rs) {
                 name = row.getString("last_name");
            }
            session.close();
            return name;
        }
    }
    public String getEmail(String username){
      /*  AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }*/
        String email =null;
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select email from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No email returned");
            return null;
        } else {
            for (Row row : rs) {
                 email = row.getString("email");
            }
            session.close();
            return email;
        }
    }
    
    
   
    public java.util.UUID getProfilePic(String username) {
        Session session = cluster.connect("instagrim");
        ByteBuffer bImage = null;
        java.util.UUID picid = null;
        String type = null;
        int length = 0;
        try {
            Convertors convertor = new Convertors();
            ResultSet rs = null;
            PreparedStatement ps = null;
                ps = session.prepare("select picid from userprofiles where login =?");
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
                    boundStatement.bind( // here you are binding the 'boundStatement'
                            username));

            if (rs.isExhausted()) {
                System.out.println("No Profilepic returned");
                return null;
            } else {
                for (Row row : rs) {
                    
                        picid = row.getUUID("picid");
                        System.out.println("get Pic" + picid);
                }
            }
        } catch (Exception et) {
            System.out.println("Can't get Pic" + et);
            return null;
        }
        session.close();

        return picid;

    }
    
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}
