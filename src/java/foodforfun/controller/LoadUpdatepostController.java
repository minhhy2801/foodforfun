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
import foodforfun.dto.PlaceDTO;
import foodforfun.dto.PostDTO;
import foodforfun.dto.TagDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class LoadUpdatepostController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
           PostDAO pdao = new PostDAO();
           String value = request.getParameter("PostId");
           int postId = Integer.parseInt(value);
            PostDTO postDTO = pdao.getPostByPostID(postId);
            request.getSession().setAttribute("postDTO", postDTO);
            System.out.println(postDTO.getImgMain());
            System.out.println(postDTO.getPlaceID());
            PlaceDTO placeDTO = pdao.getPlace(postDTO.getPlaceID());
            //System.out.println(placeDTO.getPhone() + ", " + placeDTO.getPlaceName() + ", " + placeDTO.getAddress());
            request.getSession().setAttribute("placeDTO", placeDTO);
            CategoryDAO cateDAO = new CategoryDAO();
            TagDAO tagDAO = new TagDAO();
//            TagDTO tagDTO = new TagDTO();
//            CategoryDTO cateDTO = new CategoryDTO();
            List<TagDTO> listTag = tagDAO.getAllTagName();
            request.getSession().setAttribute("tagList", listTag);
            List<CategoryDTO> listCate = cateDAO.getAllCateName();
            request.getSession().setAttribute("cateList", listCate);
            List<TagDTO> listTagOfPost = tagDAO.getTagOfPost(postId);//postID
            System.out.println(listTagOfPost.size() +","+listTag.size());
            for (int i = 0; i < listTag.size(); i++) {
                for (int j = 0; j < listTagOfPost.size(); j++) {
                    if(listTagOfPost.get(j).getId().equals(listTag.get(i).getId())) {
                        listTag.get(i).setChecked(true);
                        break;
                    }
                }
            }
            List<CategoryDTO> listCateOfPost = cateDAO.getCateOfPost(postId);//postID
            System.out.println(listCateOfPost.size() +","+listCate.size());
            for (int i = 0; i < listCate.size(); i++) {
                for (int j = 0; j < listCateOfPost.size(); j++) {
                    if(listCateOfPost.get(j).getId().equals(listCate.get(i).getId())) {
                        listCate.get(i).setChecked(true);
                        break;
                    }
                }
            }
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = postDTO.getDateAccepted();
            String dateT = dateFormat.format(date);
            request.setAttribute("POSTID", postId); //test
            request.getSession().setAttribute("DATE", dateT);
            request.getRequestDispatcher("UpdatePost.jsp").forward(request, response);
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
