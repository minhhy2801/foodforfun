<%-- 
    Document   : login
    Created on : Oct 15, 2017, 3:11:24 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="minhdn" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="minhhy.front/css/loginPage.css"/>
    </head>
    <body>
        <%
            Cookie[] listCookie = request.getCookies();
            String user = "";
            String pass = "";
            int co = 0;
            if (listCookie != null) {
                while (co < listCookie.length) {
                    if (listCookie[co].getName().equals("user")) {
                        user = listCookie[co].getValue();
                    }
                    if (listCookie[co].getName().equals("pass")) {
                        pass = listCookie[co].getValue();
                    }
                    co++;
                }

            }
        %>
        <section class="login-block">
            <div class="container">
                <div class="row" id="loginForm" onload="checkFullPage()">
                    <div class="col-md-4 login-sec">
                        <h2 class="text-center">Login Now</h2>
                        <font color="red">${SENDEDPASS}</font>
                        <form class="login-form" action="MainController" method="POST">
                            <div class="form-group">
                                <label class="text-uppercase">Username</label><br/><font color="red">${USERNAMEEMPTY}</font>
                                <minhdn:if test="${not empty USERNAMEEMPTY}">
                                    <input type="text" class="form-control" name="txtUsername" value="">
                                </minhdn:if>
                                <minhdn:if test="${empty USERNAMEEMPTY}">
                                    <input type="text" class="form-control" name="txtUsername" value="<%out.print(user);%>">
                                </minhdn:if>
                            </div>
                            <div class="form-group">
                                <label class="text-uppercase">Password</label><br/><font color="red">${PASSWORDEMPTY}</font>
                                <minhdn:if test="${not empty USERNAMEEMPTY}">
                                    <input type="password" class="form-control" name="txtPassword" value="">
                                </minhdn:if>
                                <minhdn:if test="${empty USERNAMEEMPTY}">
                                    <input type="password" class="form-control" name="txtPassword" value="<%out.print(pass);%>">
                                </minhdn:if>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label">
                                    <minhdn:if test="${empty REMEMBER}">
                                        <input type="checkbox" class="form-check-input" name="cbRemember" value="true">
                                    </minhdn:if>
                                    <minhdn:if test="${REMEMBER eq 'true'}">
                                        <input type="checkbox" class="form-check-input" name="cbRemember" value="true" checked="true">
                                    </minhdn:if>
                                    <minhdn:if test="${REMEMBER eq 'false'}">
                                        <input type="checkbox" class="form-check-input" name="cbRemember" value="true">
                                    </minhdn:if>
                                    <small>Remember Me</small>
                                </label>
                                <button type="submit" class="btn btn-login float-right" name="action" value="Login">Login</button>
                                <button type="submit" class="btn btn-login float-right" name="action" value="LoginPage" style="background-color: rgba(255,226,181,0.75)">Sign Up</button>
                            </div>
                            <a href="ForgotPass.jsp" >Forgot Password</a>
                        </form>
                        <div class="copy-text">Created with <i class="fa fa-heart"></i> by Minh Bác Ái
                        </div>
                    </div>
                    <%@include file="loginSub.jsp" %>
                </div>
            </div>
        </section>
    </body>
    <script src="minhhy.front/js/jquery-3.2.1.js"></script>
    <script src="minhhy.front/js/popper.js"></script>
    <script src="minhhy.front/js/bootstrap.js"></script>
    <script src="minhhy.front/js/bootstrap.min.js"></script>
    <script src="minhhy.front/js/loginPage.js"></script>
</html>
