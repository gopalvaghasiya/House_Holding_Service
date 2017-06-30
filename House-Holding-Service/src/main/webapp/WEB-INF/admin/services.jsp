<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="UTF-8">
        <title>View Services</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        
        <%@include file="include-css.jsp" %>
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <!-- Header -->
        <%@ include file = "header.jsp" %>
        
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            
            <%@include file="sidebar.jsp" %>
            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <%@include file="content_header.jsp" %>
                
                <!-- Main content -->
                <section class="content">
               	<div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Services</h3>
                                    <label class="control-label"  for="inputSuccess"> <h4>${response}</h4></label>
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive no-padding">
                                    <table class="table table-hover">
                                        <tr>
                                            <th>ID</th>
                                            <th>Category_Name</th>
                                            <th>Sercice_name</th>
                                            <th>Description</th>
                                            <th>Action</th>
                                        </tr>
                                        
                                        <c:forEach items="${services}" var="service">
                                        <tr>
                                            <td>${service.services.serviceId}</td>
                                            <td>${service.serviceCategory.cateName }</td>
                                            <td>${service.services.serviceName}</td>
                                            <td>${service.services.serviceDesc}</td>
                                            <td class="center">
												<a class="btn btn-info" href="/admin/edit_service?service_id=${service.services.serviceId}">
									                <i class="glyphicon glyphicon-edit icon-white"></i>
									                Edit
									            </a>
									            <a class="btn btn-danger" href="/admin/delete_service?service_id=${service.services.serviceId}">
									                <i class="glyphicon glyphicon-trash icon-white"></i>
									                Delete
									            </a>
									        </td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>
               	</section>
           </aside>
            <!-- /.right-side -->
        </div><!-- ./wrapper -->
        
        <!-- add new calendar event modal -->


        <%@include file="footer.jsp" %>
    </body>
</html>