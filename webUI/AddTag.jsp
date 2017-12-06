<%-- 
    Document   : AddTag
    Created on : Nov 10, 2017, 10:45:41 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="minhdn" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tag Page</title>
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
        <script>
            function showEdit(id, name, des) {
                var edit = document.getElementById("edit");
                var addTags = document.getElementById("addTags");
                var idAdd = document.getElementById("idAdd");
                var updateName = document.getElementById("updateName");
                var updateDes = document.getElementById("updateDes");
                edit.style.display = "block";
                addTags.style.display = "none";
                idAdd.value = id;
                updateName.value = name;
                updateDes.value = des;
            }
        </script>
    </head>
    <body class="compact-menu">
        <main class="page-content content-wrap">
            <%@include file="navAdmin.jsp" %>
            <div class="page-sidebar sidebar">
                <div class="page-sidebar-inner slimscroll">
                    <minhdn:if test="${ROLE eq 'Editor'}">
                        <ul class="menu accordion-menu">
                            <li><a href="DashboardController" class="waves-effect waves-button"><span class="menu-icon icon-home"></span><p>Dashboard</p></a></li>
                            <li><a href="UpdateProfile.jsp" class="waves-effect waves-button"><span class="menu-icon icon-user"></span><p>Profile</p></a></li>
                            <li><a href="ChangePassword.jsp" class="waves-effect waves-button"><span class="menu-icon icon-key"></span><p>Change Password</p></a></li>
                            <li><a href="MainController?action=AllPost&PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-layers"></span><p>All Posts</p></a></li>
                            <li><a href="LoadAddPostController?PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-pencil"></span><p>Add Posts</p></a></li>
                            <li><a href="LoadAddCateController?PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-grid"></span><p>Categories</p></a></li>
                            <li class="active"><a href="LoadAddTagController" class="waves-effect waves-button"><span class="menu-icon icon-pointer"></span><p>Tags</p><span class="active-page"></span></a></li>
                            <li><a href="MainController?action=AllUser&PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-users"></span><p>All Members</p></a></li>
                            <li><a href="AddUser.jsp" class="waves-effect waves-button"><span class="menu-icon icon-plus"></span><p>Add Editor</p></a></li>
                        </ul></minhdn:if>
                    <minhdn:if test="${ROLE eq 'Moderator'}">
                        <ul class="menu accordion-menu">
                            <li><a href="DashboardController" class="waves-effect waves-button"><span class="menu-icon icon-home"></span><p>Dashboard</p></a></li>
                            <li><a href="UpdateProfile.jsp" class="waves-effect waves-button"><span class="menu-icon icon-user"></span><p>Profile</p></a></li>
                            <li><a href="ChangePassword.jsp" class="waves-effect waves-button"><span class="menu-icon icon-key"></span><p>Change Password</p></a></li>
                            <li><a href="LoadAddPostController" class="waves-effect waves-button"><span class="menu-icon icon-pencil"></span><p>Add Posts</p></a></li>
                        </ul></minhdn:if>
                    <minhdn:if test="${ROLE eq 'Member'}">
                        <ul class="menu accordion-menu">
                            <li><a href="DashboardController" class="waves-effect waves-button"><span class="menu-icon icon-home"></span><p>Dashboard</p></a></li>
                            <li><a href="UpdateProfile.jsp" class="waves-effect waves-button"><span class="menu-icon icon-user"></span><p>Profile</p></a></li>
                            <li><a href="ChangePassword.jsp" class="waves-effect waves-button"><span class="menu-icon icon-key"></span><p>Change Password</p></a></li>
                        </ul></minhdn:if>

                    </div><!-- Page Sidebar Inner -->
                </div><!-- Page Sidebar -->
                <div class="page-inner">
                    <div class="page-title">
                        <div class="page-breadcrumb">
                            <ol class="breadcrumb">
                                <li><a href="Home.jsp">Home</a></li>
                                <li class="active">Tag</li>
                            </ol>
                        </div>
                    </div>
                    <div id="main-wrapper">
                        <div class="row">
                        <minhdn:if test="${empty FAULTVALUE}">
                            <div class="col-md-6" id="addTags" style="display: block">
                                <div class="panel panel-white">
                                    <div class="panel-heading clearfix">
                                        <h3 class="panel-title">Add Tag</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="center">
                                            <form id="addtag" method="post" class="validate" action="AddTagController">
                                                <font color="red">${FAULTADD}</font><br/>
                                                <label class="text-uppercase">Name: </label>
                                                <input type="text" class="form-control" name="txtTagName" value="${FAULTADDVALUE.name}">
                                                <label for="tag-description" class="text-uppercase">Description:</label>
                                                <textarea name="description" class="form-control" id="tag-description" rows="10" cols="40">${FAULTADDVALUE.des}</textarea>
                                                <button type="submit" class="btn btn-facebook float-left">Add Tag</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </minhdn:if>
                        <minhdn:if test="${not empty FAULTVALUE}">
                            <div class="col-md-6" id="addTags" style="display: none">
                                <div class="panel panel-white">
                                    <div class="panel-heading clearfix">
                                        <h3 class="panel-title">Add Tag</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="center">
                                            <form id="addtag" method="post" class="validate" action="AddTagController">
                                                <font color="red">${FAULTADD}</font><br/>
                                                <label class="text-uppercase">Name: </label>
                                                <input type="text" class="form-control" name="txtTagName" value="${FAULTADDVALUE.name}">
                                                <label for="tag-description" class="text-uppercase">Description:</label>
                                                <textarea name="description" class="form-control" id="tag-description" rows="10" cols="40">${FAULTADDVALUE.des}</textarea>
                                                <button type="submit" class="btn btn-facebook float-left">Add Tag</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </minhdn:if>
                        <minhdn:if test="${empty FAULTVALUE}">
                            <div class="col-md-6" style="display: none" id="edit">
                                <div class="panel panel-white">
                                    <div class="panel-heading clearfix">
                                        <h3 class="panel-title">Update Tag</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="center">

                                            <form id="addtag" method="post" class="validate" action="UpdateTagController">
                                                <input type="text" id="idAdd" name="txtIDUpdate"/>
                                                <label class="text-uppercase">Name: </label>
                                                <input type="text" class="form-control" id="updateName" name="txtTagName">
                                                <label class="text-uppercase">Description:</label>
                                                <textarea name="description" class="form-control" id="updateDes" rows="10" cols="40"></textarea>
                                                <button type="submit" class="btn btn-facebook float-left">Update Tag</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </minhdn:if>
                        <minhdn:if test="${not empty FAULTVALUE}">
                            <div class="col-md-6" style="display: block" id="edit">
                                <div class="panel panel-white">
                                    <div class="panel-heading clearfix">
                                        <h3 class="panel-title">Update Tag</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="center">
                                            <form id="addtag" method="post" class="validate" action="UpdateTagController">
                                                <font color="red">${FAULTUPDATE}</font><br/>
                                                <input type="text" id="idAdd" name="txtIDUpdate" value="${FAULTVALUE.id}"/>
                                                <label class="text-uppercase">Name: </label>
                                                <input type="text" class="form-control" id="updateName" name="txtTagName" value="${FAULTVALUE.name}">
                                                <label class="text-uppercase">Description:</label>
                                                <textarea name="description" class="form-control" id="updateDes" rows="10" cols="40">${FAULTVALUE.des}</textarea>
                                                <button type="submit" class="btn btn-facebook float-left">Update Tag</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </minhdn:if>
                        <div class="col-md-6">
                            <div class="panel panel-white">
                                <div class="panel-heading clearfix">
                                    <h3 class="panel-title">Tag List</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="center">
                                        <table id="mytable" class="table table-bordred table-striped">
                                            <thead>
                                            <th>Tag Name</th>
                                            <th>Author</th>
                                            <th>Description</th>
                                            <th>Count</th>
                                            <th>Edit</th>
                                            <th>Delete</th>
                                            </thead>
                                            <tbody>
                                                <minhdn:forEach items="${TAGLIST}" var="tag" varStatus="count">
                                                    <tr>
                                                        <minhdn:url var="article" value="MainController">
                                                            <minhdn:param name="tagID" value="${tag.id}"/>
                                                            <minhdn:param name="flagArticle" value="TagClick"/>
                                                            <minhdn:param name="currentPage" value="0"/>
                                                            <minhdn:param name="action" value="Article"/> 
                                                        </minhdn:url>
                                                        <td><a href="${article}">${tag.name}</a></td>
                                                        <td>${tag.createrID}</td>
                                                        <td>${tag.des}</td>
                                                        <td>${tag.count}</td>
                                                        <td>
                                                            <button onclick="showEdit(${tag.id}, '${tag.name}', '${tag.des}')" class="btn btn-primary btn-xs"><span class="fa fa-pencil"></span></button>
                                                        </td>
                                                        <td>
                                                            <form action="DeleteTagController" method="POST">
                                                                <input type="hidden" value="${tag.id}" name="txtIDRemove"/>
                                                                <button class="btn btn-danger btn-xs"><span class="fa fa-trash"></span></button>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </minhdn:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="row">
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="currentPage" value="${CURPAGE}"/>
                                            <input type="hidden" name="namePage" value="AddTag"/>
                                            <div class="pull-right">
                                                <button class="btn btn-info" name="action" value="Next"
                                                        <minhdn:if test="${NEXTRESULT eq 0}">style="visibility: hidden"</minhdn:if> >&DoubleRightArrow;</button>
                                                </div>
                                            </form>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="currentPage" value="${CURPAGE}"/>
                                            <input type="hidden" name="namePage" value="AddTag"/>
                                            <div class="pull-left">
                                                <button class="btn btn-info" name="action" value="Previous" 
                                                        <minhdn:if test="${CURPAGE eq 1}">style="visibility: hidden"</minhdn:if>>&DoubleLeftArrow;</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- Main Wrapper -->
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
