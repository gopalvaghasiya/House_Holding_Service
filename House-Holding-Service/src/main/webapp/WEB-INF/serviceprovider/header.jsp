<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav id="mainNav"
	class="navbar navbar-default navbar-fixed-top navbar-custom">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
	 <div class="header">
		<div class="navbar-header page-scroll">
			<img class="img-responsive" height="40px" width="40px"
				src="/resources/user/img/profile.png" alt="">
		</div>
		<div class="navbar-header page-scroll">
			<a class="navbar-brand" href="#page-top"> House Holding Service</a>
		</div>
	</div>
	<div class="top-menu">
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				
				<c:set var="serviceprovider" value="serviceprovider" />
				
				<c:choose>
				<c:when test="${user_role ne serviceprovider}">
					<li class="page-scroll">
					 <a href="/serviceprovider/register">Resister</a>
					</li>
					
				</c:when>
				<c:otherwise>
					<li class="page-scroll">
					  <a href="#">${user_name}</a>
					</li>
					<li class="page-scroll">
					 <a href="/serviceprovider/logout">Logout</a>
					</li>
				</c:otherwise>
				</c:choose>
				
			</ul>
		</div>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>