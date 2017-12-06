<%-- 
    Document   : ForgotPass
    Created on : Oct 16, 2017, 1:20:16 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Forgot Password Page</title>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/font-awesome.css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/forgetpage.css"/>
  </head>
  <body>
    <div class="color-div"></div>
    <div class="container">
      <div class="row">
        <div class="col-md-4 col-md-offset-4 forgot">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="text-center">
                <h3><i class="fa fa-lock fa-4x"></i></h3>
                <sh2 class="text-center">Forgot Password?</sh2>
                <p>You can reset your password here.</p>
                <div class="panel-body">

                  <form role="form" class="form" method="POST" action="MainController">
                                        <font color="red">${ERRORFORM.accountID}</font>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-mail-forward"></i></span>
                                                <input id="email" name="txtUsername" placeholder="Enter your Username here!" class="form-control"
                                                       value="${INFO.accountID}">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <button class="btn btn-danger btn-block" name="action" value="ResetPassword">Reset Password</button>
                                        </div>
                                    </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
