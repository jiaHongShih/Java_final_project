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
        <label for="cNPass">Confrim New Password</label>
        <input type="text" name="cNPass" id="cNPass" placeholder="Retype your new password">
        <p id="pUser"></p>
    </div>

    <h3>Please enter a question below:</h3>

    <div id="ContainerSQuestions">
        <select name="" id="SQuestionsPick">
            <option value="1">What Is The Name Of Your First Cosuin?</option>
            <option value="2">What Was The Color Of Your First Car?</option>
            <option value="3">What City Were You Born In?</option>
        </select>
        <input type="text" placeholder="Awnser here">
    </div>
    <br>
   

    <a href="index.jsp"><button type = "submit" rPass">Reset Password</button></a>

   

</div>
    
</body>
</html>