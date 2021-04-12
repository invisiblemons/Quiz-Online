/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.question;

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
public class QuestionDAO implements Serializable {

    private List<QuestionDTO> listQuestion;
    private List<QuestionDTO> listQuestionQuiz;

    private final int PAGE_PAGING = 5;

    public List<QuestionDTO> getListQuestionQuiz() {
        return listQuestionQuiz;
    }

    public List<QuestionDTO> getListQuestion() {
        return listQuestion;
    }

    public void loadQuestionDefault(int pageIndex) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (pageIndex > 0) {
                pageIndex = (pageIndex - 1) * PAGE_PAGING;
            }
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT questionID, questionContent, createDate, subjectID, status "
                        + "FROM question ORDER BY questionID "
                        + "OFFSET ? ROWS FETCH NEXT " + PAGE_PAGING + " ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, pageIndex);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (this.listQuestion == null) {
                        this.listQuestion = new ArrayList<>();
                    }
                    listQuestion.add(new QuestionDTO(rs.getInt("questionID"),
                            rs.getString("questionContent"), rs.getDate("createDate"), 
                            rs.getString("subjectID"), rs.getBoolean("status")));
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

    public int countTotalPageDefault() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(questionID) AS 'totalRecord' FROM question";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return (int) Math.ceil(rs.getInt("totalRecord") / (double) PAGE_PAGING);
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

    public void loadQuestionByName(int pageIndex, String questionName) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (pageIndex > 0) {
                pageIndex = (pageIndex - 1) * PAGE_PAGING;
            }
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT questionID, questionContent, createDate, subjectID, status "
                        + "FROM question WHERE questionContent LIKE ? ORDER BY questionID "
                        + "OFFSET ? ROWS FETCH NEXT " + PAGE_PAGING + " ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + questionName + "%");
                ps.setInt(2, pageIndex);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (this.listQuestion == null) {
                        this.listQuestion = new ArrayList<>();
                    }
                    listQuestion.add(new QuestionDTO(rs.getInt("questionID"),
                            rs.getString("questionContent"), rs.getDate("createDate"), 
                            rs.getString("subjectID"), rs.getBoolean("status")));
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

    public int countTotalPageByName(String questionName) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(questionID) AS 'totalRecord' "
                        + "FROM question WHERE questionContent LIKE ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + questionName + "%");
                rs = ps.executeQuery();
                if (rs.next()) {
                    return (int) Math.ceil(rs.getInt("totalRecord") / (double) PAGE_PAGING);
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

    public void loadQuestionBySubjectID(int pageIndex, String subjectID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (pageIndex > 0) {
                pageIndex = (pageIndex - 1) * PAGE_PAGING;
            }
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT questionID, questionContent, createDate, subjectID, status "
                        + "FROM question WHERE subjectID = ? ORDER BY questionID "
                        + "OFFSET ? ROWS FETCH NEXT " + PAGE_PAGING + " ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                ps.setInt(2, pageIndex);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (this.listQuestion == null) {
                        this.listQuestion = new ArrayList<>();
                    }
                    listQuestion.add(new QuestionDTO(rs.getInt("questionID"),
                            rs.getString("questionContent"), rs.getDate("createDate"), 
                            rs.getString("subjectID"), rs.getBoolean("status")));
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

    public int countTotalPageBySubjectID(String subjectID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(questionID) AS 'totalRecord' "
                        + "FROM question WHERE subjectID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return (int) Math.ceil(rs.getInt("totalRecord") / (double) PAGE_PAGING);
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

    public void loadQuestionByStatus(int pageIndex, boolean status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (pageIndex > 0) {
                pageIndex = (pageIndex - 1) * PAGE_PAGING;
            }
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT questionID, questionContent, createDate, subjectID, status "
                        + "FROM question WHERE status = ? ORDER BY questionID "
                        + "OFFSET ? ROWS FETCH NEXT " + PAGE_PAGING + " ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, status);
                ps.setInt(2, pageIndex);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (this.listQuestion == null) {
                        this.listQuestion = new ArrayList<>();
                    }
                    listQuestion.add(new QuestionDTO(rs.getInt("questionID"),
                            rs.getString("questionContent"), rs.getDate("createDate"), 
                            rs.getString("subjectID"), rs.getBoolean("status")));
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

    public int countTotalPageByStatus(boolean status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(questionID) AS 'totalRecord' "
                        + "FROM question WHERE status = ? ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, status);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return (int) Math.ceil(rs.getInt("totalRecord") / (double) PAGE_PAGING);
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

    public boolean deleteQuestion(int questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE question SET status = ? "
                        + "WHERE questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, false);
                ps.setInt(2, questionID);
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
    public boolean updateStatusAndSubjectQuestion(int questionID, String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE question SET status = ? AND subjectID = ? "
                        + "WHERE questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setString(2, subjectID);
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

    public boolean updateQuestion(QuestionDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE question SET questionContent = ?, subjectID = ?, status = ? "
                        + "WHERE questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getQuestionContent());
                ps.setString(2, dto.getSubjectID());
                ps.setBoolean(3, dto.isStatus());
                ps.setInt(4, dto.getQuestionID());
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

    public boolean addNewQuestion(QuestionDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO question(questionContent, createDate, subjectID, status) "
                        + "VALUES(?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getQuestionContent());
                ps.setDate(2, dto.getCreateDate());
                ps.setString(3, dto.getSubjectID());
                ps.setBoolean(4, dto.isStatus());
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

    public boolean checkDuplicateQuestion(String questionContent) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select questionID from question "
                        + "WHERE questionContent = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionContent);
                rs = ps.executeQuery();
                if (rs.next()) {
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
            if (rs != null) {
                rs.close();
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
                String sql = "SELECT TOP 1 questionID FROM question ORDER BY questionID DESC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("questionID");
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

    public void loadQuestionQuiz(String subjectID, int numberQuestion) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP " + numberQuestion + " questionID,questionContent,createDate,subjectID,status "
                        + "FROM question WHERE subjectID = ? AND status = ? ORDER BY NEWID()";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                ps.setBoolean(2, true);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (this.listQuestionQuiz == null) {
                        this.listQuestionQuiz = new ArrayList<>();
                    }
                    listQuestionQuiz.add(new QuestionDTO(rs.getInt("questionID"),
                            rs.getString("questionContent"), rs.getDate("createDate"), 
                            rs.getString("subjectID"), rs.getBoolean("status")));
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
}
