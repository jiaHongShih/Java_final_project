<%@page import="dataObjects.FoodItemsDTO"%>
<%@page import="java.util.List"%>
<%@page import="dataAccess.FoodItemsDAO"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Integer userId = (Integer) request.getSession().getAttribute("userId");
    if (userId != null) {
        int id = userId;
    } else {
        response.sendRedirect("index.jsp");
    }

    FoodItemsDAO dao = new FoodItemsDAO();
    List<FoodItemsDTO> itemsList = dao.getFoodItem(userId);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="pageStyle.css">
    <title>Retailer</title>
    <style>
        .red-text {
            color: red;
        }
    </style>
    <script>
        function checkExpirationDates() {
            const today = new Date();
            const oneWeekLater = new Date();
            oneWeekLater.setDate(today.getDate() + 7);
            let expiringItems = [];

            document.querySelectorAll('.expiration-date').forEach(function (elem) {
                const expDate = new Date(elem.textContent);
                const isSurplus = elem.dataset.surplus === "true";
                if (expDate <= oneWeekLater && !isSurplus) {
                    elem.classList.add('red-text');
                    elem.closest('tr').querySelector('.surplus-status').classList.add('red-text');
                    expiringItems.push(elem.dataset.itemName);
                }
            });

            if (expiringItems.length > 0) {
                alert("The following items are expiring within a week and are not surplus:\n" + expiringItems.join("\n"));
            }
        }

        window.onload = checkExpirationDates;
    </script>
</head>

<body>
    <header>
        <img src="./Photos/SmartWaste.png" alt="Logo" class="TopLogo">
        <h3 class="pTitle">Retailer</h3>
        <a href="index.jsp"><button id="LogOutBTN">LogOut</button></a>
    </header>

    <div class="main-content">
        <div class="ItemList">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Expiration Date</th>
                        <th>Price</th>
                        <th>Surplus</th>
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
                        <td class="expiration-date" data-item-name="<%= item.getName() %>" data-surplus="<%= item.isSurplus() %>"><%= item.getExpirationDate() %></td>
                        <td><%= item.getPrice() %></td>
                        <td class="surplus-status"><%= item.isSurplus() ? "Yes" : "No" %></td>
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

    <form action="AddItemServlet-URL" method="post">
        <div class="shopping-cart-sidebar">
            <h2>Add An Item</h2>
            <div class="Add-Item">
                <div class="textfieldSign">
                    <label for="Name">Name of product:</label>
                    <input type="text" name="name" id="name" placeholder="Name of product">
                    <p id="pName"></p>
                </div>
                <div class="textfieldSign">
                    <label for="quantity">Quantity:</label>
                    <input type="text" name="quantity" id="quantity" placeholder="Quantity">
                    <p id="Quantity"></p>
                </div>
                <div class="textfieldSign">
                    <label for="expirationDate">Expiry Date:</label>
                    <input type="date" name="expirationDate" id="expirationDate" placeholder="Date">
                    <p id="DateOfE"></p>
                </div>
                <div class="textfieldSign">
                    <label for="price">Price:</label>
                    <input type="text" name="price" id="price" placeholder="Price">
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
