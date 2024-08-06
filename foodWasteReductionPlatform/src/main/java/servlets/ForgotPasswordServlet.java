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
            response.sendRedirect("Fpass.jsp?error=Invalid input");
            return;
        }

        UserDTO user = userDAO.selectUserByEmail(email);
        if (user == null) {
            response.sendRedirect("Fpass.jsp?error=User not found");
            return;
        }

        UserQuestionsDTO userQuestion = userQuestionsDAO.getUserQuestionByEmail(email);
        if (userQuestion == null || userQuestion.getQuestionID() != securityQuestionID ||
            !userQuestion.getAnswer().equals(securityAnswer)) {
            response.sendRedirect("Fpass.jsp?error=Invalid security question or answer");
            return;
        }

        user.setPassword(newPassword);
        boolean isUpdated = userDAO.updateUser(user);
        if (isUpdated) {
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("Fpass.jsp?error=Failed to update password");
        }
    }
}

