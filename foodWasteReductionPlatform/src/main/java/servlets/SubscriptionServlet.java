/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;

import functions.BusinessLogic;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nicholas Jacques
 */
public class SubscriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get user ID from session
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Get form parameters
        String foodPreference = request.getParameter("foodPreference");
        String phoneNum = request.getParameter("phoneNum");
        String communicationMethod = request.getParameter("communicationMethod");

        BusinessLogic.addSubscription(userId, phoneNum, communicationMethod, foodPreference);

        // Redirect to consumer page to show updated subscription
        response.sendRedirect("Consumer.jsp");
    }
}
