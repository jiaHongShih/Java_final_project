/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.QuestionsDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh Barrett
 */
public class QuestionsDAO {

    private static final String URL = "jdbc:your_database_url";
    private static final String USER = "your_database_user";
    private static final String PASSWORD = "your_database_password";

    private static final String INSERT_QUERY = "INSERT INTO questions (questionDescription) VALUES (?)";
    private static final String SELECT_QUERY = "SELECT * FROM questions WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE questions SET questionDescription = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM questions WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM questions";

    public void addQuestion(QuestionsDTO question) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, question.getQuestionDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public QuestionsDTO getQuestion(int id) {
        QuestionsDTO question = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                question = new QuestionsDTO();
                question.setId(rs.getInt("id"));
                question.setQuestionDescription(rs.getString("questionDescription"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    public List<QuestionsDTO> getAllQuestions() {
        List<QuestionsDTO> questions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                QuestionsDTO question = new QuestionsDTO();
                question.setId(rs.getInt("id"));
                question.setQuestionDescription(rs.getString("questionDescription"));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public boolean updateQuestion(QuestionsDTO question) {
        boolean rowUpdated = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, question.getQuestionDescription());
            preparedStatement.setInt(2, question.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteQuestion(int id) {
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