<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <header>
            <h1>InstaGrim</h1>
        </header>
        
        <div class="gallery">
                <!--<li class="nav"><a href="/Instagrim/Images/majed">Sample Images</a></li>-->
        <article>
            <button type="button"><a href="/Instagrim">Home</a></button>
            <h1>Your Pics</h1>
            <%
                java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
                if (lsPics == null) {
            %>
            <p>No Pictures found</p>
            <%
            } else {
                int i=0;
                Iterator<Pic> iterator;
                iterator = lsPics.iterator();
                while (iterator.hasNext()) {
                    Pic p = (Pic) iterator.next();

            %>
            <a class="expansion" href="#" id="img<%=i%>"><img src="/Instagrim/Image/<%=p.getSUUID()%>">
                <form class="expansion" method="POST"  action="Login">
                    <table>
                        <tr>
                            <td>Comment</td> <td><input type="text" name="comment"></td>
                        </tr>
                    </table>
                    <br/>
                    <input type="submit" value="Login"> 
                </form>
            </a>
            <a href="/Instagrim/Image/<%=p.getSUUID()%>"><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a><br/>
            <!--<a href="/Instagrim/SingleImage.jsp" ><img src="/Instagrim/Thumb/<%//=p.getSUUID()%>"></a><br/>-->
            <%
                    i++;
                    }
                }
                %>
        </article>
    </div>
    <footer>
        &COPY; Krasimir Kostov
    </footer>
</body>
</html>
