<%@page import="dataObjects.FoodItemsDTO"%>
<%@page import="java.util.List"%>
<%@page import="dataAccess.FoodItemsDAO"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <%
        session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");

        FoodItemsDAO dao = new FoodItemsDAO();
        List<FoodItemsDTO> itemsList = dao.getFoodItem(userId);
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
    <link
        href="https://fonts.googleapis.com/css2?family=Edu+AU+VIC+WA+NT+Hand:wght@400..700&family=Playwrite+BE+VLG:wght@100..400&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="pageStyle.css">
    <title>Retialer</title>
</head>

<body>

    <header>
        <img src="./Photos/LogoNoBG.png" alt="Logo" class="TopLogo">
        <h3 class="pTitle">Retailer</h3>
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
    <form action="AddItem-URL" method="post">
        <div class="shopping-cart-sidebar">
            <h2>Add An Item</h2>
            <div class="Add-Item">

                <div class="textfieldSign">
                    <label for="Name">Name of product:</label>
                    <input type="text" name="name" id="name" placeholder="Name of product">
                    <p id="pName"></p>
                </div>

                <div class="textfieldSign">
                    <label for="Name">Quantity:</label>
                    <input type="text" name="name" id="name" placeholder="Quantity">
                    <p id="Quantity"></p>
                </div>

                <div class="textfieldSign">
                    <label for="Name">Expirty Date:</label>
                    <input type="date" name="name" id="name" placeholder="Date">
                    <p id="DateOfE"></p>
                </div>

                <div class="textfieldSign">
                    <label for="Name">Price:</label>
                    <input type="text" name="name" id="name" placeholder="Price">
                    <p id="Price"></p>
                </div>

                <div class="textfieldSign">
                    <label for="foodPreference">Select your food preference:</label>
                    <select id="foodPreference" name="foodPreference">
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
            </div>
            <button type="submit" id="BuyBtn">Add Item</button>
        </div>
    </form>

</body>

</html>