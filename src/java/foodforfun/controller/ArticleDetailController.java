/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodforfun.controller;

import foodforfun.dao.CategoryDAO;
import foodforfun.dao.PostDAO;
import foodforfun.dao.TagDAO;
import foodforfun.dto.CategoryDTO;
import foodforfun.dto.CommentDTO;
import foodforfun.dto.PostDTO;
import foodforfun.dto.TagDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kubin
 */
public class ArticleDetailController extends HttpServlet {

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
            String postId = request.getParameter("PostID");
            PostDAO postDAO = new PostDAO();
            TagDAO tagDAO = new TagDAO();
            CategoryDAO cateDAO = new CategoryDAO();
            PostDTO detail = postDAO.ViewDetailPost_ArticleDetail(postId);
            if (detail != null){
                request.setAttribute("POSTID", postId);
                request.setAttribute("DETAIL", detail);
            }
            int likeOfPost = postDAO.countLikeOfPost(postId);
            request.setAttribute("LIKEOFPOST", likeOfPost);
            int numOfComment = postDAO.countComment(postId);
            request.setAttribute("CMTNUM", numOfComment);
            List<CommentDTO> listCmt = postDAO.getPostComment(postId);
            List<TagDTO> listTag = tagDAO.getTagByPost(postId);            
            request.setAttribute("LISTCMT", listCmt);
            request.setAttribute("LISTTAG", listTag);
            HttpSession session = request.getSession();
            String accountID = (String)session.getAttribute("ID");
            boolean isLike = postDAO.getIsLike(accountID, postId);
            request.setAttribute("ISLIKE", isLike);
            List<PostDTO> topPost = postDAO.topPostCountByLike();
            if (topPost != null) {
                request.setAttribute("TOPPOST", topPost);
            }
            List<CategoryDTO> topCate = cateDAO.getTopCategoryUsed();
            if (topCate != null) {
                request.setAttribute("TOPCATE", topCate);
            }
            List<TagDTO> topTag = tagDAO.getTopTagUsed();
            if (topTag != null) {
                request.setAttribute("TOPTAG", topTag);
            }
            url = "ArticleDetail.jsp";
        } catch (Exception e) {
            log("ERROR at ArticleDetailController " + e.getMessage());
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
