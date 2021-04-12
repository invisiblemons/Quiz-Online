/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhdc.question.QuestionErr;

/**
 *
 * @author MONS
 */
@WebServlet(name = "ValidAddNewQuestionServlet", urlPatterns = {"/ValidAddNewQuestionServlet"})
public class ValidAddNewQuestionServlet extends HttpServlet {

    private final String ADD_NEW_QUESTION = "AddNewQuestionServlet";
    private final String ADD_NEW_PAGE = "addNewQuestion.jsp";
    
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
        String questionContent = request.getParameter("questionContent");
        String[] contentAnswer = request.getParameterValues("contentAnswer");
        String answerCorrect = request.getParameter("answerCorrect");
        String url  = ADD_NEW_QUESTION;
        try {
            boolean isError = false;
            QuestionErr err = new QuestionErr();
            if (questionContent.trim().length() <= 0) {
                isError = true;
                err.setQuestionContentEmpty("Question Content not empty!!!");
            }
            for (String string : contentAnswer) {
                int count = 0;
                for (String stringContentAnswer : contentAnswer) {
                    if (string.trim().compareToIgnoreCase(stringContentAnswer.trim()) == 0) {
                        count++;
                    }
                }
                if (string.trim().compareToIgnoreCase(answerCorrect.trim()) == 0) {
                    count++;
                }
                if (count >= 2) {
                    isError = true;
                    err.setDuplicateAnswer("Duplicate answer!!!");
                    
                }
            }
            if (isError) {
                url = ADD_NEW_PAGE;
                request.setAttribute("ANSWERDUPLI", contentAnswer);
                request.setAttribute("ERROR", err);
            }
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
