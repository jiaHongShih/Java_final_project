<%-- 
    Document   : index
    Created on : Aug 4, 2024, 3:26:43 PM
    Author     : JiaHong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Playwrite+BE+VLG:wght@100..400&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Edu+AU+VIC+WA+NT+Hand:wght@400..700&family=Playwrite+BE+VLG:wght@100..400&display=swap" rel="stylesheet">
    <title>LoginPage</title>
</head>

<body>
    <div class="Page">
        <div class="Login">
            <img id="Logotxt" src="Photos/SmartWaste.png" alt="Logo">
            <div id="LoginFRM">
                <h2 id="subTText">Good to see you again.</h2>
                <form action="loginServlet-URL" method="post">
                    <input class="Emailtxt" name="email" placeholder="Email" type="text" required>
                    <br>
                    <input class="PassTxt" name="pass" placeholder="Password" type="password" required>
                    <br>
                    <a href="Fpass.jsp">Forgot Password?</a>
                    <br>
                    <button type="submit" class="login-button">Login!</button>
                </form>
            </div>
        </div>

        <div class="Sidebar">
            <div id="SidebarFRM">
                <h1 class="sBarText">New Here?</h1>
                <h2 class="sBarText">Click To Sign Up Below.</h2>
                <a href="Signup.jsp"><button class="signup-button">SignUp!</button></a>
            </div>
        </div>
    </div>
</body>

</html>
