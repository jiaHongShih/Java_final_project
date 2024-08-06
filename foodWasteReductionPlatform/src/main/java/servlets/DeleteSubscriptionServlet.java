/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import dataAccess.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JiaHong
 */
public class DeleteSubscriptionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                deleteSubscription(id);
                response.sendRedirect("Consumer.jsp"); // Redirect to the subscriptions page after deletion
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID parameter is missing.");
        }
    }

    private void deleteSubscription(int id) throws ServletException {
        Connection connection = null;
        PreparedStatement prepQuery = null;
        ResultSet rs = null;
        String DELETE_SUBSCRIPTION_QUERY = "DELETE FROM Subscriptions WHERE id = ?";
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(DELETE_SUBSCRIPTION_QUERY);
            prepQuery.setInt(1, id);
            prepQuery.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }

}
