/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.quizdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import minhdc.utilities.DBHelpers;

/**
 *
 * @author MONS
 */
public class QuizDetailDAO implements Serializable {

    public boolean insertNewQuizDetails(int quizID, int questionID, int answerID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO quizDetail(quizID, questionID, answerID) "
                        + "VALUES(?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, quizID);
                ps.setInt(2, questionID);
                ps.setInt(3, answerID);
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

    public boolean updateQuizDetail(int quizID, int questionID, int answerID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE quizDetail SET answerID = ? "
                        + "WHERE quizID  = ? AND questionID = ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, answerID);
                ps.setInt(2, quizID);
                ps.setInt(3, questionID);
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

    public boolean checkQuizAlreadyDone(int questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT questionID FROM quizDetail "
                        + "WHERE questionID = ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
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
