<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Service</title>
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
               		<div class="box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">Edit Service</h3>
                                </div><!-- /.box-header -->
                                <!-- form start -->
                                <form role="form" method="post" action="/admin/update_service">
                                    <div class="box-body">
                                    	<div class="form-group">
                                            <label>Select Service Category</label>
                                            <select class="form-control" name="serviceCateId" required>
                                                <option selected disabled value="">-- Select --</option>
                                               	
                                               	<c:forEach items="${categories}" var="category">
                                               	
	                                               	<c:if test="${category.cateId==service.serviceCateId}">
								    					<option value="${category.cateId}" selected="selected">${category.cateName}</option>
								    				</c:if>
								    				<c:if test="${category.cateId!=service.serviceCateId}">
								    					<option value="${category.cateId}" selected="selected">${category.cateName}</option>
								    				</c:if>
                                               	</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Service Name</label>
                                            <input type="text" class="form-control" name="serviceName" id="serviceName" value="${service.serviceName}" placeholder="service name">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputPassword1">Description</label>
                                            <input type="text" class="form-control" id="serviceDesc" name="serviceDesc" value="${service.serviceDesc}" placeholder="Description">
                                        </div>
                                        <input type="hidden" name="serviceId" value="${service.serviceId}"/>
                                    </div><!-- /.box-body -->

                                    <div class="box-footer">
                                        <button type="submit" class="btn btn-primary">Update</button>
                                    </div>
                                </form>
                            </div>
               	</section>
           </aside>
            <!-- /.right-side -->
        </div><!-- ./wrapper -->
        
        <!-- add new calendar event modal -->


        <%@include file="footer.jsp" %>
    </body>
</html>