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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Profile;

/**
 *
 * @author Krasi
 */
@WebServlet(urlPatterns = {
    "/Profile",
    "/Profile/*",
    "/EditProfile"
})
@MultipartConfig
public class UserProfile extends HttpServlet {
     Cluster cluster=null;


    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        HttpSession session=request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        if(lg==null)
        {
            response.sendRedirect("/Instagrim");
        }
        else
        {
        String args[] = Convertors.SplitRequestPath(request);
            if(args.length==4)//CHECKSSSSSSS SO YOU DONT EDIT OTHERS PROFILES
        {
            if(args[2].equals(lg.getUsername()))
            {
            RequestDispatcher rd=request.getRequestDispatcher("/EditProfile.jsp");
            rd.forward(request,response); 
            }
            else
            {
                response.sendRedirect("/Instagrim");
            }
           
        }
            else if(args.length>4)
            {
                response.sendRedirect("/Instagrim");
            }
        else
        {
            System.out.println(args[2]);
            System.out.println(lg.getUsername());
            String account = (String) request.getAttribute("account");
            if(account!=null){
                User us = new User();
                us.setCluster(cluster);
                request.setAttribute("username",account);
                request.setAttribute("firstname",us.getFirstname(account));
                request.setAttribute("lastname",us.getLastname(account));
                request.setAttribute("email",us.getEmail(account));
                request.setAttribute("pic",us.getProfilePic(account));
            }
            else
            {
                Profile prof = (Profile) session.getAttribute("Profile");
                request.setAttribute("username",prof.getUsername());
                request.setAttribute("firstname",prof.getFirstname());
                request.setAttribute("lastname",prof.getLastname());
                request.setAttribute("email",prof.getEmail());
                request.setAttribute("pic",prof.getPic());
                System.out.println(prof.getPic());
                System.out.println(prof.getPic());
                System.out.println(prof.getPic());
                System.out.println(prof.getPic());
                
                
            }
            RequestDispatcher rd=request.getRequestDispatcher("/UserProfile.jsp");
            rd.forward(request,response); 
        }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        if(request.getAttribute("account")!=null)doGet(request,response);
        else
        {
        HttpSession session=request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        Profile prof = (Profile) session.getAttribute("Profile");
        String firstname=request.getParameter("firstname");
        String lastname=request.getParameter("lastname");
        String email=request.getParameter("email");
        String username= lg.getUsername();
        prof.setFirstname(firstname);
        prof.setLastname(lastname);
        prof.setEmail(email);
        User us=new User();
        us.setCluster(cluster);
        us.EditProfile(username,firstname,lastname,email);
        
	response.sendRedirect("/Instagrim/Profile/"+lg.getUsername());}
    }

}
