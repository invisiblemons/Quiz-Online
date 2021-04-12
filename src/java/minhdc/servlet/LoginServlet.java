/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.registration.RegistrationDAO;
import minhdc.registration.RegistrationDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String LOAD_QUESTION = "LoadQuestionAdminServlet";
    private final String HOME_PAGE_STUDENT = "homeStudent.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String url = LOGIN_PAGE;
        try {
            RegistrationDAO dao = new RegistrationDAO();
            if (email != null && password != null) {
                RegistrationDTO dto = dao.checkLogin(email, password);
                if (dto != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", dto);
                    if (dto.getRole().equals("admin")) {
                        url = LOAD_QUESTION;
                    } else {
                        url = HOME_PAGE_STUDENT;
                    }
                } else {
                    if (dao.checkDuplicateEmail(email)) {
                        request.setAttribute("FAILPASS", "The password you have entered is incorrect");
                    } else {
                        request.setAttribute("FAILEMAIL", "The email or phone number you entered doesn't match any of the accounts");
                    }
                }
            }
        } catch (NamingException ex) {
            log("LoginServlet_NamingEx: " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet_SQLEX: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("LoginServlet_NoSuchAlogrithmEx: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
