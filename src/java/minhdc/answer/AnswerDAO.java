/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.answer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import minhdc.utilities.DBHelpers;

/**
 *
 * @author MONS
 */
public class AnswerDAO implements Serializable {

    private List<AnswerDTO> listAnswer;
    private List<AnswerDTO> listAnswerQuiz;

    public List<AnswerDTO> getListAnswer() {
        return listAnswer;
    }

    public List<AnswerDTO> getListAnswerQuiz() {
        return listAnswerQuiz;
    }

    public void loadAnswerDefault(int questionID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT answer.questionID, answerID, answerContent,questionContent, correct "
                        + "FROM answer JOIN question ON question.questionID = answer.questionID  "
                        + "WHERE answer.questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (this.listAnswer == null) {
                        this.listAnswer = new ArrayList<>();
                    }
                    listAnswer.add(new AnswerDTO(rs.getInt("answerID"), rs.getInt("questionID"),
                            rs.getString("answerContent"), rs.getString("questionContent"), rs.getBoolean("correct")));
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

    }

    public void loadAnswerQuiz(int questionID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT answer.questionID, answerID, answerContent,questionContent, correct "
                        + "FROM answer JOIN question ON question.questionID = answer.questionID  "
                        + "WHERE answer.questionID = ?";//ORDER BY NEWID()
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (this.listAnswerQuiz == null) {
                        this.listAnswerQuiz = new ArrayList<>();
                    }
                    listAnswerQuiz.add(new AnswerDTO(rs.getInt("answerID"), rs.getInt("questionID"),
                            rs.getString("answerContent"), rs.getString("questionContent"), false));
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

    }

    public boolean updateAnswer(int answerID, int questionID, String answerContent, boolean correct) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE answer SET answerContent = ?, correct = ? "
                        + "WHERE answerID = ? AND questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, answerContent);
                ps.setBoolean(2, correct);
                ps.setInt(3, answerID);
                ps.setInt(4, questionID);
                int rs = ps.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }

    public boolean addNewAnswer(int questionID, String answerContent, boolean correct) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO answer(questionID, answerContent, correct) "
                        + "VALUES(?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);
                ps.setString(2, answerContent);
                ps.setBoolean(3, correct);
                int rs = ps.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }

    public boolean checkAnswerCorrect(int answerID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT correct FROM answer WHERE answerID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, answerID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getBoolean("correct");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }
    
}
