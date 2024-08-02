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
public class ConsumerServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name"); //create variables with each parameter from form
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String userType = request.getParameter("question");
        String location = request.getParameter("location");
        
        BusinessLogic.addUser(name, email, password, userType, location); //pass parameters to businesslogic adduser method
        
        response.sendRedirect("index.jsp"); //change page
    }
}
