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

<title>Show Service Provider</title>

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
				<h2>Available Service Provider</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<section class="content">
               	<div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Service Providers</h3>
                                    
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive no-padding">
                                    <table class="table table-hover">
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Mobile_No</th>
                                            <th>Address</th>
                                            <th>Action</th>
                                        </tr>
                                        <c:set var="count" value="1" scope="page" />
                                        <c:forEach items="${serpro}" var="sp">
                                        <tr>
                                            <td>${count}</td>
                                            <td>${sp.name}</td>
                                            <td>${sp.mobileNo}</td>
                                            <td>${sp.address}</td>
                                          	<td class="center">
									            <a class="btn btn-success" href="/customer/book_service_final?serpro_id=${sp.serviceProviderId}">
									                <i class="glyphicon glyphicon-ok icon-white"></i>
									                Book
									            </a>
									            
									        </td>
                                        </tr>
                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                        </c:forEach>
                                        
                                    </table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>
               	</section>		
			</div>
		</div>
	</div>
	</section>
	<script src="/resources/user/js/register.js"></script>
	<%@include file="include-js.jsp"%>
</body>

</html>
