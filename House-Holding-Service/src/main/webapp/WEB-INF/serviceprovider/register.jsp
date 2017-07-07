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

<title>registration</title>

<%@include file="../customer/include-css.jsp"%>
</head>

<body id="page-top" class="index">
	<div id="skipnav">
		<a href="#maincontent">Skip to main content</a>
	</div>

	<!-- Navigation -->
	<%@include file="header.jsp"%>


	<!-- Registration form -->
	<br>
	<section id="login">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h3>Service Provider - Registration</h3>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
				<c:set var = "yes" value="yes"/>
				<c:choose>
					<c:when test="${otp eq yes}">
						<form method="post" action="/serviceprovider/registration">
						<div class="row control-group">
								<div
									class="form-group col-xs-5 floating-label-form-group controls">
									<label for="phone">OTP</label>
									 <input type="tex"
										class="form-control" placeholder="Enter OTP" name="otp" id="otp"
										title="Please enter Phone no." required>
									<p class="help-block text-danger">${response}</p>
								</div>
						</div>
						
						<br>
								<div class="row">
									<div class="form-group col-xs-12">
										<input type="submit" class="btn btn-success btn-lg" value="Submit">
									</div>
								</div>
						
						</form>
					</c:when>
					<c:otherwise>
						<form method="post" action="/serviceprovider/register">
							<div class="row control-group">
								<div
									class="form-group col-xs-8 floating-label-form-group controls">
									<label for="phone">Phone Number</label> <input type="tel"
										class="form-control" placeholder="Phone Number" name="mobileNo" id="mobileNo"
										title="Please enter Phone no." required>
									<p class="help-block text-danger">${response}</p>
								</div>
							</div>
							
							<div class="row control-group">
								<div
									class="form-group col-xs-12 floating-label-form-group controls">
									<label for="name">Name</label> <input type="text"
										class="form-control" placeholder="Name" name="name" id="customerName" required
										title="Please enter your name.">
									<p class="help-block text-danger"></p>
								</div>
							</div>
							<div class="row control-group">
								<div
									class="form-group col-xs-12 floating-label-form-group controls">
									<label for="email">Email Address</label> <input type="email"
										class="form-control" placeholder="Email Address" name="email" id="email"
										title="Please enter your email address." required>
									<p class="help-block text-danger"></p>
								</div>
							</div>
		
							<div class="row control-group">
								<div
									class="form-group col-xs-12 floating-label-form-group controls">
									<label>Password</label> <input type="password"
										class="form-control" placeholder="Password" name="password" id="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
										title="Must contain at least one number and one uppercase and lowercase letter, and at least 6 or more characters." required>
									<p class="help-block text-danger"></p>
								</div>
							</div>
		
							<div class="row control-group">
								<div
									class="form-group col-xs-12 floating-label-form-group controls">
									<label for="message">Re Enter Password</label> <input
										type="password" class="form-control" placeholder="Re Enter password"
										id="password2" data-rule-equalTo="#password" title="Do not match" required>
									<p class="help-block text-danger"></p>
								</div>
							</div>
		
							<div class="row control-group" id="cityContainer">
								<div
									class="form-group col-xs-8 floating-label-form-group controls">
									<label>City</label>
									
									 <select class="form-control" id="cityId" onchange="selectArea()" required>
									 	<option value="" selected disabled>----------Select City-------------</option>
											<c:forEach items="${citys}" var="city">
											    	<option value="${city.cityId}">${city.cityName}</option>
											</c:forEach>
									 </select>
									
								</div>
							</div>
							<div class="row control-group" id="areaContainer">
								<div
									class="form-group col-xs-8 floating-label-form-group controls">
									<label>Area</label>
									 <select class="form-control" name="area" id="areaId" required>
										<option value="" selected disabled>----------Select Area-------------</option>
									 </select>
									
								</div>
							</div>
							
							<div class="row control-group">
								<div
									class="form-group col-xs-12 floating-label-form-group controls">
									<label for="message">Address</label> 
									<textarea rows='5' class='form-control' placeholder="Address" name="address" id="address" required></textarea>
									<p class="help-block text-danger"></p>
								</div>
							</div>
							<input type="hidden" name="status" value="1">
							<br>
							<div id="success"></div>
							<div class="row">
								<div class="form-group col-xs-12">
									<input type="submit" class="btn btn-success btn-lg" value="Register">
								</div>
							</div>
							<div class="row">
                            <div class="form-group col-xs-12">
								<a href="/serviceprovider">For Login Click here</a>
                            </div>
                        </div>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	</section>
	<script src="/resources/user/js/register.js"></script>
	<%@include file="../customer/include-js.jsp"%>
</body>

</html>
