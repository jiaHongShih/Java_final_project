/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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
 * @author JiaHong
 */
public class ClaimFoodServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        int userId = (Integer) session.getAttribute("userId");
        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        LocalDate expirationDate = LocalDate.parse(request.getParameter("expirationDate"));
//        double price = Double.parseDouble(request.getParameter("price"));
//        String foodPreferences = request.getParameter("foodPreferences");
//        boolean isSurplus = request.getParameter("isSurplus") != null;

        boolean isUpdated = BusinessLogic.claimItem(quantity, id);
        if (isUpdated) {
            response.sendRedirect("Consumer.jsp");
        } else {
            response.getWriter().println("Error updating the food item.");
        }
    }

}
