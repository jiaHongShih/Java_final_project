/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataObjects;

/**
 *
 * @author Josh Barrett
 */
public class UserQuestionsDTO {

    private int questionID;
    private String email;
    private int userID;
    private String answer;

    public UserQuestionsDTO() {
    }

    public UserQuestionsDTO(int questionID, String email, int userID, String answer) {
        this.questionID = questionID;
        this.email = email;
        this.userID = userID;
        this.answer = answer;
    }


    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
