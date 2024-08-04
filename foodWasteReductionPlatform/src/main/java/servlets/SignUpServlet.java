package servlets;

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
        String userTypeParam = request.getParameter("question");
        String location = request.getParameter("location");
        response.setContentType("text/html;charset=UTF-8");

        // Get the response writer
        PrintWriter out = response.getWriter();

        // Generate the HTML content
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registration Details</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Registration Details</h1>");
        out.println("<p><strong>Name:</strong> " + name + "</p>");
        out.println("<p><strong>Email:</strong> " + email + "</p>");
        out.println("<p><strong>Password:</strong> " + password + "</p>");
        out.println("</body>");
        out.println("</html>");

        
        // Determine the user type from the radio button selection
//        UserDTO.UserType userType = null;
//        if ("Consumer".equals(userTypeParam)) {
//            userType = UserDTO.UserType.CONSUMER;
//        } else if ("Retailers".equals(userTypeParam)) {
//            userType = UserDTO.UserType.RETAILER;
//        } else if ("Charitable Organization".equals(userTypeParam)) {
//            userType = UserDTO.UserType.CHARITABLE_ORGANIZATION;
//        }
//        
//        // Call BusinessLogic to add the user
//        boolean success = BusinessLogic.addUser(name, email, password, userType, location);
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
