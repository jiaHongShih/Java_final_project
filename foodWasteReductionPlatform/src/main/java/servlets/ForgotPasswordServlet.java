/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;

import dataAccess.UserDAO;
import dataAccess.UserQuestionsDAO;
import dataObjects.UserDTO;
import dataObjects.UserQuestionsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 * @author Nicholas Jacques
 */
public class ForgotPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO = new UserDAO();
    private UserQuestionsDAO userQuestionsDAO = new UserQuestionsDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String newPassword = request.getParameter("Npass");
        String confirmPassword = request.getParameter("cNPass");
        String securityAnswer = request.getParameter("securityAnswer");

        // Input validation
        if (newPassword == null || confirmPassword == null || email == null || securityAnswer == null) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("fpass.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("fpass.jsp").forward(request, response);
            return;
        }

        // Check if the email exists
        UserDTO user = userDAO.selectUserByEmail(email);
        if (user == null) {
            request.setAttribute("errorMessage", "Email not found.");
            request.getRequestDispatcher("fpass.jsp").forward(request, response);
            return;
        }

        // Retrieve the security question answer
        UserQuestionsDTO userQuestion = userQuestionsDAO.getUserQuestionByEmail(email);
        if (userQuestion == null || !userQuestion.getAnswer().equals(securityAnswer)) {
            request.setAttribute("errorMessage", "Security answer is incorrect.");
            request.getRequestDispatcher("fpass.jsp").forward(request, response);
            return;
        }

        // Update the user's password
        user.setPassword(newPassword);
        boolean updated = userDAO.updateUser(user);
        if (updated) {
            response.sendRedirect("index.jsp?message=Password+updated+successfully");
        } else {
            request.setAttribute("errorMessage", "Password update failed.");
            request.getRequestDispatcher("fpass.jsp").forward(request, response);
        }
    }
}

