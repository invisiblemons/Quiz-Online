/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.quiz;

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
public class QuizDAO implements Serializable {

    public boolean insertNewQuiz(QuizDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO quiz(subjectID, numberQuestion, timeLimit, dateTakeQuiz, timeTakeQuiz) "
                        + "VALUES(?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getSubjectID());
                ps.setInt(2, dto.getNumberQuestion());
                ps.setString(3, dto.getTimeLimit());
                ps.setDate(4, dto.getDateTakeQuiz());
                ps.setTime(5, dto.getTimeTakeQuiz());
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
    public int getLastRecord() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 quizID FROM quiz ORDER BY quizID DESC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("quizID");
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
        return -1;
    }
    
}
