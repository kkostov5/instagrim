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
        <link rel="stylesheet" type="text/css" href="Styles.css">
    </head>
    <body>
        <header>
            <h1>InstaGrim</h1>
        </header>
        <div>
            <article>
              
           <%

                Profile prof = (Profile) session.getAttribute("Profile");
            %>
                <h3>Edit Profile</h3>
                <form method="POST" action="EditProfile">
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
                <button type="button"><a href="index.jsp">Menu</a></button>
            </article>
        </div>
        <footer>
            &COPY; Krasimir Kostov
        </footer>
    </body>
</html>
