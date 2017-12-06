<%-- 
    Document   : menuArticle
    Created on : Nov 9, 2017, 3:30:28 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="minhdn" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="main-menu-continer">
    <div id="main-menu" class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <!-- Responsive Navigation -->
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle Navigation</span>
                    <i class="fa fa-bars"></i>
                </button> <!-- /.navbar-toggle -->
                <!-- Logo -->
                <a class="navbar-brand" href="#">
                    <span>Food</span>ForFun</a><!-- /.navbar-brand -->
            </div> <!-- /.navbar-header -->

            <nav class="collapse navbar-collapse" role="navigation">
                <!-- Main navigation -->
                <ul id="headernavigation" class="nav navbar-nav navs-list l-tal-c">
                    <li><a href="Home.jsp">Home</a></li>
                    <li>
                        <minhdn:url var="article" value="MainController">
                            <minhdn:param name="flagArticle" value="TitleClick"/>
                            <minhdn:param name="currentPage" value="0"/>
                            <minhdn:param name="action" value="Article"/> 
                        </minhdn:url><a href="${article}">Article</a></li>
                </ul> 
                <ul class="header-social-list l-tal-r nav navbar-nav pull-right">
                    <minhdn:if test="${empty ID}">
                        <li><a href="Login.jsp">Login</a></li>
                        <li><a href="SignUp.jsp">Sign Up</a></li>
                        </minhdn:if>
                        <minhdn:if test="${not empty ID}">
                            <minhdn:if test="${ROLE eq 'Moderator'}">
                            <li>
                                <a href="DashboardController">Hi ${ROLE} ${ID}</a>
                            </li>
                            <li>
                                <a href="LogoutController?logout=${REMEMBER}">
                                    Log out
                                </a>
                            </li>
                        </minhdn:if>
                        <minhdn:if test="${ROLE eq 'Editor'}">
                            <li>
                                <a href="DashboardController">
                                    Hi ${ROLE} ${ID}
                                </a>
                            </li>
                            <li>
                                <a href="LogoutController?logout=${REMEMBER}">
                                    Log out
                                </a>
                            </li>
                        </minhdn:if>
                        <minhdn:if test="${ROLE eq 'Member'}">
                            <li> <a href="DashboardController">
                                    Hi ${ROLE} ${ID}</a>
                            </li>
                            <li>
                                <a href="LogoutController?logout=${REMEMBER}">
                                    Log out
                                </a>
                            </li>
                        </minhdn:if>

                    </minhdn:if>
                </ul>
                <!-- /.nav .navbar-nav -->
            </nav> <!-- /.navbar-collapse  -->
        </div> <!-- /.container -->
    </div>
    <!-- /#main-menu -->
</div><!-- /.main-menu-continer -->