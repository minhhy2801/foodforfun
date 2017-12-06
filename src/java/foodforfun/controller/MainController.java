/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodforfun.controller;

import foodforfun.dto.AccountDTO;
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
public class MainController extends HttpServlet {

    private final String signUp = "SignUpController";
    private final String resetPassword = "ResetPasswordController";
    private final String signUpPage = "SignUp.jsp";
    private final String article = "ArticleController";
    private final String articleDetails = "ArticleDetailController";
    private final static String dashboard = "DashboardController";
    private final static String addEditor = "AddEditorController";
    //
    private final static String viewAllPosts = "ViewAllPostController";
    private final static String acceptPost = "ViewAcceptPostsController";
    
    
    private final static String pendPost = "ViewWaitPostController";
    private final static String login = "LoginController";
    private final static String changePassword = "ChangePasswordController";
    private final static String updateProfile = "UpdateProfileController";
    private final static String likePress = "LikePressController";
    private final static String comment = "CommentController";
    private final static String allUser = "AllUserController";
    private final static String banUser = "BanUserController";
    private final static String blockComment = "BlockCommentController";
    private final static String updateComment = "UpdateCommentController";
    private final static String editPost = "LoadUpdatepostController";
    private final static String previousClick = "PreviousControlles";
    private final static String nextClick = "NextControlles";
    private final static String deletePost = "DeletePostController";
    private final static String activePost = "ActivePostController";

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
            String action = request.getParameter("action");
            System.out.println(action);
            String url = "fail.jsp";
            if (action.equals("Login")) {
                url = "LoginController";
            } else if (action.equals("SignUp")) {
                url = signUp;
            } else if (action.equals("ResetPassword")) {
                url = resetPassword;
            } else if (action.equals("ClosePolicy")) {
                url = signUpPage;
            } else if (action.equals("Article")) {
                url = article;
            } else if (action.equals("ArticleDetail")) {
                url = articleDetails;
            } else if (action.equalsIgnoreCase("Dashboard")) {
                url = dashboard;
            } else if (action.equalsIgnoreCase("Add Editor")) {
                url = addEditor;
            } else if (action.equalsIgnoreCase("AllPost")) {
                url = viewAllPosts;
            } else if (action.equalsIgnoreCase("AccPost")) {
                url = acceptPost;
            } else if (action.equalsIgnoreCase("PendPost")) {
                url = pendPost;
            } else if (action.equalsIgnoreCase("Login")) {
                url = login;
            } else if (action.equalsIgnoreCase("Change Password")) {
                url = changePassword;
            } else if (action.equalsIgnoreCase("Update Profile")) {
                url = updateProfile;
            } else if (action.equals("LikeNumber")){
                url = likePress;
            } else if (action.equals("Comment")){
                url = comment;
            } else if (action.equals("AllUser")){
                url = allUser;
            } else if (action.equals("DeleteUser")){
                url = banUser;
            } else if (action.equals("BlockComment")){
                url = blockComment;
            } else if (action.equals("UpdateComment")){
                url = updateComment;
            } else if (action.equals("LoginPage")){
                url = signUpPage;
            } else if (action.equals("EditPost")){
                url = editPost;
            } else if (action.equals("Previous")){
                url = previousClick;
            } else if (action.equals("Next")){
                url = nextClick;
            } else if (action.equals("DeletePost")){
                url = deletePost;
            } else if (action.equals("ActivePost")) {
                url = activePost;
            }
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
