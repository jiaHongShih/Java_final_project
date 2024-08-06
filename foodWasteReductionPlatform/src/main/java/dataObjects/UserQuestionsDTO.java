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

    private int id;
    private int questionID;
    private String email;
    private String answer;

    public UserQuestionsDTO() {
    }

    public UserQuestionsDTO(int id, int questionID, String email , String answer) {
        this.id = id;
        this.questionID = questionID;
        this.email = email;
        this.answer = answer;
    }

    public UserQuestionsDTO(int questionID, String email, String answer) {
        this.questionID = questionID;
        this.email = email;
        this.answer = answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
    
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
