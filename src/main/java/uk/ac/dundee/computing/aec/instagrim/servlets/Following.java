package uk.ac.dundee.computing.aec.instagrim.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.Profile;

/**
 *
 * @author Krasi
 */
@WebServlet(name = "Following", urlPatterns = {"/Following"})
public class Following extends HttpServlet {

    Cluster cluster = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        if (lg == null) {
            response.sendRedirect("/Instagrim");
        } else {
            PicModel tm = new PicModel();
            tm.setCluster(cluster);
            User user = new User();
            user.setCluster(cluster);
            java.util.LinkedList<java.util.UUID> Pics = new java.util.LinkedList<>();
            java.util.LinkedList<String> username = new java.util.LinkedList<>();
            Profile prof = (Profile) session.getAttribute("Profile");
            for (String s : prof.getFollowing()) {
                username.add(s);
                System.out.println("Userasdfasegadf" + s);
                Pics.add(user.getProfilePic(s));

            }
            RequestDispatcher rd = request.getRequestDispatcher("/Following.jsp");
            request.setAttribute("Pics", Pics);
            request.setAttribute("Usernames", username);
            rd.forward(request, response);
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String value = request.getParameter("Follow");
        String username = request.getParameter("Followee");
        System.out.println(value);
        System.out.println(value);
        System.out.println(username);
        System.out.println(username);
        HttpSession session = request.getSession();
        Profile prof = (Profile) session.getAttribute("Profile");
        User us = new User();
        us.setCluster(cluster);
        if ("Follow".equals(value)) {
            Set<String> stuff = prof.getFollowing();
            stuff.add(username);
            System.out.println(stuff);
            prof.setFollowing(stuff);
            us.addFollowing(prof.getUsername(), username);

        } else if ("Unfollow".equals(value)) {
            Set<String> stuff = prof.getFollowing();
            stuff.remove(username);
            System.out.println(stuff);
            prof.setFollowing(stuff);
            us.deleteFollowing(prof.getUsername(), username);
        }

        //session.setAttribute("account",username);
        String path = "/Instagrim/Profile/" + username;
        System.out.println(path);
        response.sendRedirect(path);
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
