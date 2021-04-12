/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhdc.answer.AnswerDAO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "UpdateAnswerServlet", urlPatterns = {"/UpdateAnswerServlet"})
public class UpdateAnswerServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
//    private final String UPDATE_ANSWER = "updateAnswer";

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

        int questionID = Integer.parseInt(request.getParameter("key"));
        String answerContent[] = request.getParameterValues("answerContent");
        String answerCorrect = request.getParameter("answerCorrect");

        String actionLoad = request.getParameter("actionLoad");
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String searchQuestionNameValue = request.getParameter("searchQuestionNameValue");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String subjectID = request.getParameter("subjectID");

        String url = ERROR_PAGE;
        try {
            AnswerDAO dao = new AnswerDAO();
            if (answerContent != null) {
                int answerID = (questionID - 1) * 4 + 1;
                for (String content : answerContent) {
                    dao.updateAnswer(answerID, questionID, content, false);
                    answerID++;
                }
                dao.updateAnswer(answerID+3, questionID, answerCorrect, true);
                url = actionLoad
                        + "?pageIndex=" + pageIndex
                        + "&searchQuestionNameValue=" + searchQuestionNameValue
                        + "&status=" + status
                        + "&subjectID=" + subjectID;
            }
        } catch (NamingException ex) {
            log("UpdateAnswerServlet_NamingEx: " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateAnswerServlet_SQLEx: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
