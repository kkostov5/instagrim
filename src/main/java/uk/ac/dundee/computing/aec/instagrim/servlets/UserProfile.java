/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Krasi
 */
@WebServlet(name = "UserProfile", urlPatterns = {
    "/Profile",
    "/Profile/*"})
public class UserProfile extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        RequestDispatcher rd=request.getRequestDispatcher("UserProfile.jsp");
	rd.forward(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        RequestDispatcher rd=request.getRequestDispatcher("/Instagrim/Profile");
	rd.forward(request,response);
    }

}
