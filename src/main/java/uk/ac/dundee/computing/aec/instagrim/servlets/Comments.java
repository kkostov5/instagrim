/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.stores.*;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author Krasi
 */
@WebServlet(name = "SingleImage", urlPatterns = {"/Comments","/Comments/*"})@MultipartConfig
public class Comments extends HttpServlet {
    private Pic p;
    private Cluster cluster;
 public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

 /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session=request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        if(lg==null)
        {
            response.sendRedirect("/Instagrim");
        }
            else
        {
                String args[] = null;
        args = Convertors.SplitRequestPath(request);
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
        String Image = args[2];
        System.out.println(Image);
        System.out.println(Image);
        System.out.println(Image);
        boolean isUserPicture = tm.isUserPicture(lg.getUsername(),java.util.UUID.fromString(Image));
        p = tm.getPic(2,java.util.UUID.fromString(Image));
        p.setUUID(java.util.UUID.fromString(Image));
        java.util.List<Row> comments = tm.getComments(java.util.UUID.fromString(Image));
        RequestDispatcher rd = request.getRequestDispatcher("/SingleImage.jsp");
        request.setAttribute("isUserPicture",isUserPicture);
        request.setAttribute("Picture", p);
        request.setAttribute("Comments", comments);
        rd.forward(request, response);
                }
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        /*String delete = request.getParameter("delete");
        PicModel tm = new PicModel();
        HttpSession session=request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        tm.setCluster(cluster);
        if(delete.equals("Yes"))
        {
            Profile prof = (Profile) session.getAttribute("Profile");
            if((p.getSUUID()).equals(prof.getPic()))
            {
                tm.deletePicture(delete, java.util.UUID.fromString(p.getSUUID()), true);
            }
            else
            {
                tm.deletePicture(delete, java.util.UUID.fromString(p.getSUUID()), false);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
        else{*/
        PicModel tm = new PicModel();
        HttpSession session=request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        tm.setCluster(cluster);
        tm.setComment(java.util.UUID.fromString(p.getSUUID()), lg.getUsername(), request.getParameter("comment"));
        //RequestDispatcher rd = request.getRequestDispatcher("/Home");
        //rd.forward(request, response);
        
        response.sendRedirect("/Instagrim/Comments/"+p.getSUUID());
      //  }*/
        
    }

}
