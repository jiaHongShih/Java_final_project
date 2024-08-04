/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataObjects;

/**
 *
 * @author Josh Barrett
 */
public class QuestionsDTO {
    private int id;
    private String questionDescription;


    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }
        public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
