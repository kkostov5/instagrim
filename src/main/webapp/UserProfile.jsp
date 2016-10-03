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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>InstaGrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css">
    </head>
    <body>
        <header>
            <h1>InstaGrim</h1>
        </header>
        <div>


           <%

                Profile prof = (Profile) session.getAttribute("Profile");
              if(prof==null){
            %>
            error
             <%
                 }
else{
            %>
            First name: <%=prof.getFirstname()%>
            Last name: <%=prof.getLastname()%>
            E-mail: <%=prof.getEmail()%>
            First name: <%=prof.getFirstname()%>
            <%
                 }
            %>
        </div>
        <footer>
            &COPY; Krasimir Kostov
        </footer>

    </body>
</html>
