<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dataAccess.FoodItemsDAO" %>
<%@ page import="dataObjects.FoodItemsDTO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDate" %>
<%-- 
    Document   : foodItemDetail.jsp
    Created on : Aug 4, 2024, 5:50:53 PM
    Author     : JiaHong
--%>
<%
    int itemId = Integer.parseInt(request.getParameter("id"));
    FoodItemsDAO dao = new FoodItemsDAO();
    FoodItemsDTO item = dao.getOneFoodItem(itemId);
%>

<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Food Item Detail</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Purchase</h1>

    <form action="ClaimFoodServlet-URL" method="post">
        <input type="hidden" name="id" value="<%= item.getId() %>"/>
        <input type="hidden" id="isSurplus" name="isSurplus" value="<%= item.isSurplus()%>">
        <input type="hidden" class="form-control" id="foodPreferences" name="foodPreferences" value="<%= item.getFoodPreferences() %>">

        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= item.getName() %>" required readonly>
        </div>

        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" class="form-control" id="quantity" name="quantity" placeholder="Max: <%= item.getQuantity() %>" max="<%= item.getQuantity() %>" required>
        </div>

        <div class="form-group">
            <label for="expirationDate">Expiration Date</label>
            <input type="date" class="form-control" id="expirationDate" name="expirationDate" value="<%= item.getExpirationDate() %>" required readonly>
        </div>

        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" value="<%= item.getPrice() %>" required readonly>
        </div>        
        
        <button type="submit" class="btn btn-success">Confirm</button>
    </form>
</div>
</body>
</html>
