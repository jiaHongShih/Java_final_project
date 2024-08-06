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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JiaHong
 */
@WebServlet(name = "AddItemServlet-NAME", urlPatterns = {"/AddItemServlet-URL"})
public class AddItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        LocalDate expirationDate = LocalDate.parse(request.getParameter("expirationDate"));
        double price = Double.parseDouble(request.getParameter("price"));
        String foodPreferences = request.getParameter("foodPreference");

        FoodItemsDTO foodItem = new FoodItemsDTO();
        foodItem.setUserID(userId);
        foodItem.setName(name);
        foodItem.setQuantity(quantity);
        foodItem.setExpirationDate(expirationDate);
        foodItem.setPrice(price);
        foodItem.setFoodPreferences(foodPreferences);

        FoodItemsDAO dao = new FoodItemsDAO();
        dao.addFoodItem(foodItem);

        response.sendRedirect("retailer.jsp");
    }
}
