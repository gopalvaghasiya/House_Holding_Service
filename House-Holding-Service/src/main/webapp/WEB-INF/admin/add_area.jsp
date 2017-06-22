<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Area</title>
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
                                    <h3 class="box-title">Add Area</h3>
                                    <p>${response}</p>
                                </div><!-- /.box-header -->
                                <!-- form start -->
                                <form role="form" method="POST" action="/admin/add_area">
                                    <div class="box-body">
                                    	<div class="form-group">
                                            <label>Select City</label>
                                            
                                            <select class="form-control" name="cityId" required>
                                            <option selected disabled value="">---select---</option>
                                                <c:forEach items="${citys}" var="city">
                                                <option value="${city.cityId}">${city.cityName}</option>
                                                </c:forEach>
                                            </select>
                                       
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Area Name</label>
                                            <input type="text" name="areaName" class="form-control" id="exampleInputEmail1" placeholder="Area name" required>
                                        </div>
                                        
                                    </div><!-- /.box-body -->

                                    <div class="box-footer">
                                        <button type="submit" class="btn btn-primary">Add</button>
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