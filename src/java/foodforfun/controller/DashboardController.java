package foodforfun.controller;

import foodforfun.dao.AccountDAO;
import foodforfun.dao.CommentDAO;
import foodforfun.dao.PostDAO;
import foodforfun.dto.CommentDTO;
import foodforfun.dto.PostDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DashboardController extends HttpServlet {

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
            HttpSession session = request.getSession();
            PostDAO pdao = new PostDAO();
            CommentDAO cdao = new CommentDAO();
            AccountDAO adao = new AccountDAO();
            String role = (String)session.getAttribute("ROLE");
            String accountId = (String)session.getAttribute("ID");
            if (role.equals("Editor")) {
                int totalPost = pdao.countTotalOfPost();
                request.setAttribute("TP", totalPost);
                int totalComt = cdao.countTotalOfComt();
                request.setAttribute("TC", totalComt);
                int totalAcc = adao.countTotalOfAcount();
                request.setAttribute("TA", totalAcc);
                List<CommentDTO> listComt = cdao.get5RecentlyCommentForDashboard();
                request.setAttribute("LC", listComt);
                List<PostDTO> listPost = pdao.get5RecentlyPostForDashboard();
                request.setAttribute("LP", listPost);
            } else if (role.equals("Moderator")) {
                int totalPost = pdao.countTotalOfPostOfAccount(accountId);
                request.setAttribute("TP", totalPost);
                int totalComt = cdao.countTotalOfComtByCreaterId(accountId);
                request.setAttribute("TC", totalComt);
                int totalLike = pdao.countTotalOfLikeOfAccount(accountId);
                request.setAttribute("TL", totalLike);
                List<CommentDTO> listComt = cdao.get5RecentlyCommentForDashboardByAccountId(accountId);
                request.setAttribute("LC", listComt);
                List<PostDTO> listPost = pdao.get5RecentlyPostForDashboardByAccountId(accountId);
                request.setAttribute("LP", listPost);
            } else if (role.equals("Member")) {
                int totalComt = cdao.countTotalCmtByMember(accountId);
                request.setAttribute("TC", totalComt);
                int totalLike = pdao.countTotalOfLikeByMember(accountId);
                request.setAttribute("TL", totalLike);
                List<CommentDTO> listComt = cdao.get5RecentlyCommentForDashboardByMember(accountId);
                request.setAttribute("LC", listComt);
                List<PostDTO> listPost = pdao.get5RecentlyLikeForDashboardByMember(accountId);
                
                request.setAttribute("LP", listPost);
            } else {
                System.out.println("No Role");
            }

            request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            log("Dashboard " + e.getMessage());
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
