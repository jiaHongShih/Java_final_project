/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;

import dataAccess.UserDAO;
import dataAccess.UserQuestionsDAO;
import dataObjects.UserDTO;
import dataObjects.UserQuestionsDTO;
import functions.Logger;

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

    private UserDAO userDAO;
    private UserQuestionsDAO userQuestionsDAO;

    public void init() {
        userDAO = new UserDAO();
        userQuestionsDAO = new UserQuestionsDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String newPassword = request.getParameter("Npass");
        String confirmPassword = request.getParameter("cNPass");
        String securityQuestion = request.getParameter("SQuestionsPick");
        String securityAnswer = request.getParameter("securityAnswer");

//        if (email == null || newPassword == null || confirmPassword == null || securityQuestion == null || securityAnswer == null ||
//                email.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty() || securityAnswer.isEmpty()) {
//            request.setAttribute("errorMessage", "All fields are required.");
//            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
//            return;
//        }
//
//        if (!newPassword.equals(confirmPassword)) {
//            request.setAttribute("errorMessage", "Passwords do not match.");
//            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
//            return;
//        }

        try {
            UserDTO user = userDAO.selectUserByEmail(email);
            if (user == null) {
                request.setAttribute("errorMessage", "No user found with the provided email address.");
                request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
                return;
            }

            UserQuestionsDTO userQuestion = userQuestionsDAO.getUserQuestionByEmail(email);
            if (userQuestion == null || !validateSecurityAnswer(userQuestion, securityQuestion, securityAnswer)) {
                request.setAttribute("errorMessage", "Invalid security question or answer.");
                request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
                return;
            }

            user.setPassword(newPassword);
            boolean isUpdated = userDAO.updateUser(user);
            if (isUpdated) {
                response.sendRedirect("index.jsp?successMessage=Password reset successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to reset password. Please try again.");
                request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
            }
        } catch (Exception e) {
            Logger.log("Failed to reset password: " + e.getMessage());
            request.setAttribute("errorMessage", "An error occurred while resetting your password. Please try again.");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }
    }

    private boolean validateSecurityAnswer(UserQuestionsDTO userQuestion, String securityQuestion, String securityAnswer) {
        return userQuestion.getQuestionID() == Integer.parseInt(securityQuestion) &&
               userQuestion.getAnswer().equalsIgnoreCase(securityAnswer);
    }
}
