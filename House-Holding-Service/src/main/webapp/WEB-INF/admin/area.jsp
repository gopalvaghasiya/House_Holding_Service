<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="UTF-8">
        <title>View Area</title>
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
                                    <h3 class="box-title">Area</h3>
                                    <label class="control-label"  for="inputSuccess"> <h4>${response}</h4></label>
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive no-padding">
                                    <table class="table table-hover">
                                        <tr>
                                            <th>ID</th>
                                            <th>City_Name</th>
                                            <th>Area_Name</th>
                                            <th>Action</th>
                                        </tr>
                                        <c:forEach items="${areas}" var="area">
                                        <tr>
                                            <td >${area.area.areaId}</td>
                                            <td>${area.city.cityName}</td>
                                            <td>${area.area.areaName}</td>
                                            <td class="center">
												<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">
													<i class="glyphicon glyphicon-edit icon-white"></i> Edit
												</button>
									            <a class="btn btn-danger" href="/admin/delete_area?area_id=${area.area.areaId}">
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
        
        <!-- Test start -->


	<div class="container">


		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<form method="post" action="/admin/update_area">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Edit Area</h4>
						</div>
						<div class="modal-body" id="content">

							<div class="form-group">
								<label>Select City</label>
								
								<div id="cities">
								</div>	
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Area Name</label>
								 <input type="text" name="areaName" class="form-control"
									id="areaName" value="" placeholder="Area name" required>
							</div>
							
							<div class="box-footer">
								<button type="submit" class="btn btn-primary">Update</button>
							</div>
							<input type="hidden" value="" name="areaId" id="areaId"/>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>

					</div>
				</form>
			</div>
		</div>

	</div>


	<!-- Test End -->
        <!-- add new calendar event modal -->


        

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript">
	var cities=document.getElementById("cities");
	var content=document.getElementById("content");
		$(document)
				.ready(
						function() {

							$('table button')
									.click(
											function() {
												
												document.getElementById("cities").innerHTML = ""
												
												var tr = $(this).closest('tr');
												var id = tr.children('td:eq(0)').text(); //get the text from first col of current row

												var ct = new XMLHttpRequest();
												var area=new XMLHttpRequest();
												
												

												area.open('GET','http://localhost:8080/rest_select_area_by_id/'+id);
												var cid=0;
												
													area.onload = function(){
														if(area.statusText=="Not Found"){
															var str="<p> Record not Found </p>";
															content.innerHTML = ""
															content.insertAdjacentHTML('beforeend',str);
														}
														else{
															var area_data=JSON.parse(area.responseText);
															
															ct.open('GET','http://localhost:8080/rest_select_all_city');
															ct.onload = function() {
																var data = JSON.parse(ct.responseText);
					
																setSelect(area_data.cityId,data);
															};
															ct.send();
															
															document.getElementById("areaId").value=area_data.areaId;
															document.getElementById("areaName").value=area_data.areaName;
														}
													};
													area.send();
												
											});
						});
		
		function setSelect(id,data){
			var str="";
			
			str+="<select class=form-control name=cityId id=cityId required>"
			+"<option selected disabled value=>---select---</option>";
			
			for(i=0;i<data.length;i++){
				
				if(data[i].cityId!=id)
				{
					str+="<option value="+data[i].cityId+">"+data[i].cityName+"</option>";
					continue;
				}
				
				str+="<option value="+data[i].cityId+" selected=selected>"+data[i].cityName+"</option>";
			}
			str+="</select>";
			
			cities.insertAdjacentHTML('beforeend',str);
		}
	</script>
	<%@include file="footer.jsp" %>
</body>

</html>