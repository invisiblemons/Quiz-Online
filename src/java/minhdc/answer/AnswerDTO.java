/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.answer;

import java.io.Serializable;

/**
 *
 * @author MONS
 */
public class AnswerDTO implements Serializable{
    private int answerID;
    private int questionID;
    private String answerContent;
    private String questionContent;
    private boolean correct;

    public AnswerDTO(int answerID, int questionID, String answerContent, String questionContent, boolean correct) {
        this.answerID = answerID;
        this.questionID = questionID;
        this.answerContent = answerContent;
        this.questionContent = questionContent;
        this.correct = correct;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
}
