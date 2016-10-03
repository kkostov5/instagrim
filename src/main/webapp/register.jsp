<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css">
    </head>
    <body>
        <header>
            <h1>InstaGrim</h1>
        </header>
        <div>
            <article>
                <h3>Register as user</h3>
                <form method="POST"  action="Register">
                    <table>
                        <tr>
                            <td>First Name</td><td> <input type="text" name="firstname"></td>
                        </tr>
                        <tr>
                            <td>Last Name</td><td> <input type="text" name="lastname"></td>
                        </tr>
                        <tr>
                            <td>E-mail</td><td> <input type="text" name="lastname"></td>
                        </tr>
                        <tr>
                            <td>User Name</td><td> <input type="text" name="username"></td>
                        </tr>
                        <tr>
                            <td>Password</td><td><input type="password" name="password"></td>
                        </tr>
                    </table>
                    <br/>
                    <input type="submit" value="Register"> 
                </form>

            </article>
        </div>
        <footer>
            &COPY; Krasimir Kostov
        </footer>
    </body>
</html>
