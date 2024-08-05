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
import javax.servlet.http.HttpSession;


/**
 *
 * @author Nicholas Jacques
 */
public class ForgotPasswordServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    private UserQuestionsDAO userQuestionsDAO = new UserQuestionsDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String newPassword = request.getParameter("Npass");
        String confirmPassword = request.getParameter("cNPass");
        int securityQuestionID = Integer.parseInt(request.getParameter("SQuestionsPick"));
        String securityAnswer = request.getParameter("securityAnswer");

        // Validate input
        if (email == null || newPassword == null || confirmPassword == null || securityAnswer == null ||
                !newPassword.equals(confirmPassword)) {
            // Redirect or set an error message for invalid inputs
            response.sendRedirect("Fpass.jsp?error=Invalid input");
            return;
        }

        // Fetch the user by email
        UserDTO user = userDAO.selectUserByEmail(email);
        if (user == null) {
            // Redirect or set an error message if user is not found
            response.sendRedirect("Fpass.jsp?error=User not found");
            return;
        }

        // Fetch the security question for the user
        UserQuestionsDTO userQuestion = userQuestionsDAO.getUserQuestionByEmail(email);
        if (userQuestion == null || userQuestion.getQuestionID() != securityQuestionID ||
                !userQuestion.getAnswer().equals(securityAnswer)) {
            // Redirect or set an error message if security question/answer is incorrect
            response.sendRedirect("Fpass.jsp?error=Invalid security question or answer");
            return;
        }

        // Update the user's password
        user.setPassword(newPassword);
        boolean isUpdated = userDAO.updateUser(user);
        if (isUpdated) {

            // Redirect to the index page on successful password update
            response.sendRedirect("index.jsp");
        } else {
            // Redirect or set an error message if password update fails
            response.sendRedirect("Fpass.jsp?error=Failed to update password");
        }
    }
}

