<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <header>
            <h1>InstaGrim</h1>
        </header>
        <div>
                <!-- <li class="nav"><a href="/Instagrim/Images/majed">Sample Images</a></li> -->
            <article>
                <h3>File Upload</h3>
                <form method="POST" enctype="multipart/form-data" action="Image">
                    File to upload: <input type="file" name="upfile"><br/>
                    <br/>
                    <input type="submit" value="Press"> to upload the file!
                </form>

            </article>
        </div>
        <footer>
            &COPY; Krasimir Kostov
        </footer>
    </body>
</html>
