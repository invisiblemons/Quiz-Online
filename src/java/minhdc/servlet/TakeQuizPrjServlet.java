/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.answer.AnswerDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "TakeQuizPrjServlet", urlPatterns = {"/TakeQuizPrjServlet"})
public class TakeQuizPrjServlet extends HttpServlet {

    private final String LOAD_ANSWER_QUIZ = "LoadAnswerQuizServlet";
    private final int FIFTEEN_MINUTES = 60 * 15;

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
        String userChoose = request.getParameter("userChoose");
        String key = request.getParameter("key");
        String page = request.getParameter("pageIndex");
        String btAction = request.getParameter("btAction");
        int pageIndex = 1;
        if (page != null) {
            pageIndex = Integer.parseInt(page);
        }
        String timeRemaining = request.getParameter("timeRemaining");
        int timeout = FIFTEEN_MINUTES;
        if (timeRemaining != null) {
            timeout = Integer.parseInt(timeRemaining);
        }
        String url = LOAD_ANSWER_QUIZ;
        try {
            Map<Integer, List<AnswerDTO>> mapQuiz = (Map<Integer, List<AnswerDTO>>) session.getAttribute("MAPQUIZ");
            if (userChoose != null) {
                int answerIDUserChoose = Integer.parseInt(userChoose);
                int questionID = Integer.parseInt(key);
                for (Map.Entry<Integer, List<AnswerDTO>> entry : mapQuiz.entrySet()) {
                    List<AnswerDTO> value = entry.getValue();
                    for (AnswerDTO answerDTO : value) {
                        if (answerDTO.getQuestionID() == questionID) {
                            if (answerIDUserChoose == answerDTO.getAnswerID()) {
                                answerDTO.setCorrect(true);
                            } else {
                                answerDTO.setCorrect(false);
                            }
                        }

                    }

                }
                session.setAttribute("MAPQUIZ", mapQuiz);
            }
            session.setAttribute("TIMEOUT", timeout);
            if (btAction != null) {
                if (btAction.equals("FinishQuizServlet")) {
                    url = btAction;
                }
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
