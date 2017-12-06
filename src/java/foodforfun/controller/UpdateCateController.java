/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodforfun.controller;

import foodforfun.dao.CategoryDAO;
import foodforfun.dto.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class UpdateCateController extends HttpServlet {

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
            String idRemove = request.getParameter("txtIDUpdate");
            String name = request.getParameter("txtCateName");
            String des = request.getParameter("description");
            System.out.println(idRemove);
            boolean isTrue = true;
            String faultMess = "";
            if (name.isEmpty()) {
                isTrue = false;
                faultMess += "Please input category name";
            }
            if (des.isEmpty()) {
                if(isTrue) {
                isTrue = false;
                faultMess += "Please input category description.";
                } else {
                faultMess += " and description.";
                }
            } else {
                faultMess += ".";
            }

            if (isTrue) {
                CategoryDAO tDAO = new CategoryDAO();
                int result = tDAO.updateCategory(Integer.parseInt(idRemove), name, des);
            } else {
                request.setAttribute("FAULTUPDATE", faultMess);
                CategoryDTO cateDTO = new CategoryDTO();
                cateDTO.setId(idRemove);
                cateDTO.setName(name);
                cateDTO.setDes(des);
                request.setAttribute("FAULTVALUE", cateDTO);
                
            }
            request.getRequestDispatcher("LoadAddCateController?PageID=1").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Bug at update cate controller");
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
