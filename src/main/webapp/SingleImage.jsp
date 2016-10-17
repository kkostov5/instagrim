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
            <h1>Single Picture</h1>
            <%
                Pic p = (Pic) request.getAttribute("Picture");
                String[][] comments = (String[][]) request.getAttribute("Comments");
                if (p == null) {
            %>
            <p>No Pictures found</p>

            <%
                        } else {%>

            <form method="POST"  action="Comments">
                <table>
                    <tr>
                    <img src="/Instagrim/Thumb/<%=p.getSUUID()%>">
                    <td>User</td> <td>Comment</td>
                    <%
                        if (comments != null) {
                            int i = 0;
                            while (comments.length < i) {

                    %>
                    <tr><td></td><td><%=comments[i][0]%></td> <td><%=comments[i][1]%></td></tr>
                    <%
                                    i++;
                                }
                            }
                        }
                    %>
                    <td>Comment</td> <td><input type="textbox" name="comment"></td>
                </table>
                <br/>
                <input type="submit" value="Comment"> 
            </form>

<!--<a href="/Instagrim/SingleImage.jsp" ><img src="/Instagrim/Thumb/<%//=p.getSUUID()%>"></a><br/>-->

        </article>
    </div>
    <footer>
        &COPY; Krasimir Kostov
    </footer>
</body>
</html>
