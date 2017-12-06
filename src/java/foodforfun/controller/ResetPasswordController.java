/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodforfun.controller;

import foodforfun.dao.AccountDAO;
import foodforfun.dto.AccountDTO;
import foodforfun.errorobj.AccountError;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kubin
 */
public class ResetPasswordController extends HttpServlet {
    private final String resetPass = "ForgotPass.jsp";
    private final String login = "Login.jsp";
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
            AccountError err = new AccountError();
            AccountDAO dao = new AccountDAO();
            boolean check = true;
            String username = request.getParameter("txtUsername");
            if (username.isEmpty()) {
                err.setAccountID("Username can't be blank!");
                check = false;
            } else if (!dao.checkDuplicateAccountID(username)) {
                err.setAccountID("This Username haven't registered yet!");
                check = false;
            }
            AccountDTO dto = new AccountDTO();
            dto.setAccountID(username);
            if (!check) {
                request.setAttribute("INFO", dto);
                request.setAttribute("ERRORFORM", err);
                url = resetPass;
            } else {
                String password = dao.getPasswordByUsername(username);
                String email = dao.getEmail(username);
                String hostMailName = "kubinhocx";
                String hostMailPass = "Minh0709";
                String subject = "Lab Java web";
                String body = "Here is your current Password! \n " + password;
                dao.sendFromGMail(hostMailName, hostMailPass, email, subject, body);
                request.setAttribute("SENDEDPASS", "We have sent the password for your Email!");
                url = login;
            }

        } catch (Exception e) {
            e.printStackTrace();
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
