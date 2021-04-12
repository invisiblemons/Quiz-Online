/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.quiz;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author MONS
 */
public class QuizDTO implements Serializable {

    private int quizID;
    private String subjectID;
    private int numberQuestion;
    private String timeLimit;
    private Date dateTakeQuiz;
    private Time timeTakeQuiz;

    public QuizDTO(int quizID, String subjectID, int numberQuestion, String timeLimit, Date dateTakeQuiz, Time timeTakeQuiz) {
        this.quizID = quizID;
        this.subjectID = subjectID;
        this.numberQuestion = numberQuestion;
        this.timeLimit = timeLimit;
        this.dateTakeQuiz = dateTakeQuiz;
        this.timeTakeQuiz = timeTakeQuiz;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
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
    
}
