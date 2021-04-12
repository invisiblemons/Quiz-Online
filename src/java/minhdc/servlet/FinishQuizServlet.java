/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import minhdc.quizdetail.QuizDetailDAO;
import minhdc.result.ResultDAO;
import minhdc.result.ResultDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "FinishQuizServlet", urlPatterns = {"/FinishQuizServlet"})
public class FinishQuizServlet extends HttpServlet {

    private final String RESULT_PAGE = "result.jsp";

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
        HttpSession session = request.getSession();
        String quizID = (String) session.getAttribute("QUIZID");

        Map<Integer, List<AnswerDTO>> mapQuiz = (Map<Integer, List<AnswerDTO>>) session.getAttribute("MAPQUIZ");
        String email = request.getParameter("email");
        String totalPage = request.getParameter("totalPage");
        try {
            AnswerDAO answerDAO = new AnswerDAO();
            ResultDAO resultDAO = new ResultDAO();
            QuizDetailDAO quizDetailDAO = new QuizDetailDAO();

            int correctAnswer = 0;
            for (Map.Entry<Integer, List<AnswerDTO>> entry : mapQuiz.entrySet()) {
                List<AnswerDTO> value = entry.getValue();
                for (AnswerDTO answerDTO : value) {
                    if (answerDTO.isCorrect()) {
                        quizDetailDAO.updateQuizDetail(Integer.parseInt(quizID),answerDTO.getQuestionID(),
                                answerDTO.getAnswerID());
                        if (answerDAO.checkAnswerCorrect(answerDTO.getAnswerID())) {
                            correctAnswer++;
                        }
                    }
                }
            }
            int totalQuestion = Integer.parseInt(totalPage);
            float scoreOfAnAnswerCorrect = 10 / totalQuestion;
            ResultDTO resultDTO = new ResultDTO(resultDAO.getLastRecord(email),
                    0, email, scoreOfAnAnswerCorrect * correctAnswer, correctAnswer);
            resultDAO.finishResult(resultDTO);
            request.setAttribute("RESULT", resultDTO);
        } catch (SQLException ex) {
            log("FinishQuizServlet_SQLEx: " + ex.getMessage());
        } catch (NamingException ex) {
            log("FinishQuizServlet_NamingEx: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(RESULT_PAGE);
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
