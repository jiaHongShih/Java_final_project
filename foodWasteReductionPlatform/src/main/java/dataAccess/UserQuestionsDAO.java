/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.UserQuestionsDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh Barrett
 */
public class UserQuestionsDAO {

    private static final String URL = "jdbc:your_database_url";
    private static final String USER = "your_database_user";
    private static final String PASSWORD = "your_database_password";

    private static final String INSERT_QUERY = "INSERT INTO user_questions (questionID, email, userID, answer) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM user_questions WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE user_questions SET questionID = ?, email = ?, userID = ?, answer = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM user_questions WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM user_questions";

    public void addUserQuestion(UserQuestionsDTO userQuestion) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, userQuestion.getQuestionID());
            preparedStatement.setString(2, userQuestion.getEmail());
            preparedStatement.setInt(3, userQuestion.getUserID());
            preparedStatement.setString(4, userQuestion.getAnswer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserQuestionsDTO getUserQuestion(int id) {
        UserQuestionsDTO userQuestion = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                userQuestion = new UserQuestionsDTO();
                userQuestion.setQuestionID(rs.getInt("questionID"));
                userQuestion.setEmail(rs.getString("email"));
                userQuestion.setUserID(rs.getInt("userID"));
                userQuestion.setAnswer(rs.getString("answer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userQuestion;
    }

    public List<UserQuestionsDTO> getAllUserQuestions() {
        List<UserQuestionsDTO> userQuestions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                UserQuestionsDTO userQuestion = new UserQuestionsDTO();
                userQuestion.setQuestionID(rs.getInt("questionID"));
                userQuestion.setEmail(rs.getString("email"));
                userQuestion.setUserID(rs.getInt("userID"));
                userQuestion.setAnswer(rs.getString("answer"));
                userQuestions.add(userQuestion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userQuestions;
    }

    public boolean updateUserQuestion(UserQuestionsDTO userQuestion) {
        boolean rowUpdated = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, userQuestion.getQuestionID());
            preparedStatement.setString(2, userQuestion.getEmail());
            preparedStatement.setInt(3, userQuestion.getUserID());
            preparedStatement.setString(4, userQuestion.getAnswer());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteUserQuestion(int id) {
        boolean rowDeleted = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}