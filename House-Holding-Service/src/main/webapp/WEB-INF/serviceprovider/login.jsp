<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

    <%@include file="../customer/include-css.jsp" %>
</head>

<body id="page-top" class="index">
<div id="skipnav"><a href="#maincontent">Skip to main content</a></div>

    <!-- Navigation -->
    <%@include file="header.jsp" %>
<br>
    <section id="login">
        <div class="container">
            <div class="row">
            <h4>${msg}</h4>
                <div class="col-lg-12 text-center">
                    <h3>Service Provider - Login </h3>
                    <hr class="star-primary">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
                    <h4>${response}</h4>
                    <form method="post" action="/serviceprovider/login">
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label for="phone">Phone Number</label>
                                <input type="text" class="form-control" placeholder="Phone Number" name="phone" id="phone" title="Please enter your phone number." pattern="[0-9]{10}" required>
                                
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>Password</label>
                                <input type="password" class="form-control" name="pass" placeholder="Password" id="" title="Please enter your email address." required>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        
                        <br>
                        <div id="success"></div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <button type="submit" class="btn btn-success btn-lg">Login</button>
                            </div>
                            
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12">
								<a href="/serviceprovider/register">For Sign Up Click here</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

 
<%@include file="../customer/include-js.jsp" %>
</body>

</html>
