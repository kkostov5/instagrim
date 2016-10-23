<%-- 
    Document   : Following
    Created on : Oct 23, 2016, 7:52:48 PM
    Author     : Krasi
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
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
            </ul></div><div id="body"><div class="gallery">
                <h1>Following</h1>
                <%
                    java.util.LinkedList<java.util.UUID> lsPics = (java.util.LinkedList<java.util.UUID>) request.getAttribute("Pics");
                    java.util.LinkedList<String> users = (java.util.LinkedList<String>) request.getAttribute("Usernames");
                    if (users.isEmpty()) {
                %>
                <p>You are not following anyone!</p>
                <%
                } else {
                    Iterator<String> userIterator;
                    Iterator<java.util.UUID> picIterator;
                    userIterator = users.iterator();
                    picIterator = lsPics.iterator();
                    while (userIterator.hasNext()) {
                        String name = (String) userIterator.next();
                        java.util.UUID p = (java.util.UUID) picIterator.next();


                %>
                <a href="/Instagrim/Profile/<%=name%>"><div id="following">

                        <%
    if (p == null) {%>
                        <img src="/Instagrim/picture.jpg"/></br>
                        <%
                        } else {
                        %>
                        <img src="/Instagrim/Thumb/<%=p%>"></a><br/>
                        <%
                            }
                        %>
                        <p><%=name%></p>
                    </div></a>
                    <%
                            }
                        }
                    %>
            </div></div>



        <div id="footer">
            <div>
                <p>&COPY; Krasimir Kostov</p>
            </div>
        </div>
    </body>
</html>
