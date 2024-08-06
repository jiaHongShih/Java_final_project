package servlets;

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

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String userTypeParam = request.getParameter("userType");
        String city = request.getParameter("city");
        String question = request.getParameter("SecurityQuestion");
        String answer = request.getParameter("SecurityAnswer");
        response.setContentType("text/html;charset=UTF-8");

        String userType = null;
        if ("Consumer".equals(userTypeParam)) {
            userType = "CONSUMER";
        } else if ("Retailers".equals(userTypeParam)) {
            userType = "RETAILER";
        } else if ("Charitable Organization".equals(userTypeParam)) {
            userType = "CHARITABLE_ORGANIZATION";
        }
     
        int intQuestion = Integer.parseInt(question);
        boolean success = BusinessLogic.addUser(name, email, password, userTypeParam, city)
                && BusinessLogic.addUserQuestion(intQuestion, email, answer);
        
        if (success) {
            response.sendRedirect("index.jsp");
        } else {
            response.getWriter().println("Error adding the user.");
        }
    }
}
