<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <div id="header">
            <a href="/Instagrim"><h1>InstaGrim</h1></a>
            <%

                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                if (lg != null) {
                    if (lg.getlogedin()) {
            %>
            <ul>
            <li><a href="/Instagrim/Profile/<%=lg.getUsername()%>">Profile</a></li>
             <li><a href="/Instagrim/Upload">Upload</a></li>
             <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
             <li><a href="/Instagrim/Logout">Logout</a></li>
             </ul></div><div id="body"><div class="gallery">
                <!--<li class="nav"><a href="/Instagrim/Images/majed">Sample Images</a></li>-->
            <button type="button"><a href="/Instagrim">Home</a></button>
            <h1>Your Pics</h1>
            <%
                java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
                if (lsPics == null) {
            %>
            <p>No Pictures found</p>
            <%
            } else {
                int i=0;
                Iterator<Pic> iterator;
                iterator = lsPics.iterator();
                while (iterator.hasNext()) {
                    Pic p = (Pic) iterator.next();

            %>
            <a href="/Instagrim/Comments/<%=p.getSUUID()%>"><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a><br/>
            <!--<a href="/Instagrim/SingleImage.jsp" ><img src="/Instagrim/Thumb/<%//=p.getSUUID()%>"></a><br/>-->
            <%
                    i++;
                    }
                }
                %>
    </div></div>
                <%}
                } else {
                response.sendRedirect("/Instagrim");
                    }%>
        
        
    <div id="footer">
            <div>
            <p>&COPY; Krasimir Kostov</p>
            </div>
        </div>
</body>
</html>
