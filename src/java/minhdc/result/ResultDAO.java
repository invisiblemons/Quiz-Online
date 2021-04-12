/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.result;

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
public class ResultDAO {

    private List<ResultDTO> listResult;

    public List<ResultDTO> getListResult() {
        return listResult;
    }

    public void loadResultBySubject(String subjectID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT quiz.dateTakeQuiz, quiz.timeTakeQuiz, quiz.numberQuestion, "
                        + "result.email, result.numberCorrect, result.score "
                        + "FROM result JOIN quiz ON quiz.quizID = result.resultID "
                        + "JOIN subject ON subject.subjectID = quiz.subjectID "
                        + "WHERE quiz.subjectID = ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (this.listResult == null) {
                        this.listResult = new ArrayList<>();
                    }
                    listResult.add(new ResultDTO(rs.getString("email"), subjectID, rs.getFloat("score"),
                            rs.getInt("numberCorrect"), rs.getDate("dateTakeQuiz"),
                            rs.getTime("timeTakeQuiz"), rs.getInt("numberQuestion")));
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
    public void loadResultByEmail(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT quiz.dateTakeQuiz,quiz.subjectID, quiz.timeTakeQuiz, quiz.numberQuestion, "
                        + "result.email, result.numberCorrect, result.score "
                        + "FROM result JOIN quiz ON quiz.quizID = result.resultID "
                        + "JOIN subject ON subject.subjectID = quiz.subjectID "
                        + "WHERE result.email = ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (this.listResult == null) {
                        this.listResult = new ArrayList<>();
                    }
                    listResult.add(new ResultDTO(rs.getString("email"), rs.getString("subjectID"), rs.getFloat("score"),
                            rs.getInt("numberCorrect"), rs.getDate("dateTakeQuiz"),
                            rs.getTime("timeTakeQuiz"), rs.getInt("numberQuestion")));
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

    public boolean insertNewResult(ResultDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO result(quizID, email, score, numberCorrect) "
                        + "VALUES(?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, dto.getQuizID());
                ps.setString(2, dto.getEmail());
                ps.setFloat(3, dto.getScore());
                ps.setInt(4, dto.getNumberCorrect());
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

    public boolean finishResult(ResultDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE result SET score = ?, numberCorrect = ? "
                        + "WHERE resultID = ? AND email = ? ";
                ps = con.prepareStatement(sql);
                ps.setFloat(1, dto.getScore());
                ps.setInt(2, dto.getNumberCorrect());
                ps.setInt(3, dto.getResultID());
                ps.setString(4, dto.getEmail());
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

    public int getLastRecord(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 resultID FROM result "
                        + "WHERE email = ? ORDER BY resultID DESC";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("resultID");
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

