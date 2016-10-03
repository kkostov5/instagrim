/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.Profile;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author Krasi
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile","/Profile/*"})
public class Account extends HttpServlet {

     Cluster cluster=null;


    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    /*    HttpSession session=request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        String username= lg.getUsername();
        //String password=request.getParameter("password");
        
        User us=new User();
        
        us.setCluster(cluster);
        
       // boolean isValid=us.IsValidUser(username, password);
        
        System.out.println("Session in servlet "+session);
        //if (isValid){
            Profile profile = new Profile();
            //LoggedIn lg= new LoggedIn();
            profile.setFirstname(us.getFirstname(username));
            session.setAttribute("Profile", profile);
            System.out.println("Session in servlet "+session);
            RequestDispatcher rd=request.getRequestDispatcher("UserProfile.jsp");
            rd.forward(request,response);
            
       // }else{
          //  response.sendRedirect("/Instagrim/index.jsp");
       // }
        */
            //RequestDispatcher rd=request.getRequestDispatcher("UserProfile.jsp");
            //rd.forward(request,response);
    //response.sendRedirect("/Instagrim/UserProfile.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
