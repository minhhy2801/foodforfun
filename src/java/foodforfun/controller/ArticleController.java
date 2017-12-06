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
import foodforfun.dto.PostDTO;
import foodforfun.dto.TagDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kubin
 */
public class ArticleController extends HttpServlet {

    private static int page = 1;
    private static String pageStyle = "";
    private static String clickObj = "";

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
        String url = "Article.jsp";
        try {
            PostDAO dao = new PostDAO();
            int curentPage = Integer.parseInt(request.getParameter("currentPage"));
            if (curentPage == 0) {
                page = 1;
            } else{
                page = page + curentPage;
            }
            //page = (page / 2) + 1;

            CategoryDAO cateDAO = new CategoryDAO();
            TagDAO tagDAO = new TagDAO();
            String flag = request.getParameter("flagArticle");
            if (!flag.equals("ChangePage")){
                pageStyle = flag; }
            String clickID;
            List<PostDTO> article;
            List<PostDTO> articleNext;
            if (pageStyle.equals("TitleClick")) {
                article = dao.get3PostForArticlePage(page);
                articleNext = dao.get3PostForArticlePage(page + 1);
                if (article != null) {
                    request.setAttribute("ARTICLE", article);
                }
            } else if (pageStyle.equals("CateClick")){
                clickID = request.getParameter("cateID");
                if (clickID != null){
                clickObj = clickID; }
                System.out.println("click obj = " + clickObj);
                article = dao.get3PostForArticlePageByCategory(page, clickObj);
                articleNext = dao.get3PostForArticlePageByCategory((page + 1), clickObj);
                if (article != null) {
                    request.setAttribute("ARTICLE", article);
                }
            } else {
                clickID = request.getParameter("tagID");
                if (clickID != null){
                clickObj = clickID; }
                article = dao.get3PostForArticlePageByTag(page, clickObj);
                articleNext = dao.get3PostForArticlePageByTag(page + 1, clickObj);
                if (article != null) {
                    request.setAttribute("ARTICLE", article);
                }
            }
            List<PostDTO> topPost = dao.topPostCountByLike();
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
            request.setAttribute("PREPAGE", page);
            if (article.size() < 3){
                request.setAttribute("NEXTPAGE", "finalPage");
            }
            if (articleNext.isEmpty()){
                request.setAttribute("NEXTPAGEVIEW", "emptyPage");
                System.out.println("is empty ");
            }
            url = "Article.jsp";
        } catch (Exception e) {
            log("ERROR at ArticleController " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            return;
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
