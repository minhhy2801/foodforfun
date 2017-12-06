/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodforfun.controller;

import foodforfun.dao.AccountDAO;
import foodforfun.dto.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

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
      String username = request.getParameter("txtUsername");
      String password = request.getParameter("txtPassword");
      String result = "";
      String checked = "";
      HttpSession session = request.getSession(true);
      checked = request.getParameter("cbRemember");
      AccountDAO dao = new AccountDAO();

      if (username.isEmpty() && password.isEmpty()) {
        request.setAttribute("USERNAMEEMPTY", "Username can not be empty!");
        request.setAttribute("PASSWORDEMPTY", "Password can not be empty!");
        request.setAttribute("REMEMBER", checked);
        request.getRequestDispatcher("Login.jsp").forward(request, response);
      } else if (username.isEmpty()) {
        request.setAttribute("USERNAMEEMPTY", "Username can not be empty!");
        request.getRequestDispatcher("Login.jsp").forward(request, response);
      } else if (password.isEmpty()) {
        request.setAttribute("PASSWORDEMPTY", "Password can not be empty!");
        request.getRequestDispatcher("Login.jsp").forward(request, response);
      } else {
        //System.out.println(result);
        AccountDTO dto = dao.getAccountByID(username);
        result = dao.checkLogin(username, password);
        if (result.equals("Moderator") || result.equals("Member") || result.equals("Editor")) {
          if (checked == null) {
            session.setAttribute("INFO", dto);
            session.setAttribute("ID", username);
            session.setAttribute("ROLE", result);
            session.setAttribute("REMEMBER", "false");
            request.getRequestDispatcher("Home.jsp").forward(request, response);
          } else {
            session.setAttribute("INFO", dto);
            session.setAttribute("ID", username);
            session.setAttribute("ROLE", result);
            session.setAttribute("REMEMBER", "true");
            Cookie user = new Cookie("user", username);
            Cookie pass = new Cookie("pass", password);
            response.addCookie(user);
            response.addCookie(pass);
            request.getRequestDispatcher("Home.jsp").forward(request, response);
          }
        } else if (result.equals("fail")) {
          if (checked == null) {
            session.setAttribute("REMEMBER", "false");
          } else {
            session.setAttribute("REMEMBER", "true");
          }
          request.setAttribute("USERNAMEEMPTY", "Invalid username or password, please try again.");
          request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
      }
    } catch (Exception e) {

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
