package foodforfun.controller;

import foodforfun.dao.AccountDAO;
import foodforfun.dto.AccountDTO;
import foodforfun.errorobj.AccountError;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProfileController extends HttpServlet {

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
      AccountDAO dao = new AccountDAO();
      AccountDTO dto = new AccountDTO();
      String id = request.getParameter("updateId");
      String name = request.getParameter("updateName");
      String gender = request.getParameter("updateGender");
      String addr = request.getParameter("updateAddress");
      String phone = request.getParameter("updatePhone");
      String mail = request.getParameter("updateEmail");
      String exp = request.getParameter("updateYear");
      String work = request.getParameter("updatePlace");
      boolean gen = false, check = false;
      //yob
      String txtDob = request.getParameter("updateDob");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date conDate = sdf.parse(txtDob);
      Date today = new Date(System.currentTimeMillis());
      //err dto
      AccountError aErr = new AccountError();

      //check validate
      if (txtDob.equals("")) {
        aErr.setDob("DOB can't be blank!");
        check = true;
      }
      if ((today.getYear() - conDate.getYear()) < 16) {
        aErr.setDob("DOB must be 16+!");
        check = true;
      }
      if (gender == null || gender.isEmpty()) {
        aErr.setSex("Gender can't be blank!");
        check = true;
      }
      if (gender.equalsIgnoreCase("true")) {
        gen = true;
      }
      if (name.isEmpty()) {
        aErr.setName("Name can't be blank!");
        check = true;
      }
      if (name.length() > 100) {
        aErr.setName("Name max length is 100 characters!");
        check = true;
      }
      if (addr.isEmpty()) {
        aErr.setAddress("Address can't be blank!");
        check = true;
      }
      if (addr.length() > 100) {
        aErr.setAddress("Address max length is 100 characters");
        check = true;
      }
      if (phone.isEmpty()) {
        aErr.setPhone("Phone can't be blank!");
        check = true;
      }
      if (phone.length() > 11) {
        aErr.setPhone("Phone max length is 11 characters!");
        check = true;
      }
      if (!phone.matches("\\d{9,11}")) {
        aErr.setPhone("Phone is invaild!");
        check = true;
      }
      if (mail.isEmpty()) {
        aErr.setEmail("Email can't be blank!");
        check = true;
      }
      if (!mail.matches("\\w+@[a-zA-Z]+[.][a-zA-Z]+([.][a-zA-Z]+)?$")) {
        aErr.setEmail("Email is invaild!");
        check = true;
      }
      if (!exp.matches("\\d")) {
        aErr.setExpYear("Experience Years is invaild!");
        check = true;
      }
      if (work.length() > 100) {
        aErr.setWorledPlace("Worked Place max length is 100 character!");
        check = true;
      }
      //action 
      int update = 0;
      String action = request.getParameter("action");
      if (action.equalsIgnoreCase("Update Profile")) {
        if (!check) {
          long t = conDate.getTime();
          java.sql.Date dob = new java.sql.Date(t);
          int expYear = Integer.parseInt(exp);
          update = dao.updateProfileMod(id, name, addr, phone, mail, work, expYear, gen, dob);

          if (update != 0) {
            //find account by id
             String str = "Updated Profile successful!";
            request.setAttribute("SU", str);
            dto = dao.getAccountByID(id);
            request.setAttribute("INFO", dto);
            request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
          } else {
            //find account by id
            dto = dao.getAccountByID(id);
            request.setAttribute("INFO", dto);
            request.setAttribute("ERROR", aErr);
            request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
          }
        } else {
          dto = dao.getAccountByID(id);
          request.setAttribute("INFO", dto);
          request.setAttribute("ERROR", aErr);
          request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
        }
      }
    } catch (Exception e) {
      log("Update Profile error " + e.getMessage());
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
