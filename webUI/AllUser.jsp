<%-- 
    Document   : AllUser
    Created on : Nov 10, 2017, 11:14:49 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="minhhy" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Members Page</title>
        <!-- Styles -->
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
    <body>
    <body class="compact-menu">
        <main class="page-content content-wrap">
            <%@include file="navAdmin.jsp" %>
            <div class="page-sidebar sidebar">
                <div class="page-sidebar-inner slimscroll">
                    <minhhy:if test="${ROLE eq 'Editor'}">
                        <ul class="menu accordion-menu">
                            <li><a href="DashboardController" class="waves-effect waves-button"><span class="menu-icon icon-home"></span><p>Dashboard</p></a></li>
                            <li><a href="UpdateProfile.jsp" class="waves-effect waves-button"><span class="menu-icon icon-user"></span><p>Profile</p></a></li>
                            <li><a href="ChangePassword.jsp" class="waves-effect waves-button"><span class="menu-icon icon-key"></span><p>Change Password</p></a></li>
                            <li><a href="MainController?action=AllPost&PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-layers"></span><p>All Posts</p></a></li>
                            <li><a href="LoadAddPostController" class="waves-effect waves-button"><span class="menu-icon icon-pencil"></span><p>Add Posts</p></a></li>
                            <li><a href="LoadAddCateController?PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-grid"></span><p>Categories</p></a></li>
                            <li><a href="LoadAddTagController?PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-pointer"></span><p>Tags</p></a></li>
                            <li class="active"><a href="MainController?action=AllUser&PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-users"></span><p>All Members</p><span class="active-page"></span></a></li>
                            <li><a href="AddUser.jsp" class="waves-effect waves-button"><span class="menu-icon icon-plus"></span><p>Add Editor</p></a></li>
                        </ul></minhhy:if>
                    <minhhy:if test="${ROLE eq 'Moderator'}">
                        <ul class="menu accordion-menu">
                            <li><a href="DashboardController" class="waves-effect waves-button"><span class="menu-icon icon-home"></span><p>Dashboard</p></a></li>
                            <li><a href="UpdateProfile.jsp" class="waves-effect waves-button"><span class="menu-icon icon-user"></span><p>Profile</p></a></li>
                            <li><a href="ChangePassword.jsp" class="waves-effect waves-button"><span class="menu-icon icon-key"></span><p>Change Password</p></a></li>
                            <li><a href="LoadAddPostController" class="waves-effect waves-button"><span class="menu-icon icon-pencil"></span><p>Add Posts</p></a></li>
                        </ul></minhhy:if>
                    <minhhy:if test="${ROLE eq 'Member'}">
                        <ul class="menu accordion-menu">
                            <li><a href="DashboardController" class="waves-effect waves-button"><span class="menu-icon icon-home"></span><p>Dashboard</p></a></li>
                            <li><a href="UpdateProfile.jsp" class="waves-effect waves-button"><span class="menu-icon icon-user"></span><p>Profile</p></a></li>
                            <li><a href="ChangePassword.jsp" class="waves-effect waves-button"><span class="menu-icon icon-key"></span><p>Change Password</p></a></li>
                        </ul></minhhy:if>


                    </div><!-- Page Sidebar Inner -->
                </div><!-- Page Sidebar -->
                <div class="page-inner">
                    <div class="page-title">
                        <div class="page-breadcrumb">
                            <ol class="breadcrumb">
                                <li><a href="Home.jsp">Home</a></li>
                                <li class="active">Members List</li>
                            </ol>
                        </div>
                    </div>
                    <div id="main-wrapper">
                        <div class="col-md-12">
                            <div class="panel panel-white">
                                <div class="panel-heading clearfix">
                                    <h4 class="panel-title">All Members</h4>
                                </div>
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table id="example" class="display table dataTable" style="width: 100%;" role="grid" aria-describedby="example_info">
                                            <thead>
                                            <th>Username</th>
                                            <th>Name</th>
                                            <th>Role</th>
                                            <th>Delete</th>
                                            </thead>

                                            <tbody>          

                                            <minhhy:forEach var="listAcc" items="${LISTACCOUNT}">

                                                <tr role="row" class="odd">

                                                    <td>${listAcc.name}</td>
                                                    <td class="sorting_1">${listAcc.accountID}</td>
                                                    <td>${listAcc.role}</td>
                                                    <td>
                                                        <form action="MainController" method="POST">
                                                            <input type="hidden" value="${listAcc.accountID}" name="AccountID"/>
                                                            <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" value="DeleteUser" name="action" 
                                                                    data-target="#delete" <minhhy:if test="${listAcc.role eq 'Banner'}">disabled</minhhy:if> ><span class="fa fa-trash"></span>
                                                                    </button>
                                                            </form>
                                                        </td>

                                                    </tr>
                                            </minhhy:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="row">
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="currentPage" value="${CURPAGE}"/>
                                        <input type="hidden" name="namePage" value="AllUser"/>
                                        <div class="pull-right">
                                            <button class="btn btn-info" name="action" value="Next"
                                                    <minhhy:if test="${NEXTRESULT eq 0}">style="visibility: hidden"</minhhy:if> >&DoubleRightArrow;</button>
                                            </div>
                                        </form>
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="currentPage" value="${CURPAGE}"/>
                                        <input type="hidden" name="namePage" value="AllUser"/>
                                        <div class="pull-left">
                                            <button class="btn btn-info" name="action" value="Previous" 
                                                    <minhhy:if test="${CURPAGE eq 1}">style="visibility: hidden"</minhhy:if>>&DoubleLeftArrow;</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>   
                </div><!-- Page Inner -->
            </div>
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
