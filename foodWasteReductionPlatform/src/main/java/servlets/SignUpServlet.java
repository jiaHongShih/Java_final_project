package servlets;

import dataAccess.UserDAO;
import dataObjects.UserDTO;
import functions.BusinessLogic;
import java.io.IOException;
import java.io.PrintWriter;
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
        String userTypeParam = request.getParameter("userType");
        String location = request.getParameter("location");
        response.setContentType("text/html;charset=UTF-8");

        // Get the response writer
        PrintWriter out = response.getWriter();

        // Generate the HTML content
        UserDTO user = new UserDTO(name, email, password, userTypeParam, location);
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(user);
        response.sendRedirect("index.jsp");
//         Determine the user type from the radio button selection
//        String userType = null;
//        if ("Consumer".equals(userTypeParam)) {
//            userType = "CONSUMER";
//        } else if ("Retailers".equals(userTypeParam)) {
//            userType = "RETAILER";
//        } else if ("Charitable Organization".equals(userTypeParam)) {
//            userType = "CHARITABLE_ORGANIZATION";
//        }
        // Call BusinessLogic to add the user
//        boolean success = BusinessLogic.addUser(name, email, password, userTypeParam, location);
//
//        if (success) {
//            // Redirect to the login page or another appropriate page upon successful signup
//            response.sendRedirect("index.jsp");
//        } else {
//            // Redirect back to the signup page with an error message
//            response.sendRedirect("signup.jsp?error=Signup+failed");
//        }
    }
}
