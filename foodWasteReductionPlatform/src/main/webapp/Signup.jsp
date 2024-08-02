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

                <Label id="sTitle">SignUp</Label>
                <br>
                <br>

                <label for="" id="PickAcc">Please choose a type of account you would like to make.</label>

                <div id="TOA">
                    <label for="">Consumer</label>
                    <input type="radio" name="question" id="">
                    <label for="">Retailers</label>
                    <input type="radio" name="question" id="">
                    <label for="">Charitable Organization</label>
                    <input type="radio" name="question" id="">
                </div>
                <br>
    
                <div class="textfieldSign">
                    <label for="Name">Name</label>
                    <input type="text" name="name" id="name" placeholder="Type your name">
                    <p id="pName"></p>
                </div>
    
    
                <div class="textfieldSign">
                    <label for="Email">Email Address</label>
                    <input type="text" name="email" id="email" placeholder="Type your email">
                    <p id="pEmail"></p>
    
                </div>
               
    
                <div class="textfieldSign">
                    <label for="login">City</label>
                    <input type="text" name="Location" id="Location" placeholder="Enter Your Location">
                    <p id="pPhone"></p>
                </div>
    
    
                <div class="textfieldSign">
                    <label for="pass">Password</label>
                    <input type="password" name="pass" id="pass" placeholder="Type your password">
                    <p id="pPass"></p>
                </div>
                
    
                <div class="textfieldSign">
                    <label for="cPass">Confrim Password</label>
                    <input type="text" name="cPass" id="cPass" placeholder="Retype your password">
                    <p id="pUser"></p>
                </div>

                <Label id="SqTitle">Security Questions:</Label>
                <br>
                <br>

                <div class="textfieldSign">
                    <label for="sq1">What Is The Name Of Your First Cosuin?</label>
                    <input type="text" name="SecurityQuestion1" id="SecurityQuestion1" placeholder="Awnser Here">
                    <p id="pUser"></p>
                </div>

                <div class="textfieldSign">
                    <label for="sq2">What Was The Color Of Your First Car?</label>
                    <input type="text" name="SecurityQuestion2" id="SecurityQuestion2" placeholder="Awnser Here">
                    <p id="pUser"></p>
                </div>

                <div class="textfieldSign">
                    <label for="sq3">What City Were You Born In?</label>
                    <input type="text" name="SecurityQuestion3" id="SecurityQuestion3" placeholder="Awnser Here">
                    <p id="pUser"></p>
                </div>

                <div class="Sub">
                    <label for="Sub">Please Click Here To Allow Notifications</label>
                    <input type="checkbox" name="SubForNoti" id="SubForNoti" placeholder="Awnser Here">
                    <p id="pUser"></p>
                </div>

                <div id="Back2Login">
                <a href="index.jsp">Already Have An Account? Login Here.</a>
            </div>


    
            </div>
    
         
    
            <div class="BTN">
                <a href="index.jsp"><button id="LoginBtn">SignUp</button></a>
            </div>
    
        </div>
    


    </div>
    
</body>
</html>