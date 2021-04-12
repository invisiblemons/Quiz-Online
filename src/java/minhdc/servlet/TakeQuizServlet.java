/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.answer.AnswerDAO;
import minhdc.answer.AnswerDTO;
import minhdc.question.QuestionDAO;
import minhdc.question.QuestionDTO;
import minhdc.quiz.QuizDAO;
import minhdc.quiz.QuizDTO;
import minhdc.quizdetail.QuizDetailDAO;
import minhdc.result.ResultDAO;
import minhdc.result.ResultDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "TakeQuizServlet", urlPatterns = {"/TakeQuizServlet"})
public class TakeQuizServlet extends HttpServlet {

    private final String LOAD_QUIZ_PRJ = "TakeQuizPrjServlet";
    private final int FIFTEEN_MINUTES = 60 * 15;
    private final int NUMBER_QUESTION = 10;

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
        String subject = request.getParameter("subject");
        String email = request.getParameter("email");
        HttpSession session = request.getSession();

        int timeout = FIFTEEN_MINUTES;
        int minutes = timeout / 60;
        int seconds = timeout % 60;
        try {
            QuestionDAO dao = new QuestionDAO();
            dao.loadQuestionQuiz(subject, NUMBER_QUESTION);
            List<QuestionDTO> listQuestionQuiz = dao.getListQuestionQuiz();
            Map<Integer, List<AnswerDTO>> mapQuiz = new HashMap<>();
            if (listQuestionQuiz != null) {
                QuizDAO quizDAO = new QuizDAO();
                java.util.Date date = new java.util.Date();
                Date dateTakeQuiz = new Date(date.getTime());
                Time timeTakeQuiz = new Time(date.getTime());
                if (quizDAO.insertNewQuiz(new QuizDTO(0, subject, NUMBER_QUESTION,
                        String.valueOf(minutes) + ":" + String.valueOf(seconds), dateTakeQuiz, timeTakeQuiz))) {
                    
                    ResultDAO resultDAO = new ResultDAO();
                    resultDAO.insertNewResult(new ResultDTO(0, quizDAO.getLastRecord(), email, 0, 0));
                }
                
                int quizID = quizDAO.getLastRecord();
                session.setAttribute("QUIZID", String.valueOf(quizID));
                
                QuizDetailDAO quizDetailDAO = new QuizDetailDAO();
                    for (int i = 0; i < listQuestionQuiz.size(); i++) {
                    int page = i + 1;
                    AnswerDAO ansDao = new AnswerDAO();
                    ansDao.loadAnswerQuiz(listQuestionQuiz.get(i).getQuestionID());
                    quizDetailDAO.insertNewQuizDetails(quizID, listQuestionQuiz.get(i).getQuestionID(), 0);
                    mapQuiz.put(page, ansDao.getListAnswerQuiz());
                }
                session.setAttribute("MAPQUIZ", mapQuiz);
            }
            session.setAttribute("TIMEOUT", timeout);
            session.setAttribute("TOTALPAGE", NUMBER_QUESTION);
        } catch (NamingException ex) {
            log("TakeQuizServlet_NamingEx: " + ex.getMessage());
        } catch (SQLException ex) {
            log("TakeQuizServlet_SQLEx: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(LOAD_QUIZ_PRJ);
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
