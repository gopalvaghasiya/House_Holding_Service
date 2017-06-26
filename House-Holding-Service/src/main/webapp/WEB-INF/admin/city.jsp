<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>View City</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>

<%@include file="include-css.jsp"%>
</head>
<body class="skin-blue">
	<!-- header logo: style can be found in header.less -->
	<!-- Header -->
	<%@ include file="header.jsp"%>

	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!-- Left side column. contains the logo and sidebar -->

		<%@include file="sidebar.jsp"%>
		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side"> <!-- Content Header (Page header) -->
		<%@include file="content_header.jsp"%> <!-- Main content -->
		<section class="content">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">City</h3>
						<h4>${response}</h4>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive no-padding">
						<table class="table table-hover">
							<tr>
								<th>ID</th>
								<th>City_Name</th>
								<th>Action</th>
							</tr>

							<c:forEach items="${citys}" var="city">
								<tr>
									<td>${city.cityId}</td>
									<td>${city.cityName}</td>
									<td class="center"><a class="btn btn-info"
										href="/admin/edit_city?city_id=${city.cityId}"> <i
											class="glyphicon glyphicon-edit icon-white"></i> Edit
									</a> <a class="btn btn-danger"
										href="/admin/delete_city?city_id=${city.cityId}"> <i
											class="glyphicon glyphicon-trash icon-white"></i> Delete
									</a>
										<button type="button" class="btn btn-info btn-lg"
											data-toggle="modal" data-target="#myModal">Edit</button></td>

								</tr>
							</c:forEach>
						</table>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
		</div>
		</section> </aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
	<!-- Test start -->


	<div class="container">


		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<form method="get" action="/admin/home">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Edit City</h4>
					</div>
					<div class="modal-body" id="divid">
						
						<div class="form-group">
							<label >City Name</label>
							 <input	type="text" name="cityName" id="cityName" class="form-control" placeholder="City name">
							 <input type="hidden" name="cityId"/>
						</div>
						<p>Some text in the modal.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Add</button>
					</div>
				</div>
				</form>
			</div>
		</div>

	</div>


	<!-- Test End -->
	<!-- add new calendar event modal -->
	<%@include file="footer.jsp"%>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			
			$('table button').click(function() {

				var tr = $(this).closest('tr');
				var id = tr.children('td:eq(0)').text(); //get the text from first col of current row
				var name = tr.children('td:eq(1)').text();
				document.getElementById("cityName").value = name;
				$("#divid").find("p").html(id);
				
				var details = new XMLHttpRequest();
				var content=document.getElementById("divid");
				details.open('GET','https://learnwebcode.github.io/json-example/animals-1.json');

				details.onload=function(){
					var data=JSON.parse(details.responseText);

					//renderHTML(data);
				
				
				var string="";

				for(i=0;i<data.length;i++){
					string+= "<p>" + data[i].name + " is a " + data[i].species + ".</p>";
				}

				content.insertAdjacentHTML('beforeend',string);};
				details.send();

			});
		});
	</script>
</body>
</html>