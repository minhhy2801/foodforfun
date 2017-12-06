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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.control.DatePicker;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kubin
 */
public class SignUpController extends HttpServlet {

    private final String login = "Login.jsp";
    private final String signUp = "SignUp.jsp";

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
            AccountDAO dao = new AccountDAO();
            AccountError err = new AccountError();
            boolean check = true;
            String role = request.getParameter("role");
            String username = request.getParameter("txtUsername");
            if (username.isEmpty()) {
                err.setAccountID("Username can't be blank!");
                check = false;
            } else if (dao.checkDuplicateAccountID(username)) {
                err.setAccountID("This Username has already exist!");
                check = false;
            }
            String name = request.getParameter("txtName");
            if (name.isEmpty()) {
                err.setName("Name can't be blank!");
                check = false;
            }
            String password = request.getParameter("txtPassword");
            if (password.isEmpty()) {
                err.setPassword("Password can't be blank!");
                check = false;
            }
            String confirmPassword = request.getParameter("txtConfirmPassword");
            if (confirmPassword.isEmpty()) {
                err.setConfirmPassword("Confirm Password can’t be blank");
                check = false;
            } else if (!confirmPassword.equals(password)) {
                err.setConfirmPassword("Confirm password not match password.");
                check = false;
            }
            String birthday = request.getParameter("txtBirthday");
            System.out.println(birthday);
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);

            //lay ngay hien hanh 
            Date today = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            //Tinh tuoi 
            Calendar c2 = Calendar.getInstance();
            c2.setTime(date);
            int age;
            int factor = 0;
            if (c.get(Calendar.DAY_OF_YEAR) < c2.get(Calendar.DAY_OF_YEAR)) {
                factor = -1;
            }
            age = c.get(Calendar.YEAR) - c2.get(Calendar.YEAR) + factor;

            if (role.equals("Modetrator")) {
                if (age < 16) {
                    err.setDob("Age must over 16");
                    check = false;
                }
            }
            java.sql.Date DOB = new java.sql.Date(date.getTime());
            boolean gender = false;
            String sex = request.getParameter("gender");
            if (sex.equals("true")) {
                gender = true;
            }
            String address = request.getParameter("txtAddress");
            if (address.isEmpty()) {
                err.setAddress("Address can't be blank!");
                check = false;
            }
            String phone = request.getParameter("txtPhone");
            if (phone.isEmpty()) {
                err.setPhone("Phone is invalid!");
                check = false;
            } else if (!phone.matches("\\d{8,11}")) {
                err.setPhone("The phone number should be numeric only and must be between 8 and 11 digits");
                check = false;
            }
            String email = request.getParameter("txtEmail");
            if (email.isEmpty() || !email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
                err.setEmail("Email is invalid!");
                check = false;
            }

            int experienceYear = 0;
            String workedPlace = null;
            if (role.equals("Modetrator")) {
                String expYear = request.getParameter("txtExpYear");
                if (expYear.isEmpty()) {
                    err.setExpYear("Experience Years can't be blank");
                    check = false;
                } else if (!expYear.matches("\\d{1,2}")) {
                    err.setExpYear("Experience Years is invaild");
                    check = false;
                } else {
                    experienceYear = Integer.parseInt(expYear);
                    if (experienceYear >= age) {
                        err.setExpYear("Experience Years is invaild");
                        check = false;
                    }
                }

                workedPlace = request.getParameter("txtWorkedPlace");
            }
            String agree = request.getParameter("cbAgree");
            if (agree == null) {
                err.setAgreePolicy("You need agree the policy to register");
                check = false;
            }

            // AccountDTO dto = new AccountDTO(username, password, role, name, gender, email, phone, address, DOB, experienceYear, workedPlace);
            AccountDTO dto = new AccountDTO();
            dto.setAccountID(username);
            dto.setPassword(password);
            dto.setRole(role);
            dto.setName(name);
            dto.setSex(gender);
            dto.setEmail(email);
            dto.setPhone(phone);
            dto.setAddress(address);
            dto.setDob(DOB);
            dto.setExpYear(experienceYear);
            dto.setWorledPlace(workedPlace);

            if (!check) {
                request.setAttribute("INFO", dto);
                request.setAttribute("ERRORFORM", err);
                url = signUp;
            } else {
                int n = dao.registNewAccount(dto);
                if (n != 0) {
                    request.setAttribute("SUCCESS", "Update Successfully!!");
                    url = login;
                }
            }

        } catch (Exception e) {
            log("ERROR at SignUpController " + e.getMessage());
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
