<%-- 
    Document   : OtherUser
    Created on : Oct 17, 2016, 5:19:41 PM
    Author     : Krasi
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
            <button type="button"><a href="/Instagrim/Profiled">Profile</a></button></br>
            <button type="button"><a href="/Instagrim/Upload">Upload</a></button></br>
            <button type="button"><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></button>
            <button type="button"><a href="/Instagrim/Logout">Logout</a></button>
                <%}
                } else {
                %>

                <button type="button"><a href="/Instagrim/Register">Register</a></button></br>
                <button type="button"><a href="/Instagrim/Login">Login</a></button>
                <%
                    }%>
        </div>
        <footer>
            &COPY; Krasimir Kostov
        </footer>

    </body>
</html>
