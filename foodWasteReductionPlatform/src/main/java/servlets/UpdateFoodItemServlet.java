/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import dataAccess.FoodItemsDAO;
import dataObjects.FoodItemsDTO;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JiaHong
 */
public class UpdateFoodItemServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        LocalDate expirationDate = LocalDate.parse(request.getParameter("expirationDate"));
        double price = Double.parseDouble(request.getParameter("price"));
        String foodPreferences = request.getParameter("foodPreferences");

        FoodItemsDTO foodItem = new FoodItemsDTO();
        foodItem.setId(id);
        foodItem.setName(name);
        foodItem.setQuantity(quantity);
        foodItem.setExpirationDate(expirationDate);
        foodItem.setPrice(price);
        foodItem.setFoodPreferences(foodPreferences);

        FoodItemsDAO dao = new FoodItemsDAO();
        boolean isUpdated = dao.updateFoodItem(foodItem);

        if (isUpdated) {
            response.sendRedirect("retailer.jsp");
        } else {
            response.getWriter().println("Error updating the food item.");
        }
    }
}
