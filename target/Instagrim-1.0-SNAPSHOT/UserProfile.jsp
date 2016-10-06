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
            if(prof.getPic()==null)
{
            %>
           <img src="../java/uk/ac/dundee/computing/aec/instagrim/pic/images.jpg" alt=""/></br>
            First name: <%=prof.getFirstname()%><br/>
            Last name: <%=prof.getLastname()%><br/>
            E-mail: <%=prof.getEmail()%><br/>
            <button type="button"><a href="/Instagrim">Home</a></button>
            <%
                 }
else{
            %>
            <a href="/Instagrim/Image/<%=prof.getPic().getSUUID()%>" ><img src="/Instagrim/Thumb/<%=prof.getPic().getSUUID()%>"></a><br/>
            First name: <%=prof.getFirstname()%><br/>
            Last name: <%=prof.getLastname()%><br/>
            E-mail: <%=prof.getEmail()%><br/>
            <button type="button"><a href="/Instagrim">Home</a></button>
            <%
                }}
                %>
                <button type="button"><a href="EditProfile.jsp">Edit Profile</a></button>
        </div>
        <footer>
            &COPY; Krasimir Kostov
        </footer>

    </body>
</html>
