<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1>InstaGrim</h1>
        </header>
        <div>


            <%

                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                if (lg != null) {
                    if (lg.getlogedin()) {
            %>
            <button type="button"><a href="UserProfile.jsp">Profile</a></button></br>
            <button type="button"><a href="upload.jsp">Upload</a></button></br>
            <button type="button"><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></button>
                <%}
                } else {
                %>

                <button type="button"><a href="register.jsp">Register</a></button></br>
                <button type="button"><a href="login.jsp">Login</a></button>
                <%
                    }%>
        </div>
        <footer>
            &COPY; Krasimir Kostov
        </footer>

    </body>
</html>
