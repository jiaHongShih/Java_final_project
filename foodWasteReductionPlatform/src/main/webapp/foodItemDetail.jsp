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
    <h1>Food Item Detail</h1>

    <form action="UpdateFoodItemServlet-URL" method="post">
        <input type="hidden" name="id" value="<%= item.getId() %>"/>

        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= item.getName() %>" required>
        </div>

        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" class="form-control" id="quantity" name="quantity" value="<%= item.getQuantity() %>" required>
        </div>

        <div class="form-group">
            <label for="expirationDate">Expiration Date</label>
            <input type="date" class="form-control" id="expirationDate" name="expirationDate" value="<%= item.getExpirationDate() %>" required>
        </div>

        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" value="<%= item.getPrice() %>" required>
        </div>

        <div class="form-group">
            <label for="foodPreferences">Food Preferences</label>
            <input type="text" class="form-control" id="foodPreferences" name="foodPreferences" value="<%= item.getFoodPreferences() %>">
        </div>
        
        <div class="form-group">
            <label for="isSurplus">List as Surplus?</label>
            <input type="checkbox" id="isSurplus" name="isSurplus" value="<%= item.isSurplus()%>">
        </div>
        
        <button type="submit" class="btn btn-success">Save Changes</button>
    </form>
</div>
</body>
</html>
