<%@page import="functions.BusinessLogic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="dataObjects.FoodItemsDTO"%>
<%@page import="java.util.List"%>
<%@page import="dataAccess.FoodItemsDAO"%>

<%
    Integer userId = (Integer) request.getSession().getAttribute("userId");
    if (userId != null) {
        int id = userId;
    } else {
        response.sendRedirect("index.jsp");
    }
    
    List<FoodItemsDTO> itemsList = BusinessLogic.listForLocation(userId);
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
    <style>
        .notifications {
            display: none;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            padding: 20px;
            position: fixed;
            right: 10px;
            width: 400px; /* Increase width */
            height: 80vh; /* Increase height */
            z-index: 100;
            overflow-y: auto;
        }
        .notifications.visible {
            display: block;
        }
        .subscription-form {
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin: 20px;
            position: fixed;
            bottom: 20px;
            left: 50%;
            transform: translateX(-100%);
            width: 80%;
            max-width: 600px;
        }
    </style>
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
                <table class="table table-striped">
        <thead>
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Expiration Date</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (FoodItemsDTO item : itemsList) {
            %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.getExpirationDate() %></td>
                    <td><%= item.getPrice() %></td>
                    <td>
                        <a href="foodItemDetail.jsp?id=<%= item.getId() %>" class="btn btn-primary">Edit</a>
                    </td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
        
        </div>
    </div>

    <div class="shopping-cart-sidebar">
        <button id="NotificationsBtn">Notifications</button>
        <div class="notifications" id="NotificationsContent">
            <!-- Smaller version of the main content to be displayed here -->
            <div class="ItemList">
                    <table class="table table-striped">
        <thead>
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Expiration Date</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (FoodItemsDTO item : itemsList) {
            %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.getExpirationDate() %></td>
                    <td><%= item.getPrice() %></td>
                    <td>
                        <a href="foodItemDetail.jsp?id=<%= item.getId() %>" class="btn btn-primary">Edit</a>
                    </td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
            
            </div>
        </div>
    </div>

    <div class="subscription-form">
        <h2>Subscribe to Food Preferences</h2>
    <form action="SubscriptionServlet-URL" method="post">
        <div class="form-group">
            <label for="foodPreference">Select your food preference:</label>
            <select id="foodPreference" name="foodPreference" class="form-control">
                <option value="fruits">Fruits</option>
                <option value="vegetables">Vegetables</option>
                <option value="grains">Grains</option>
                <option value="dairy">Dairy</option>
                <option value="proteins">Proteins</option>
                <option value="seafood">Seafood</option>
                <option value="snacks">Snacks</option>
                <option value="beverages">Beverages</option>
                <option value="sweets">Sweets</option>
                <option value="condiments">Condiments</option>
                <option value="bakedGoods">Baked Goods</option>
                <option value="cereals">Cereals</option>
                <option value="nutsAndSeeds">Nuts and Seeds</option>
                <option value="sauces">Sauces</option>
            </select>
        </div>

        <div class="form-group">
            <label>Preferred communication method:</label><br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="communicationMethod" id="contactMethodPhone" value="phone">
                <label class="form-check-label" for="contactMethodPhone">Phone</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="communicationMethod" id="contactMethodEmail" value="email">
                <label class="form-check-label" for="contactMethodEmail">Email</label>
            </div>
        </div>

        <div class="form-group">
            <label for="phoneNum">Phone Number:</label>
            <input type="tel" id="phoneNum" name="phoneNum" class="form-control">
        </div>

        <button type="submit" class="btn btn-primary">Subscribe</button>
    </form>
    </div>

    <script>
        document.getElementById('NotificationsBtn').addEventListener('click', function () {
            var notificationsContent = document.getElementById('NotificationsContent');
            notificationsContent.classList.toggle('visible');
        });
    </script>

</body>

</html>
