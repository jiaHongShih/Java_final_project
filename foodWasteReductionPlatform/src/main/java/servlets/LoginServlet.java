/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;

import dataAccess.UserDAO;
import dataObjects.UserDTO;
import functions.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Nicholas Jacques
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        UserDTO user = userDAO.selectUserByEmailAndPassword(email, password);

        if (user != null) {
            String userType = user.getUserType();
            request.getSession().setAttribute("user", user);

            switch (userType) {
                case "Consumer":
                    response.sendRedirect("Consumer.jsp");
                    break;
                case "Retailers":
                    response.sendRedirect("retailer.jsp");
                    break;
                case "Organization":
                    response.sendRedirect("cOrg.jsp");
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } else {
            response.sendRedirect("loginError.jsp");
        }
    }
}
