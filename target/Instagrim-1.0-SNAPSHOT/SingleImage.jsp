<%-- 
    Document   : SingleImage
    Created on : Oct 5, 2016, 7:01:11 PM
    Author     : Krasi
--%>

<%@page import="java.util.Iterator"%>
<%@page import="com.datastax.driver.core.Row"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
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
             <li><a href="/Instagrim/Search">Search Users</a></li>
             <li><a href="/Instagrim/Logout">Logout</a></li>
             </ul></div><div id="body"><div class="gallery">
            <h1>Single Picture</h1>
            <%
                Pic p = (Pic) request.getAttribute("Picture");
                java.util.List<Row> comments = (java.util.List<Row>) request.getAttribute("Comments");
                if (p == null) {
            %>
            <p>No Pictures found</p>

            <%
                        } else {%>

            <form method="POST"  action="Comments">
                <table>
                    <tr>
                    <img src="/Instagrim/Thumb/<%=p.getSUUID()%>">
                    <%
                        Boolean check = (Boolean) request.getAttribute("isUserPicture");
                        if(check)
                        {
                            
                    %>
                   <input type="submit" name = "delete" value="Delete Picture" onClick='window.confirm("Proceed with the deletion of the picture?")'></button></br>
                    <%
                        }if (comments != null) {
                            int i = 0;
                    
                    
                    
                    %>
                    <td>User</td> <td>Comment</td>
                    <%
                        Iterator<Row> iterator;
                        iterator = comments.iterator();
                        while (iterator.hasNext()) {
                        Row row = (Row) iterator.next();
                           // while (comments.length < i) {

                    %>
                    <tr><td><%=row.getString("user")%></td> <td><%=row.getString("comment")%></td></tr>
                    <%
                                    i++;
                                }
                            }%>
                       
                    <td>Comment</td> <td><input type="textbox" name="comment"></td>
                </table>
                <br/>
                <input type="submit" value="Comment"> 
            </form>
<% }
                    %>
                 </div></div>


        
<!--<a href="/Instagrim/SingleImage.jsp" ><img src="/Instagrim/Thumb/<%//=p.getSUUID()%>"></a><br/>-->


    <div id="footer">
            <div>
            <p>&COPY; Krasimir Kostov</p>
            </div>
        </div>
</body>
</html>
