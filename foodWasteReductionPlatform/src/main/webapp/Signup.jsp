<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>SignUp</title>
</head>
<body>
    <div class="SignupP">
        <div class="SignUpWrapper">
            <div class="textInputsSign">
                <label id="sTitle">SignUp</label>
                <br>
                <br>

    
                <form action="signup-URL" method="post">
                    <label for="" id="PickAcc">Please choose a type of account you would like to make.</label>

                    <div id="TOA">
                        <label for="consumer">Consumer</label>
                        <input type="radio" name="userType" value="Consumer" id="consumer">
                        <label for="retailers">Retailers</label>
                        <input type="radio" name="userType" value="Retailers" id="retailers">
                        <label for="charitable">Charitable Organization</label>
                        <input type="radio" name="userType" value="Organization" id="charitable">
                    </div>
                    <br>
                    <div class="textfieldSign">
                        <label for="name">Name</label>
                        <input type="text" name="name" id="name" placeholder="Type your name">
                        <p id="pName"></p>
                    </div>
        
                    <div class="textfieldSign">
                        <label for="email">Email Address</label>
                        <input type="text" name="email" id="email" placeholder="Type your email">
                        <p id="pEmail"></p>
                    </div>
        
                    <div class="textfieldSign">
                        <label for="city">City</label>
                        <input type="text" name="city" id="city" placeholder="Type your city">
                        <p id="pCity"></p>
                    </div>
        
                    <div class="textfieldSign">
                        <label for="pass">Password</label>
                        <input type="password" name="pass" id="pass" placeholder="Type your password">
                        <p id="pPass"></p>
                    </div>
        
                    <div class="textfieldSign">
                        <label for="cPass">Confirm Password</label>
                        <input type="password" name="cPass" id="cPass" placeholder="Retype your password">
                        <p id="pUser"></p>
                    </div>

                    <label id="SqTitle">Security Questions:</label>
                    <br>
                    <br>

                    <div class="textfieldSign">
                        <label for="SecurityQuestion">Select a Security Question</label>
                        <select name="SecurityQuestion" id="SecurityQuestion">
                            <option value="1">What Is The Name Of Your First Cousin?</option>
                            <option value="2">What Was The Color Of Your First Car?</option>
                            <option value="3">What City Were You Born In?</option>
                        </select>
                    </div>

                    <div class="textfieldSign">
                        <label for="SecurityAnswer">Answer</label>
                        <input type="text" name="SecurityAnswer" id="SecurityAnswer" placeholder="Answer Here">
                        <p id="pUser"></p>
                    </div>

                    <div id="Back2Login">
                        <a href="index.jsp">Already Have An Account? Login Here.</a>
                    </div>

                    <div class="BTN">
                        <button type="submit" id="SignUpBtn">SignUp</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
