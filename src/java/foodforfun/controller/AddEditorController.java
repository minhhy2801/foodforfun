
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

public class AddEditorController extends HttpServlet {

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
    try {
      String id = request.getParameter("addID");
      String name = request.getParameter("addName");
      String pass = request.getParameter("addPass");
      String confirm = request.getParameter("addConfirm");
      String action = request.getParameter("action");
      AccountDTO dto = new AccountDTO();
      AccountDAO dao = new AccountDAO();
      AccountError err = new AccountError();
      boolean check = false;
      if (id.isEmpty() || id.equals("")) {
        err.setAccountID("Username can't be blank!");
        check = true;
      }
      if (id.length() > 20) {
        err.setAccountID("Username max length is 20 characters!");
        check = true;
      }
      if (name.isEmpty() || name.equals("")) {
        err.setName("Name can't be blank!");
        check = true;
      }
      if (name.length() > 100) {
        err.setName("Name max length is 100 characters!");
        check = true;
      }
      if (pass.isEmpty() || pass.equals("")) {
        err.setPassword("Password can't be blank!");
        check = true;
      }
      if (pass.length() > 50) {
        err.setPassword("Password max length is 50 characters!");
        check = true;
      }
      if (confirm.isEmpty() || confirm.equals("")) {
        err.setConfirmPassword("Confirm Password can't be blank!");
        check = true;
      }
      if (!confirm.equals(pass)) {
        err.setConfirmPassword("Password and Confirm Password are not match!");
        check = true;
      }
      if (action.equalsIgnoreCase("Add Editor")) {
        if (!check) {
          String role = "editor";
          dto.setAccountID(id);
          dto.setPassword(pass);
          dto.setRole(role);
          dto.setName(name);
          int insert = dao.addUser(id, name, pass, role);
          if (insert != 0) {
            dto.setAccountID(id);
            dto = dao.getAccountByID(id);
            request.setAttribute("AE", dto);
            String success = "Add Editor successful!";
            request.setAttribute("SUCCESS", success);
            request.getRequestDispatcher("AddUser.jsp").forward(request, response);
          } else {
            dto.setAccountID(id);
            dto = dao.getAccountByID(id);
            request.setAttribute("AE", dto);
            err.setAccountID("Username is existed!");
            request.setAttribute("ERROR", err);
            request.getRequestDispatcher("AddUser.jsp").forward(request, response);
          }
        } else {
          request.setAttribute("ERROR", err);
          request.getRequestDispatcher("AddUser.jsp").forward(request, response);
        }
      }
    } catch (Exception e) {
      log("Add Editor Error " + e.getMessage());
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
