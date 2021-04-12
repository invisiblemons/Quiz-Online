/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.question;

import java.io.Serializable;

/**
 *
 * @author MONS
 */
public class QuestionErr implements Serializable{
    private String questionContentEmpty;
    private String duplicateAnswer;

    public QuestionErr() {
    }

    public String getQuestionContentEmpty() {
        return questionContentEmpty;
    }

    public void setQuestionContentEmpty(String questionContentEmpty) {
        this.questionContentEmpty = questionContentEmpty;
    }




    public String getDuplicateAnswer() {
        return duplicateAnswer;
    }

    public void setDuplicateAnswer(String duplicateAnswer) {
        this.duplicateAnswer = duplicateAnswer;
    }
    
}