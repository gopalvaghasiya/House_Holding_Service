<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit service category</title>
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
                                    <h3 class="box-title">Edit Service Category</h3>
                                    
                                </div><!-- /.box-header -->
                                <!-- form start -->
                                <form role="form" method="post" enctype="multipart/form-data" action="/admin/update_sercate">
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Category Name</label>
                                            <input type="text" class="form-control" id="cateName" name="cateName" value="${sercate.cateName}" placeholder="category name" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputPassword1">Description</label>
                                            <input type="text" class="form-control" id="cateDesc" name="cateDesc" value="${sercate.cateDesc}" placeholder="Description" required>
                                        </div>
                                        <div class="form-group">
                                        <img src="${sercate.cateImg}" height="40px" width="50px" />
                                        <br>
                                            <label for="exampleInputFile">Image</label>
                                            <input type="file" name="file" value="${sercate.cateImg}" required>
                                            <p class="help-block">set image so user can identify service.</p>
                                        </div>
                                        <input type="hidden" name="cateId" value="${sercate.cateId}"/>
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