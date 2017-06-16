<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>${exception.message}</h3>
<h1>${msg}</h1>

	<table>
	<tr>
		<td>Student Name:</td>
		<td>${student.studentName}</td>
	</tr>
	<tr>
		<td>student Hobby</td>
		<td>${student.studentHobby}</td>
	</tr>
	<tr>
		<td>student Mobile</td>
		<td>${student.studentMobile}</td>
	</tr>
	<tr>
		<td>student DOB</td>
		<td>${student.studentDOB}</td>
	</tr>
	<tr>
		<td>student Skill</td>
		<td>${student.studentSkills}</td>
	</tr>
	<tr>
		<td>student Address</td>
		<td>$Country:${student.studentAddress.country}
		$City:${student.studentAddress.city}
		$Street:${student.studentAddress.street}
		$Pincode:${student.studentAddress.pincode}</td>
	</tr>
	</table>
</body>
</html>