<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="UTF-8">
        <title>Service provider</title>
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
                                    <h3 class="box-title">Service Providers</h3>
                                    
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive no-padding">
                                    <table class="table table-hover">
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>E-Mail</th>
                                            <th>Mobile_No</th>
                                            <th>Area</th>
                                            <th>Address</th>
                                            <th>Status</th>
                                            
                                        </tr>
                                        
                                        <c:forEach items="${providers}" var="provid">
                                        <tr>
                                            <td>${provid.serviceProviderId}</td>
                                            <td>${provid.name}</td>
                                            <td>${provid.email}</td>
                                            <td>${provid.mobileNo}</td>
                                            <td>${provid.area}</td>
                                            <td>${provid.address}</td>
                                            
                                           	<c:choose>
											    <c:when test="${provid.status==1}">
											        <td><a href="/admin/change_user_status?status_id=${provid.status}&user_id=${provid.serviceProviderId}&user_type=2">
											        
											        <span class="label label-success">Active</span></a></td>
											    </c:when>    
											    <c:otherwise>
											        <td><a href="/admin/change_user_status?status_id=${provid.status}&user_id=${provid.serviceProviderId}&user_type=2">
											        <span class="label label-danger">Block</span></a></td>
											    </c:otherwise>
											</c:choose>
                                          
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