<%-- 
    Document   : SignUp
    Created on : Oct 15, 2017, 3:24:49 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="minhnh" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/datepicker.css"/>
        <link rel="stylesheet" href="css/register.css"/>

    </head>
    <body>
        <section class="signup-block">
            <div class="container">
                <div class="register">
                    <h2 class="text-center">Register Now</h2>
                    <form class="signup-form" action="MainController" method="POST">
                        <div class="form-group">
                            <label class="text-uppercase">Username</label> <font color="red">${ERRORFORM.accountID}</font>
                            <input type="text" class="form-control" name="txtUsername" value="${INFO.accountID}">
                            <label class="text-uppercase">Name</label> <font color="red">${ERRORFORM.name}</font>
                            <input type="text" class="form-control" name="txtName" value="${INFO.name}">
                            <label class="text-uppercase">Password</label> <font color="red">${ERRORFORM.password}</font>
                            <input type="password" class="form-control" name="txtPassword">
                            <label class="text-uppercase">Confirm Password</label> <font color="red">${ERRORFORM.confirmPassword}</font>
                            <input type="password" class="form-control" name="txtConfirmPassword">
                            <div class="row">
                                <div class="col-6">
                                    <label class="text-uppercase">BirthDay</label> <font color="red">${ERRORFORM.dob}</font>
                                    <input type="text" class="form-control datepicker" value="1/1/1997" name="txtBirthday"/>
                                </div>
                                <div class="col-5">
                                    <label class="text-uppercase">Gender</label> <br/>
                                    <input type="radio" checked value="true" name="gender"/><span>Male</span>
                                    <input type="radio" value="false" name="gender"/><span>Female</span> <br/>
                                </div>
                            </div>
                            <label class="text-uppercase">Address</label> <font color="red">${ERRORFORM.address}</font>
                            <input type="text" class="form-control" name="txtAddress" value="${INFO.address}">
                            <label class="text-uppercase">Phone</label> <font color="red">${ERRORFORM.phone}</font>
                            <input type="text" class="form-control" name="txtPhone" value="${INFO.phone}">
                            <label class="text-uppercase">Email</label> <font color="red">${ERRORFORM.email}</font>
                            <input type="text" class="form-control" name="txtEmail" value="${INFO.email}">
                            <label class="text-uppercase">Role</label> <br/>
                            <input type="radio" name="role" value="Modetrator" onclick="showDiv(this)"/><span>Moderator</span>
                            <input type="radio" name="role" checked value="Member" onclick="hideDiv(this)"/><span>Member</span>
                            <br/>
                            <div id="ex-and-place">
                                <label class="text-uppercase">Experience Years</label> <font color="red">${ERRORFORM.expYear}</font>
                                <input type="text" class="form-control" name="txtExpYear" value="${INFO.expYear}">
                                <label class="text-uppercase">Worked Place</label> <font color="red">${ERRORFORM.worledPlace}</font>
                                <input type="text" class="form-control" name="txtWorkedPlace" value="${INFO.worledPlace}">
                            </div>
                                <minhnh:url var="policyPage" value="MainController">
                                    <minhnh:param name="action" value="Policy"/>
                                </minhnh:url>
                            <input type="checkbox" name="cbAgree"><span>I agree with
                                <a href="Policy.jsp">Privacy Policy</a></span> <br/> <font color="red">${ERRORFORM.agreePolicy}</font>
                            <button type="submit" class="btn btn-login float-right" name="action" value="SignUp">Sign Up</button>
                            <a type="button" class="btn btn-login float-right"
                               style="background-color: rgba(255,226,181,0.75)" href="Login.jsp">Login
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </body>
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
    <script src="js/registerPage.js"></script>


</html>
