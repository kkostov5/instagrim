<%-- 
    Document   : EditProfile
    Created on : Oct 5, 2016, 1:18:36 PM
    Author     : Krasi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>InstaGrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css">
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

                Profile prof = (Profile) session.getAttribute("Profile");
            %>
                <h3>Edit Profile</h3>
                <form method="POST" action="UserProfile">
                    <table>
                        <tr>
                            <td>First Name</td><td> <input type="text" name="firstname" value="<%=prof.getFirstname()%>"></td>
                        </tr>
                        <tr>
                            <td>Last Name</td><td> <input type="text" name="lastname" value="<%=prof.getLastname()%>"></td>
                        </tr>
                        <tr>
                            <td>E-mail</td><td> <input type="text" name="email" value="<%=prof.getEmail()%>"></td>
                        </tr>

                    </table>
                    <br/>
                    <input type="submit" value="Edit"> 
                </form>
        </div>
              
           
            </article>
        </div>
        <div id="footer">
            <div>
            <p>&COPY; Krasimir Kostov</p>
            </div>
        </div>
    </body>
</html>
