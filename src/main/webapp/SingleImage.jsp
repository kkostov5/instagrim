<%-- 
    Document   : SingleImage
    Created on : Oct 5, 2016, 7:01:11 PM
    Author     : Krasi
--%>

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
                Iterator<Pic> iterator;
                iterator = lsPics.iterator();
                while (iterator.hasNext()) {
                    Pic p = (Pic) iterator.next();

            %>
            <a href="/Instagrim/Image/<%=p.getSUUID()%>" ><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a><br/><%

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
