package servlets;

import dataObjects.UserDTO;
import functions.BusinessLogic;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String userTypeParam = request.getParameter("question");
        String location = request.getParameter("location");

        // Determine the user type from the radio button selection
        UserDTO.UserType userType = null;
        if ("Consumer".equals(userTypeParam)) {
            userType = UserDTO.UserType.CONSUMER;
        } else if ("Retailers".equals(userTypeParam)) {
            userType = UserDTO.UserType.RETAILER;
        } else if ("Charitable Organization".equals(userTypeParam)) {
            userType = UserDTO.UserType.CHARITABLE_ORGANIZATION;
        }
        
        // Call BusinessLogic to add the user
        boolean success = BusinessLogic.addUser(name, email, password, userType, location);

        if (success) {
            // Redirect to the login page or another appropriate page upon successful signup
            response.sendRedirect("index.jsp");
        } else {
            // Redirect back to the signup page with an error message
            response.sendRedirect("signup.jsp?error=Signup+failed");
        }
    }
}
