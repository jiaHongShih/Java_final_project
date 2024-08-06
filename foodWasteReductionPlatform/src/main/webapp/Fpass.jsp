<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Forgot Password?</title>
</head>
<body>
    <div class="FPassWrapper">
        <h1>Forgot Password?</h1>
        <img id="FPassPhoto" src="./Photos/FPass.png" alt="">

        <form action="ForgotPasswordServlet-URL" method="post">
            <div class="FpassInput">
                <label for="Email">Email Address</label>
                <input type="text" name="email" id="email" placeholder="Type your email">
                <p id="pEmail"></p>
            </div>

            <div class="FpassInput">
                <label for="Npass">New Password</label>
                <input type="password" name="Npass" id="Npass" placeholder="Type your new password">
                <p id="pPass"></p>
            </div>

            <div class="FpassInput">
                <label for="cNPass">Confirm New Password</label>
                <input type="password" name="cNPass" id="cNPass" placeholder="Retype your new password">
                <p id="pUser"></p>
            </div>

            <h3>Please enter a question below:</h3>
            <div id="ContainerSQuestions">
                <select name="SQuestionsPick" id="SQuestionsPick">
                    <option value="1">What Is The Name Of Your First Cousin?</option>
                    <option value="2">What Was The Color Of Your First Car?</option>
                    <option value="3">What City Were You Born In?</option>
                </select>
                <input type="text" name="securityAnswer" placeholder="Answer here">
            </div>
            <br>
            <button type="submit" class="rPass">Reset Password</button>
        </form>
        <div id="Back2Login">
            <a href="index.jsp">Return to Login Page</a>
        </div>
    </div>
</body>
</html>
