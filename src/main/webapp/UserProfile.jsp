<%-- 
    Document   : UserProfile
    Created on : Oct 1, 2016, 11:44:51 AM
    Author     : Krasi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id="header">
            <a href="/Instagrim"><h1>InstaGrim</h1></a>
            <%

                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            %>
            <ul>
                <li><a href="/Instagrim/Profile/<%=lg.getUsername()%>">Profile</a></li>
                <li><a href="/Instagrim/Upload">Upload</a></li>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><a href="/Instagrim/Following">Following</a></li>
                <li><a href="/Instagrim/Search">Search Users</a></li>
                <li><a href="/Instagrim/Logout">Logout</a></li>
            </ul></div><div id="body">

            <%
             
            if (request.getAttribute("pic") == null) {
            %>

            <img src="/Instagrim/picture.jpg"/></br>
            <%
            } else {
            %>
            <a href="/Instagrim/Comments/${pic}" ><img src="/Instagrim/Thumb/${pic}"></a><br/>
                <%}%>
            Username: ${username}<br/>
            First name: ${firstname}<br/>
            Last name: ${lastname}<br/>
            E-mail: ${email}<br/>
            </div>
            <%
             Profile prof = (Profile) session.getAttribute("Profile");
             if (prof.getUsername() == request.getAttribute("username")) {
           %>
            <button type="button"><a href="/Instagrim/Profile/<%=lg.getUsername()%>/EditProfile">Edit Profile</a></button>
        </div>
        <%}
else{
%>
        <button type="button"><a href="/Instagrim/Images/${username}">User's images</a></button>
        <form method="POST" action="Following">
            <input type="hidden" name="Followee" value="${username}">
            <input type="submit" name="Follow" value="${follower}">
        </form>
        <%
}
                
%>

        <div id="footer">
            <div>
                <p>&COPY; Krasimir Kostov</p>
            </div>
        </div>

    </body>
</html>
