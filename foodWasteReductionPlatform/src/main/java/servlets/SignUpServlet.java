/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author Nicholas Jacques
 */
public class SignUpServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
    private BusinessLogic businessLogic;

    public void init() {
        businessLogic = new BusinessLogic();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name"); //create variables with each parameter from form
        
                
        businessLogic.method(name); //pass parameters to businesslogic adduser method
        
        response.sendRedirect("success.jsp"); //change page
    }
}
