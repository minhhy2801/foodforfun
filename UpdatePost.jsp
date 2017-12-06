<%-- 
    Document   : UpdatePost
    Created on : Nov 10, 2017, 2:32:30 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="minhdn" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update post</title>
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
        <link href="assets/plugins/summernote-master/summernote.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css"/>

        <!-- Theme Styles -->
        <link href="assets/css/meteor.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/layers/dark-layer.css" class="theme-color" rel="stylesheet" type="text/css"/>
        <link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>

        <script src="assets/plugins/3d-bold-navigation/js/modernizr.js"></script>
        <script src="assets/plugins/offcanvasmenueffects/js/snap.svg-min.js"></script>

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
                            <li  class="active"><a href="LoadAddPostController" class="waves-effect waves-button"><span class="menu-icon icon-pencil"></span><p>Add Posts</p><span class="active-page"></span></a></li>
                            <li><a href="LoadAddCateController?PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-grid"></span><p>Categories</p></a></li>
                            <li><a href="LoadAddTagController?PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-pointer"></span><p>Tags</p></a></li>
                            <li><a href="MainController?action=AllUser&PageID=1" class="waves-effect waves-button"><span class="menu-icon icon-users"></span><p>All Members</p></a></li>
                            <li><a href="AddUser.jsp" class="waves-effect waves-button"><span class="menu-icon icon-plus"></span><p>Add Editor</p></a></li>
                        </ul>
                    </minhdn:if>
                    <minhdn:if test="${ROLE eq 'Moderator'}">
                        <ul class="menu accordion-menu">
                            <li><a href="DashboardController" class="waves-effect waves-button"><span class="menu-icon icon-home"></span><p>Dashboard</p></a></li>
                            <li><a href="UpdateProfile.jsp" class="waves-effect waves-button"><span class="menu-icon icon-user"></span><p>Profile</p></a></li>
                            <li><a href="ChangePassword.jsp" class="waves-effect waves-button"><span class="menu-icon icon-key"></span><p>Change Password</p></a></li>
                            <li  class="active"><a href="LoadUpdatePostController" class="waves-effect waves-button"><span class="menu-icon icon-pencil"></span><p>Update Posts</p><span class="active-page"></span></a></li>
                        </ul>
                    </minhdn:if>
                    

                </div><!-- Page Sidebar Inner -->
            </div><!-- Page Sidebar -->
            <div class="page-inner">
                <div class="page-title">
                    <div class="page-breadcrumb">
                        <ol class="breadcrumb">
                            <li><a href="Home.jsp">Home</a></li>
                            <li class="active">Update Post</li>
                        </ol>
                    </div>
                </div>

                <div id="main-wrapper">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-white">
                                <div class="panel-heading clearfix">
                                    <h4 class="panel-title">Update Post</h4>
                                </div>
                                <form class="form-horizontal"action="UpdatepostController" method="POST" id="testform"
                                      enctype="multipart/form-data">
                                    <input type="hidden" value="${POSTID}" name="txtPostID"/>
                                    <div class="panel-body">
                                        <minhdn:if test="${not empty sessionScope.errPost.title}">
                                            <div class="form-group">
                                                <label for="input-Default" class="col-sm-2 control-label">Title<font color="red">*</font>:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="input-Default" name="txtTitle" value="${sessionScope.postDTO.title}"><br/>
                                                    <font color="red">${sessionScope.errPost.title}</font>
                                                </div>
                                            </div>
                                        </minhdn:if>
                                        <minhdn:if test="${empty sessionScope.errPost.title}">
                                            <div class="form-group">
                                                <label for="input-Default" class="col-sm-2 control-label">Title:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="input-Default" name="txtTitle" value="${sessionScope.postDTO.title}">
                                                </div>
                                            </div>
                                        </minhdn:if>
                                        <minhdn:if test="${not empty sessionScope.errPlace.address}">
                                            <div class="form-group">
                                                <label for="input-help-block" class="col-sm-2 control-label">Address<font color="red">*</font>:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="input-help-block" name="txtAddress" value="${sessionScope.placeDTO.address}"><br/>
                                                    <font color="red">${sessionScope.errPlace.address}</font>
                                                </div>
                                            </div>
                                        </minhdn:if>
                                        <minhdn:if test="${empty sessionScope.errPlace.address}">
                                            <div class="form-group">
                                                <label for="input-help-block" class="col-sm-2 control-label">Address:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="input-help-block" name="txtAddress" value="${sessionScope.placeDTO.address}">
                                                </div>
                                            </div>
                                        </minhdn:if>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Place: </label>
                                            <div class="col-sm-10">
                                                <div class="row">
                                                    <minhdn:if test="${not empty sessionScope.errPlace.phone}">
                                                        <div class="col-md-3">
                                                            <input type="text" class="form-control" placeholder="Phone" name="txtPhone" value="${sessionScope.placeDTO.phone}"><font color="red">${sessionScope.errPlace.phone}</font>
                                                        </div>
                                                    </minhdn:if>
                                                    <minhdn:if test="${empty sessionScope.errPlace.phone}">
                                                        <div class="col-md-3">
                                                            <input type="text" class="form-control" placeholder="Phone" name="txtPhone" value="${sessionScope.placeDTO.phone}">
                                                        </div>
                                                    </minhdn:if>
                                                    <minhdn:if test="${not empty sessionScope.errPlace.placeName}">
                                                        <div class="col-md-4">
                                                            <input type="text" class="form-control" placeholder="Name" name="txtName"  value="${sessionScope.placeDTO.placeName}"><font color="red">${sessionScope.errPlace.placeName}</font>
                                                        </div>
                                                    </minhdn:if>
                                                    <minhdn:if test="${empty sessionScope.errPlace.placeName}">
                                                        <div class="col-md-4">
                                                            <input type="text" class="form-control" placeholder="Name" name="txtName" value="${sessionScope.placeDTO.placeName}">
                                                        </div>
                                                    </minhdn:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Date: </label>
                                            <div class="col-md-2">
                                                <input type="text" class="form-control" placeholder="Date" readonly="true" value="${sessionScope.DATE}"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Status</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" placeholder="" readonly="true" value="Pending"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Image Main</label>
                                            <div class="col-sm-4">
                                                <input class="note-image-input" type="file" name="files" accept="image/*"><font color="red">${EMPTYIMG}</font>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <div class="summernote" id="summernote"></div>
                                            </div>
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label class="col-sm-2 control-label">Categories</label>
                                            <div class="col-sm-12">
                                                <div style="overflow-y: scroll; height: 100px;">
                                                    <minhdn:forEach items="${sessionScope.cateList}" var="cate">
                                                        <minhdn:if test="${cate.checked eq false}">
                                                            <p><input type="checkbox" value="" name="Cate${cate.id}"><span>${cate.name}</p>
                                                            </minhdn:if>
                                                            <minhdn:if test="${cate.checked eq true}">
                                                            <p><input type="checkbox" value="" name="Cate${cate.id}" checked="true"><span>${cate.name}</p>
                                                            </minhdn:if>
                                                        </minhdn:forEach>
                                                </div><br>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" placeholder="Add Tags..." name="txtNewCate" value="${sessionScope.INPUTCATE}"><br>
                                                    <font color="red">${sessionScope.CATEEMPTY}</font><br/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label class="col-sm-2 control-label">Tags</label>
                                            <div class="col-sm-12">
                                                <div style="overflow-y: scroll; height: 100px;">
                                                    <minhdn:forEach items="${sessionScope.tagList}" var="tag">
                                                        <minhdn:if test="${tag.checked eq false}">
                                                            <p><input type="checkbox" value="" name="Tag${tag.id}"><span>${tag.name}</p>
                                                            </minhdn:if>
                                                            <minhdn:if test="${tag.checked eq true}">
                                                            <p><input type="checkbox" value="" name="Tag${tag.id}" checked="true"><span>${tag.name}</p>
                                                            </minhdn:if>
                                                        </minhdn:forEach>
                                                </div><br>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" placeholder="Add Category..." name="txtNewTag" value="${sessionScope.INPUTTAG}"><br>
                                                    <font color="red">${sessionScope.CATEEMPTY}</font>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="pull-right">
                                            <input type="submit" class="btn btn-success" id="btnSubmit" value="Submit"/>
                                            <input class="btn btn-danger" value="Cancel"/>
                                        </div>
                                    </div>
                                    <input type="hidden" name="content" id="txtContent" value="${sessionScope.postDTO.detail}"/>
                                    <%
                                        session.removeAttribute("errPlace");
                                        session.removeAttribute("postDTO");
                                        session.removeAttribute("placeDTO");
                                        session.removeAttribute("errPost");
                                        session.removeAttribute("cateList");
                                        session.removeAttribute("tagList");
                                        session.removeAttribute("DATE");
                                        session.removeAttribute("CATEEMPTY");
                                        session.removeAttribute("HIHI");
                                        session.removeAttribute("TAGEMPTY");
                                        session.removeAttribute("INPUTCATE");
                                        session.removeAttribute("INPUTTAG");
                                    %>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- Row -->
        </div>
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
        <script src="assets/plugins/summernote-master/summernote.min.js"></script>
        <script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
        <script src="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
        <script src="assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
        <script src="assets/js/meteor.min.js"></script>
        <script src="assets/js/pages/form-elements.js"></script>
        <script src="js/texteditor.js" type="text/javascript"></script>