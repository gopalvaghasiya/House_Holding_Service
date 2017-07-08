<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="UTF-8">
        <title>Add service</title>
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
					<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Add Service</h3>
				<p>${response}</p>
			</div>
			<!-- /.box-header -->
			<!-- form start -->
			<form role="form" method="POST" action="/serviceprovider/add_service">
				<div class="box-body">
					<div class="form-group">
						<label>Select Service Category</label>
						 <select class="form-control"	name="cateId" id="cateId" onchange="selectCate()" required>
							<option selected disabled value="">---select---</option>
							<c:forEach items="${categories}" var="cate">
								<option value="${cate.cateId}">${cate.cateName}</option>
							</c:forEach>
						</select>

					</div>
					<div class="form-group" id="content">
						<label>Select Service</label>
						 <select class="form-control"	name="service_id" required>
							<option selected disabled value="">---select---</option>
						</select>

					</div>

				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="submit" class="btn btn-primary">Add</button>
				</div>
			</form>
		</div>			
                                    
               	</section>
           </aside>
            <!-- /.right-side -->
        </div><!-- ./wrapper -->
        
        <%@include file="../admin/footer.jsp" %>
        
        <script type="text/javascript">
        
        function selectCate(){
        	var cateId=document.getElementById("cateId").value;
        	
        	var service = new XMLHttpRequest();

        	service.open('GET', 'http://localhost:8080/rest_select_services_by_cate_id/'+cateId);
        	
        	service.onload = function() {
        		var data = JSON.parse(service.responseText);

        		setServices(data);
        	};
        	service.send();
        }
        function setServices(data){
        	var str = "";
        	
        	str += ""
        			+ "<label>Select Service</label>"
        			+ "<select class='form-control'	name='service_id' required>"
        			+ "<option selected disabled value=''>---select---</option>"

        	for (i = 0; i < data.length; i++) {
        		str += "<option value=" + data[i].serviceId + ">" + data[i].serviceName+"</option>";
        	}

        	str += "</select>"
        			+ "</div>"

        	document.getElementById("content").innerHTML = "";
        	document.getElementById("content").insertAdjacentHTML('beforeend',
        			str);
        }
        </script>
    </body>
</html>