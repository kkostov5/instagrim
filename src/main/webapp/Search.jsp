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
                <h3>Search for a user</h3>
                <form method="POST" action="Search">
                    <table>
                        <tr>
                            <td>Search</td><td> <input type="text" name="account"></td>
                        </tr>
                    </table>
                    <br/>
                    <%
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null)
out.println("<font color=red>"+login_msg+"</font>");
%>
                    <input type="submit" value="Search"> 
                </form>
                
                
        </div>
                   
        
        <div id="footer">
            <div>
            <p>&COPY; Krasimir Kostov</p>
            </div>
        </div>

    </body>
</html>
