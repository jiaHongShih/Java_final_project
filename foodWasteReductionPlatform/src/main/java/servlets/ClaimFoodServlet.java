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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        boolean isUpdated = BusinessLogic.claimItem(quantity, id);
        if (isUpdated) {
            String sourcePage = request.getParameter("sourcePage");

            if ("Consumer.jsp".equals(sourcePage)) {
                response.sendRedirect("Consumer.jsp");
            } else if ("cOrg.jsp".equals(sourcePage)) {
                response.sendRedirect("cOrg.jsp");
            }
      
        } else {
            response.getWriter().println("Error updating the food item.");
        }
    }

}
