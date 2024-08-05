<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <%
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId != null) {
            int id = userId;
        }else{
            response.sendRedirect("index.jsp");
        }
    %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Playwrite+BE+VLG:wght@100..400&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Edu+AU+VIC+WA+NT+Hand:wght@400..700&family=Playwrite+BE+VLG:wght@100..400&display=swap" rel="stylesheet">
    <title>Consumer Page</title>
    <link rel="stylesheet" href="pageStyle.css">
</head>

<body>

    <header>
        <img src="./Photos/LogoNoBG.png" alt="Logo" class="TopLogo">
        <h3 class="pTitle">Consumer</h3>
        <a href="index.html"><button id="LogOutBTN">LogOut</button></a>
    </header>

    <div class="main-content">
        <!-- div to hold all items posted for consumer to buy. -->
        <div class="ItemList">
        
        </div>
    </div>


    <div class="shopping-cart-sidebar">
        <h2>Shopping Cart</h2>
        <div class="cart-items">
         
        </div>
        <button id="BuyBtn">Buy</button>
    </div>

</body>

</html>
