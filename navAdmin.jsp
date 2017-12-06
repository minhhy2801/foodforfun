<%-- 
    Document   : navAdmin
    Created on : Nov 9, 2017, 8:22:15 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="navbar">
  <div class="navbar-inner">
    <div class="sidebar-pusher">
      <a href="javascript:void(0);" class="waves-effect waves-button push-sidebar">
        <i class="icon-arrow-right"></i>
      </a>
    </div>

    <div class="topmenu-outer">
      <div class="top-menu">
        <ul class="nav navbar-nav navbar-left">
          <li>		
            <a href="javascript:void(0);" class="sidebar-toggle"><i class="icon-arrow-left"></i></a>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <span class="user-name">${ROLE} ${ID}<i class="fa fa-angle-down"></i></span>
            </a>
            <ul class="dropdown-menu dropdown-list" role="menu">
              <li role="presentation"><a href="UpdateProfile.jsp"><i class="icon-user"></i>Profile</a></li>
              <li role="presentation"><a href="LogoutController?logout=${REMEMBER}"><i class="icon-key m-r-xs"></i>Log out</a></li>
            </ul>
          </li>
        </ul><!-- Nav -->
      </div><!-- Top Menu -->
    </div>
  </div>
</div><!-- Navbar -->
