<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" >
        <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>

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
             <li><a href="/Instagrim/Logout">Logout</a></li>
             </ul></div><div id="body">
            <script type="text/javascript">
                function readURL(input) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            $('#blah')
                                    .attr('src', e.target.result)
                                    .width(150)
                                    .height(200);
                        };

                        reader.readAsDataURL(input.files[0]);
                    }
                }
            </script>
            <!-- <li class="nav"><a href="/Instagrim/Images/majed">Sample Images</a></li> -->
            <article>
                <h3>File Upload</h3>
                <form class="upload" method="POST" enctype="multipart/form-data" action="Image">
                    <!--<input type="radio" name="Filter" value="Black">Black and White<br/>
                    <input type="radio" name="Filter" value="None">None<br/>-->
                    <input type="checkbox" name="profilepic" value="true">Set as profile picture<br/>
                    <input type="file" name="upfile" onchange="readURL(this);"><br/>
                    <img id="blah" src="#" alt="your image" />

                    <br/>
                    <%%>
                    <input type="submit" value="Submit">
                </form>
        </div>

                   
        
        
        <div id="footer">
            <div>
            <p>&COPY; Krasimir Kostov</p>
            </div>
        </div>
    </body>
</html>
