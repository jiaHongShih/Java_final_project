package functions;

import dataAccess.DBConnection;
import dataAccess.UserDAO;
import dataObjects.UserDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddUserTest {
    
    private Connection connection;

    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/foodwaste";
    private static final String USER = "Josh";
    private static final String PASSWORD = "CodeFlash73";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

     @BeforeEach
    public void setUp() throws SQLException {
        try {
            // Load the MySQL driver
            Class.forName(DRIVER);

            // Initialize a MySQL database connection
            connection = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
            DBConnection.getInstance().setTestConnection(connection);

            // Create the necessary table for testing
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS users ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "name VARCHAR(255),"
                        + "email VARCHAR(255),"
                        + "password VARCHAR(255),"
                        + "userType ENUM('retailer', 'consumer', 'charitable_organization') NOT NULL,"
                        + "location VARCHAR(255)"
                        + ")");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
    }

    @AfterEach
public void tearDown() throws SQLException {
    
    // Close the database connection after each test
    if (connection != null && !connection.isClosed()) {
        connection.close();
    }
}

    @Test
    public void testAddUser() {
        System.out.println("addUser");
        String name = "John";
        String email = "john.doe@example.com";
        String password = "password123";
        String userType = "consumer";
        String location = "Ottawa";
        boolean expResult = true;
        boolean result = BusinessLogic.addUser(name, email, password, userType, location);
        assertEquals(expResult, result);

        // Verify the user was added to the database
        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.selectUserByEmail(email);
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(userType, user.getUserType());
        assertEquals(location, user.getLocation());
    }
}