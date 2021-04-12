/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.result;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author MONS
 */
public class ResultDTO implements Serializable {

    private int resultID;
    private int quizID;
    private String email;
    private float score;
    private int numberCorrect;
    private String subjectID;

    private Date dateTakeQuiz;
    private Time timeTakeQuiz;
    private int numberQuestion;

    public ResultDTO(int resultID, int quizID, String email, float score, int numberCorrect) {
        this.resultID = resultID;
        this.quizID = quizID;
        this.email = email;
        this.score = score;
        this.numberCorrect = numberCorrect;
    }

    public ResultDTO(String email, String subjectID, float score, int numberCorrect, Date dateTakeQuiz, Time timeTakeQuiz, int numberQuestion) {
        this.email = email;
        this.subjectID = subjectID;
        this.score = score;
        this.numberCorrect = numberCorrect;
        this.dateTakeQuiz = dateTakeQuiz;
        this.timeTakeQuiz = timeTakeQuiz;
        this.numberQuestion = numberQuestion;
    }

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public Date getDateTakeQuiz() {
        return dateTakeQuiz;
    }

    public void setDateTakeQuiz(Date dateTakeQuiz) {
        this.dateTakeQuiz = dateTakeQuiz;
    }

    public Time getTimeTakeQuiz() {
        return timeTakeQuiz;
    }

    public void setTimeTakeQuiz(Time timeTakeQuiz) {
        this.timeTakeQuiz = timeTakeQuiz;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

}
