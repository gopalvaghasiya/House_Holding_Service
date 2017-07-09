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

<title>Book Service</title>

<%@include file="include-css.jsp"%>
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
				<h2>Book Service</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
						
						<form method="post" action="/customer/show_serviceprovider">
							
							<div class="row control-group" id="cityContainer">
								<div
									class="form-group col-xs-8 floating-label-form-group controls">
									<label>Service</label>
									
									 <select class="form-control" name="service_id" required>
									 	<option value="" selected disabled>----------Select Service----------</option>
											<c:forEach items="${services}" var="ser">
											    	<option value="${ser.serviceId}">${ser.serviceName}</option>
											</c:forEach>
									 </select>
									
								</div>
							</div>
							
							<!-- <div class="row control-group">
								<div
									class="form-group col-xs-8 floating-label-form-group controls">
									<label>Address</label>
									<table><tr>
									<td width="150px">Saved Address : &nbsp<input type="radio" name="address" value="Saved Address"></td>
									<td>New Address : &nbsp<input type="radio" name="address" value="New Address"> </td>
									</tr></table>
									<p class="help-block text-danger"></p>
								</div>
							</div>
							 -->
							
							<br>
							<div id="success"></div>
							<div class="row">
								<div class="form-group col-xs-12">
									<input type="submit" class="btn btn-success btn-lg" value="Show Service Provider">
								</div>
							</div>
						</form>
			</div>
		</div>
	</div>
	</section>
	<script src="/resources/user/js/register.js"></script>
	<%@include file="include-js.jsp"%>
</body>

</html>
