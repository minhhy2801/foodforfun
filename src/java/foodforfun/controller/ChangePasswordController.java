package foodforfun.controller;

import foodforfun.dao.AccountDAO;
import foodforfun.errorobj.AccountError;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePasswordController extends HttpServlet {

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
      String id = request.getParameter("passID");
      String old = request.getParameter("updateOldPass");
      String oldPass = request.getParameter("oldPass");
      String newPass = request.getParameter("newPass");
      String confirm = request.getParameter("confirmPass");
      String action = request.getParameter("action");
      boolean check = false;

      AccountDAO dao = new AccountDAO();
      AccountError err = new AccountError();
      String eStr = "";
      if (old.isEmpty() || old == null) {
        eStr = "Old Password can't be blank!";
        check = true;
      } else if (!old.equals(oldPass)) {
        eStr = "Old Password is invaild!";
        check = true;
      }
      if (newPass.isEmpty() || newPass == null) {
        err.setPassword("New Password can't be blank!");
        check = true;
      }
      if (confirm.isEmpty() || confirm == null) {
        err.setConfirmPassword("Confirm Password can't be blank!");
        check = true;
      }
      if (!newPass.equals(confirm)) {
        err.setPassword("Password and Confirm Pass are not match!");
        err.setConfirmPassword("Password and Confirm Pass are not match!");
        check = true;
      }
      if (action.equalsIgnoreCase("Change Password")) {
        if (!check) {
          int change = dao.changePassword(id, newPass);
          if (change != 0) {
            String str = "Changed Password successful!";
            request.setAttribute("SU", str);
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
          } else {
            request.setAttribute("ER", eStr);
            request.setAttribute("ERROR", err);
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
          }
        } else {
          request.setAttribute("ERROR", err);
          request.setAttribute("ER", eStr);
          request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
        }
      }

    } catch (Exception e) {
      log("Change Password Error: " + e.getMessage());
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
