<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css">

    </head>
    <body>
        <div id="header">
            <a href="/Instagrim"><h1>InstaGrim</h1></a>
            <ul>
                    <li><a href="/Instagrim/Register">Register</a></li>
                    <li><a href="/Instagrim/Login">Login</a></li>
                </ul>
            </div><div id="body">
               
                <h3>Login</h3>
                <form method="POST"  action="Login">
                    <table>
                        <tr>
                            <td>User Name</td> <td><input type="text" name="username" required></td>
                        </tr>
                        <tr>
                            <td>Password</td><td><input type="password" name="password" required></td>
                        </tr>
                    </table>
                    <br/>
                    <%
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null)
out.println("<font color=red>"+login_msg+"</font>");
%>
                    <input type="submit" value="Login"> </br>
                </form>
                    <button type="button"><a href="/Instagrim/Register">Register</a></button></br>
        </div>
        <div id="footer">
            <div>
            <p>&COPY; Krasimir Kostov</p>
            </div>
        </div>
    </body>
</html>
