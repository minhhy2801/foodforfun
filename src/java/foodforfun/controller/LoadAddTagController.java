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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class LoadAddTagController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static int page;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            TagDAO tDAO = new TagDAO();
            
            String flag = request.getParameter("PageID");
            page = Integer.parseInt(flag);
            request.setAttribute("CURPAGE", page);
            List<TagDTO> nextResult = tDAO.getAllTagAndCount(page + 1);
            request.setAttribute("NEXTRESULT", nextResult.size());
            
            List<TagDTO> list = tDAO.getAllTagAndCount(page);
            List<TagDTO> listZero = tDAO.getAllTag(page);
            boolean check;
            int[] arr = new int[listZero.size()];
            
            int count = 0;
            for (int i = 0; i < listZero.size(); i++) {
                check = false;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getId().equals(listZero.get(i).getId())) {
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    arr[count] = i;
                    count++;
                }
            }
            for (int i = 0; i < count; i++) {
                TagDTO newDTO = new TagDTO();
                newDTO.setId(listZero.get(arr[i]).getId());
                newDTO.setName(listZero.get(arr[i]).getName());
                newDTO.setDes(listZero.get(arr[i]).getDes());
                newDTO.setCount(0);
                newDTO.setCreaterID(listZero.get(arr[i]).getCreaterID());
                newDTO.setChecked(false);
                list.add(newDTO);
            }
            request.setAttribute("TAGLIST", list);
            request.getRequestDispatcher("AddTag.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Bug at loadtag controller");
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
