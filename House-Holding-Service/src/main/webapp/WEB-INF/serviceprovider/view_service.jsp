<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="UTF-8">
        <title>View Service</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        
        <%@include file="../admin/include-css.jsp" %>
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <!-- Header -->
        <%@ include file = "../admin/header.jsp" %>
        
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            
            <%@include file="sidebar.jsp" %>
            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <%@include file="../admin/content_header.jsp" %>
                
                <!-- Main content -->
                <section class="content">
               	<div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">My Service</h3>
                                    <label class="control-label"  for="inputSuccess"> <h4>${response}</h4></label>
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive no-padding">
                                    <table class="table table-hover">
                                        <tr>
                                            <th>ID</th>
                                            <th>Category_Name</th>
                                            <th>Service_Name</th>
                                            <th>Description</th>
                                            <th>Action</th>
                                        </tr>
                                        <c:forEach items="${skills}" var="skill">
                                        <tr>
                                            <td >${skill.services.serviceId}</td>
                                            <td>${skill.serviceCategory.cateName}</td>
                                            <td>${skill.services.serviceName}</td>
                                            <td>${skill.services.serviceDesc}</td>
                                            <td class="center">
									            <a class="btn btn-danger" href="/serviceprovider/delete_skill?skill_id=${skill.services.serviceId}">
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
        
<%@include file="../admin/footer.jsp" %>
</body>

</html>