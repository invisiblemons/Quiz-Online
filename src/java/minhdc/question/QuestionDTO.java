/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.question;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author MONS
 */
public class QuestionDTO implements Serializable{
    private int questionID;
    private String questionContent;
    private Date createDate;
    private String subjectID;
    private boolean status;

    public QuestionDTO(int questionID, String questionContent, Date createDate, String subjectID, boolean status) {
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.createDate = createDate;
        this.subjectID = subjectID;
        this.status = status;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}

