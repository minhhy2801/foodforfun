/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodforfun.controller;

import foodforfun.dao.CommentDAO;
import foodforfun.dao.PostDAO;
import foodforfun.dto.CommentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kubin
 */
public class CommentController extends HttpServlet {

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
        String url = null;
        try {
            String txtComment = request.getParameter("txtComment");
            String postID = request.getParameter("postID");
            CommentDAO comtDAO = new CommentDAO();
            HttpSession session = request.getSession();
            String createrID = (String) session.getAttribute("ID");
            String commentID = request.getParameter("commentID");
            Date today = new Date();
            Timestamp dateCreate = new Timestamp(today.getTime());
            
            int result ;
            if (!commentID.isEmpty()) {
                CommentDTO comtDTO = new CommentDTO();
                comtDTO.setDetail(txtComment);
                comtDTO.setDateCreated(dateCreate);
                comtDTO.setCommentID(commentID);
                result = comtDAO.UpdateComment(comtDTO);
                
            } else {                 
                CommentDTO comtDTO = new CommentDTO(createrID, txtComment, dateCreate, postID);
                result = comtDAO.AddComment(comtDTO);
                
            }
            if (result > 0){
                    url = "MainController?action=ArticleDetail&PostID=" + postID;
            }
        } catch (Exception e) {
            log("Error at CommentController " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
