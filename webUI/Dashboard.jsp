<%-- 
    Document   : Dashboard
    Created on : Nov 9, 2017, 8:18:38 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="minhhy" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="minhnh" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Page</title>
        <link href="assets/plugins/pace-master/themes/blue/pace-theme-flash.css" rel="stylesheet"/>
        <link href="assets/plugins/uniform/css/default.css" rel="stylesheet"/>
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/fontawesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/line-icons/simple-line-icons.css" rel="stylesheet" type="text/css"/>	
        <link href="assets/plugins/offcanvasmenueffects/css/menu_cornerbox.css" rel="stylesheet" type="text/css"/>	
        <link href="assets/plugins/waves/waves.min.css" rel="stylesheet" type="text/css"/>	
        <link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/3d-bold-navigation/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/slidepushmenus/css/component.css" rel="stylesheet" type="text/css"/>	
        <link href="assets/plugins/weather-icons-master/css/weather-icons.min.css" rel="stylesheet" type="text/css"/>	
        <!-- Theme Styles -->
        <link href="assets/css/meteor.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/layers/dark-layer.css" class="theme-color" rel="stylesheet" type="text/css"/>
        <link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
        <script src="assets/plugins/3d-bold-navigation/js/modernizr.js"></script>
    </head>
    <body class="compact-menu">
        <main class="page-content content-wrap">
            <%@include file="navAdmin.jsp" %>
            <div class="page-sidebar sidebar">
                <div class="page-sidebar-inner slimscroll">
                    <!--          menu admin-->

                    <minhhy:if test="${ROLE eq 'Editor'}">
                        <ul class="menu accordion-menu">
                            <li class="active"><a href="DashboardController" class="waves-effect waves-button"><span class="menu-icon icon-home"></span><p>Dashboard</p><span class="active-page"></span></a></li>
                            <li><a href="UpdateProfile.jsp" class="waves-effect waves-button"><span class="menu-icon icon-user"></span><p>Profile</p></a></li>
                            <li><a href="ChangePassword.jsp" class="waves-effect waves-button"><span class="menu-icon icon-key"></span><p>Change Password</p></a></li>
                            <li><a href="MainController?action=AllPost&PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-layers"></span><p>All Posts</p></a></li>
                            <li><a href="LoadAddPostController" class="waves-effect waves-button"><span class="menu-icon icon-pencil"></span><p>Add Posts</p></a></li>
                            <li><a href="LoadAddCateController?PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-grid"></span><p>Categories</p></a></li>
                            <li><a href="LoadAddTagController?PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-pointer"></span><p>Tags</p></a></li>
                            <li><a href="MainController?action=AllUser&PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-users"></span><p>All Members</p></a></li>
                            <li><a href="AddUser.jsp" class="waves-effect waves-button"><span class="menu-icon icon-plus"></span><p>Add Editor</p></a></li>
                        </ul>
                    </minhhy:if>
                    <minhhy:if test="${ROLE eq 'Moderator'}">
                        <ul class="menu accordion-menu">
                            <li class="active"><a href="DashboardController" class="waves-effect waves-button"><span class="menu-icon icon-home"></span><p>Dashboard</p><span class="active-page"></span></a></li>
                            <li><a href="UpdateProfile.jsp" class="waves-effect waves-button"><span class="menu-icon icon-user"></span><p>Profile</p></a></li>
                            <li><a href="ChangePassword.jsp" class="waves-effect waves-button"><span class="menu-icon icon-key"></span><p>Change Password</p></a></li>
                            <li><a href="LoadAddPostController" class="waves-effect waves-button"><span class="menu-icon icon-pencil"></span><p>Add Posts</p></a></li>
                        </ul>
                    </minhhy:if>
                    <minhhy:if test="${ROLE eq 'Member'}">
                        <ul class="menu accordion-menu">
                            <li class="active"><a href="DashboardController" class="waves-effect waves-button"><span class="menu-icon icon-home"></span><p>Dashboard</p><span class="active-page"></span></a></li>
                            <li><a href="UpdateProfile.jsp" class="waves-effect waves-button"><span class="menu-icon icon-user"></span><p>Profile</p></a></li>
                            <li><a href="ChangePassword.jsp" class="waves-effect waves-button"><span class="menu-icon icon-key"></span><p>Change Password</p></a></li>
                        </ul>
                    </minhhy:if>
                    <!--          menu admin-->
                </div><!-- Page Sidebar Inner -->
            </div><!-- Page Sidebar -->
            <div class="page-inner">
                <div class="page-title">
                    <div class="page-breadcrumb">
                        <ol class="breadcrumb">
                            <li><a href="Home.jsp">Home</a></li>
                            <li class="active">Dashboard</li>
                        </ol>
                    </div>
                </div>
                <div id="main-wrapper">
                    
                    
                    <div class="row">
                        <minhnh:if test="${(sessionScope.ROLE eq 'Member') or (sessionScope.ROLE eq 'Banner')}">
                            <div class="col-md-6">
                            <div class="panel panel-white">
                                <div class="panel-heading clearfix">
                                    <h3 class="panel-title">Recent Like</h3>
                                </div>
                                <div class="panel-body">
                                    <minhhy:forEach var="post" varStatus="counter" items="${requestScope.LP}">
                                        
                                        <minhhy:url var="postDetail" value="MainController">
                                            <minhhy:param name="action" value="ArticleDetail"/>
                                            <minhhy:param name="PostID" value="${post.id}"/>
                                        </minhhy:url>
                                        <p><span>You have been LIKE at <a href="${postDetail}">${post.title}</a></span></p>
                                            </minhhy:forEach>
                                </div>
                            </div>
                        </div>
                        </minhnh:if>
                        <minhnh:if test="${(sessionScope.ROLE eq 'Editor') or (sessionScope.ROLE eq 'Moderator')}">
                        <div class="col-md-6">
                            <div class="panel panel-white">
                                <div class="panel-heading clearfix">
                                    <h3 class="panel-title">Recent Activity</h3>
                                </div>
                                <div class="panel-body">
                                    <minhhy:forEach var="post" varStatus="counter" items="${requestScope.LP}">
                                        <fmt:formatDate type="date" pattern="hh:mm dd/MM/yyyy" value="${post.dateAccepted}" var="timePublic"/> 
                                        <minhhy:url var="postDetail" value="MainController">
                                            <minhhy:param name="action" value="ArticleDetail"/>
                                            <minhhy:param name="PostID" value="${post.id}"/>
                                        </minhhy:url>
                                        <p><span>${timePublic} created <a href="${postDetail}">${post.title}</a></span></p>
                                            </minhhy:forEach>
                                </div>
                            </div>
                        </div>
                        </minhnh:if>
                        <div class="col-md-6">
                            <div class="panel panel-white">
                                <div class="panel-heading clearfix">
                                    <h3 class="panel-title">Total</h3>
                                </div>
                                <minhnh:if test="${sessionScope.ROLE eq 'Editor'}">
                                    <div class="panel-body">
                                        <div class="panel-header-stats">
                                            <div class="row">
                                                <div class="col-md-3 col-xs-6">
                                                    <i class="icon-user"></i>
                                                    <h4 class="no-m">Users: ${TA}</h4>
                                                </div>
                                                <div class="col-md-3 col-xs-6">
                                                    <i class="icon-pencil"></i>
                                                    <h4 class="no-m">Posts: ${TP}</h4>
                                                </div>
                                                <div class="col-md-3 col-xs-6">
                                                    <i class="icon-bubble"></i>
                                                    <h4 class="no-m">Comments: ${TC}</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </minhnh:if>
                                <minhnh:if test="${sessionScope.ROLE eq 'Moderator'}">
                                    <div class="panel-body">
                                        <div class="panel-header-stats">
                                            <div class="row">
                                                <div class="col-md-3 col-xs-6">
                                                    <i class="icon-heart"></i>
                                                    <h4 class="no-m">Like: ${TL}</h4>
                                                </div>
                                                <div class="col-md-3 col-xs-6">
                                                    <i class="icon-pencil"></i>
                                                    <h4 class="no-m">Posts: ${TP}</h4>
                                                </div>
                                                <div class="col-md-3 col-xs-6">
                                                    <i class="icon-bubble"></i>
                                                    <h4 class="no-m">Comments: ${TC}</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </minhnh:if>
                                <minhnh:if test="${(sessionScope.ROLE eq 'Member') or (sessionScope.ROLE eq 'Banner')}">
                                    <div class="panel-body">
                                        <div class="panel-header-stats">
                                            <div class="row">
                                                <div class="col-md-3 col-xs-6">
                                                    <i class="icon-heart"></i>
                                                    <h4 class="no-m">Like: ${TL}</h4>
                                                </div>
                                                <div class="col-md-3 col-xs-6">
                                                    <i class="icon-bubble"></i>
                                                    <h4 class="no-m">Comments: ${TC}</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </minhnh:if>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="panel panel-white">
                                <div class="panel-heading clearfix">
                                    <h3 class="panel-title">Recent Comments</h3>
                                </div>
                                <div class="panel-body statement-card">
                                    <minhhy:forEach var="comt" varStatus="counter" items="${requestScope.LC}">
                                        <minhhy:url var="postDetailCmt" value="MainController">
                                            <minhhy:param name="action" value="ArticleDetail"/>
                                            <minhhy:param name="PostID" value="${comt.postID}"/>
                                        </minhhy:url>
                                        <p><span>${comt.nameOfCreater} comment at <a href="${postDetailCmt}">${comt.namePost}</a></span></p>
                                            </minhhy:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!-- Main Wrapper -->
            </div><!-- Page Inner -->
        </main><!-- Page Content -->

        <!-- Javascripts -->
        <script src="assets/plugins/jquery/jquery-3.1.0.min.js"></script>
        <script src="assets/plugins/jquery-ui/jquery-ui.min.js"></script>
        <script src="assets/plugins/pace-master/pace.min.js"></script>
        <script src="assets/plugins/jquery-blockui/jquery.blockui.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plugins/switchery/switchery.min.js"></script>
        <script src="assets/plugins/uniform/js/jquery.uniform.standalone.js"></script>
        <script src="assets/plugins/offcanvasmenueffects/js/classie.js"></script>
        <script src="assets/plugins/waves/waves.min.js"></script>
        <script src="assets/plugins/3d-bold-navigation/js/main.js"></script>
        <script src="assets/plugins/waypoints/jquery.waypoints.min.js"></script>  
        <script src="assets/plugins/flot/jquery.flot.min.js"></script>
        <script src="assets/plugins/flot/jquery.flot.time.min.js"></script>
        <script src="assets/plugins/flot/jquery.flot.symbol.min.js"></script>
        <script src="assets/plugins/flot/jquery.flot.resize.min.js"></script>
        <script src="assets/plugins/flot/jquery.flot.tooltip.min.js"></script>
        <script src="assets/plugins/curvedlines/curvedLines.js"></script>
        <script src="assets/plugins/chartjs/Chart.bundle.min.js"></script>
        <script src="assets/js/meteor.min.js"></script>
    </body>
</html>
