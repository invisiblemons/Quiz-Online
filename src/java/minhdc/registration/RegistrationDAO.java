/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.registration;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
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
public class RegistrationDAO implements Serializable {

    public boolean registerAccount(RegistrationDTO dto) throws NamingException, SQLException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO registration(email, name, password, role, status) "
                        + "VALUES(?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getEmail());
                ps.setString(2, dto.getName());
                ps.setString(3, DBHelpers.encryptedPasswordSHA256(dto.getPassword()));
                ps.setString(4, dto.getRole());
                ps.setString(5, dto.getStatus());
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

    public RegistrationDTO checkLogin(String email, String password) throws NamingException, SQLException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT email, name, password, role, status "
                        + "FROM registration "
                        + "WHERE email = ? AND password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, DBHelpers.encryptedPasswordSHA256(password));
                rs = ps.executeQuery();
                if (rs.next()) {
                    return new RegistrationDTO(rs.getString("email"),
                            rs.getString("name"), rs.getString("password"),
                            rs.getString("role"), rs.getString("status"));
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
        return null;
    }

    public boolean checkDuplicateEmail(String email) throws NamingException, SQLException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT email, name, password, role, status "
                        + "FROM registration "
                        + "WHERE email = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
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

}
