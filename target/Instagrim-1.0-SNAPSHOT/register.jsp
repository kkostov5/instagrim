<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
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
                <%

                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                if (lg == null) {
            %>
                <h3>Register as user</h3>
                <form method="POST"  action="Register">
                    <table>
                        <tr>
                            <td>First Name</td><td> <input type="text" name="firstname" required></td>
                        </tr>
                        <tr>
                            <td>Last Name</td><td> <input type="text" name="lastname" required></td>
                        </tr>
                        <tr>
                            <td>E-mail</td><td> <input type="text" name="email" required></td>
                        </tr>
                        <tr>
                            <td>User Name</td><td> <input type="text" name="username" required></td>
                            <%
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null)
out.println("<font color=red>"+login_msg+"</font>");
%>
                        </tr>
                        <tr>
                            <td>Password</td><td><input type="password" name="password" pattern=".{5,10}" required title="5 to 10 characters"></td>
                        </tr>
                    </table>
                    <br/>
                    <input type="submit" value="Register"> 
                </form>
<%
                } else {
                %>
                You are already logged in.
                <button type="button"><a href="/Instagrim">Menu</a></button>
                <%
                    }%>
           
        </div>
        <div id="footer">
            <div>
            <p>&COPY; Krasimir Kostov</p>
            </div>
        </div>
    </body>
</html>
