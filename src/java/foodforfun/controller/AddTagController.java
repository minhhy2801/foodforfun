/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodforfun.controller;

import foodforfun.dao.TagDAO;
import foodforfun.dto.TagDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AddTagController extends HttpServlet {

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
            String name = request.getParameter("txtTagName");
            String des = request.getParameter("description");
            boolean isTrue = true;
            String faultMess = "";
            if (name.isEmpty()) {
                isTrue = false;
                faultMess += "Please input tag name";
            }
            if (des.isEmpty()) {
                if (isTrue) {
                    isTrue = false;
                    faultMess += "Please input tag description.";
                } else {
                    faultMess += " and description.";
                }
            } else {
                faultMess += ".";
            }
            if (isTrue) {
                TagDAO tDAO = new TagDAO();
                //String creater = (String) request.getSession().getAttribute("ID");
                HttpSession session = request.getSession();
                String creater = (String)session.getAttribute("ID");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = new Date();
                String time = dateFormat.format(date);
                String[] arr = time.split(" ");
                String[] arr1 = arr[0].split("-");
                String[] arr2 = arr[1].split(":");
                Timestamp timet = new Timestamp(Integer.parseInt(arr1[0]) - 1900, Integer.parseInt(arr1[1]) - 1, Integer.parseInt(arr1[2]), Integer.parseInt(arr2[0]), Integer.parseInt(arr2[1]), Integer.parseInt(arr2[2]), 0);
                int result = tDAO.addTag(name, creater, timet, des);
                if (result == 1) {
                    request.getRequestDispatcher("LoadAddTagController?PageID=1").forward(request, response);
                } else {
                    System.out.println("Add sai roi");
                }
            } else {
                request.setAttribute("FAULTADD", faultMess);
                TagDTO tagDTO = new TagDTO();
                tagDTO.setName(name);
                tagDTO.setDes(des);
                request.setAttribute("FAULTADDVALUE", tagDTO);
                request.getRequestDispatcher("LoadAddTagController?PageID=1").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Fail at Add Tag controller!");
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
