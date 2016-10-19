<%-- 
    Document   : UserProfile
    Created on : Oct 1, 2016, 11:44:51 AM
    Author     : Krasi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%//@page import="uk.ac.dundee.computing.aec.instagrim.pic.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <li><a href="/Instagrim/Profiled">Profile</a></li>
             <li><a href="/Instagrim/Upload">Upload</a></li>
             <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
             <li><a href="/Instagrim/Logout">Logout</a></li>
             </ul></div><div id="body">

           <%

               
                Profile prof = (Profile) session.getAttribute("Profile");
              if(prof==null){
            %>
            error
             <%
                 }
else{
            if(prof.getPic()==null)
{
            %>
           <img src="images.jpg"></br>
            <%
                 }
else{
            %>
            <a href="/Instagrim/Image/<%=prof.getPic()%>" ><img src="/Instagrim/Thumb/<%=prof.getPic()%>"></a><br/>
            <%}%>
            First name: <%=prof.getFirstname()%><br/>
            Last name: <%=prof.getLastname()%><br/>
            E-mail: <%=prof.getEmail()%><br/>
            <button type="button"><a href="/Instagrim">Home</a></button>
            <%
                
                }
                %>
                <button type="button"><a href="EditProfile.jsp">Edit Profile</a></button>
        </div>
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
