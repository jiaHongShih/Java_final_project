/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.UserQuestionsDTO;
import functions.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh Barrett
 */
public class UserQuestionsDAO {

    private static Connection connection = null;
    private static PreparedStatement prepQuery = null;
    private static ResultSet rs = null;

    private static final String INSERT_QUERY = "INSERT INTO user_questions (questionID, email, answer) VALUES (?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM user_questions WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE user_questions SET questionID = ?, email = ?, answer = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM user_questions WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM user_questions";

    public void addUserQuestion(UserQuestionsDTO userQuestion) {
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(INSERT_QUERY);
            prepQuery.setInt(1, userQuestion.getQuestionID());
            prepQuery.setString(2, userQuestion.getEmail());
            prepQuery.setString(3, userQuestion.getAnswer());
            prepQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
    }

    public UserQuestionsDTO getUserQuestion(int id) {
        UserQuestionsDTO userQuestion = null;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_QUERY);
            prepQuery.setInt(1, id);
            rs = prepQuery.executeQuery();

            if (rs.next()) {
                userQuestion = new UserQuestionsDTO();
                userQuestion.setQuestionID(rs.getInt("questionID"));
                userQuestion.setEmail(rs.getString("email"));
                userQuestion.setAnswer(rs.getString("answer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return userQuestion;
    }

    public List<UserQuestionsDTO> getAllUserQuestions() {
        List<UserQuestionsDTO> userQuestions = new ArrayList<>();
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ALL_QUERY);
            rs = prepQuery.executeQuery();

            while (rs.next()) {
                UserQuestionsDTO userQuestion = new UserQuestionsDTO();
                userQuestion.setQuestionID(rs.getInt("questionID"));
                userQuestion.setEmail(rs.getString("email"));
                userQuestion.setAnswer(rs.getString("answer"));
                userQuestion.setId(rs.getInt("id"));
                userQuestions.add(userQuestion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return userQuestions;
    }

    public boolean updateUserQuestion(UserQuestionsDTO userQuestion) {
        boolean rowUpdated = false;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(UPDATE_QUERY);
            prepQuery.setInt(1, userQuestion.getQuestionID());
            prepQuery.setString(2, userQuestion.getEmail());
            prepQuery.setString(3, userQuestion.getAnswer());
            prepQuery.setInt(4, userQuestion.getId());

            rowUpdated = prepQuery.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return rowUpdated;
    }

    public boolean deleteUserQuestion(int id) {
        boolean rowDeleted = false;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(DELETE_QUERY);
            prepQuery.setInt(1, id);
            rowDeleted = prepQuery.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return rowDeleted;
    }
}
