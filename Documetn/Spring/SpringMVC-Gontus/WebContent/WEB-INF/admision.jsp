<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${msg}</h1>
<form:errors path="student.*"/>
	<form action="/SpringMVC-Gontus/submitadmissionform" method="post">
		<table>
		<tr><td>Stu:Name:</td><td><input type="text" name="studentName"/></td></tr>
		<tr><td>Hobby:</td><td><input type="text" name="studentHobby"/></td></tr> 
		<tr><td>Student Mobile:</td><td><input type="text" name="studentMobile"/></td></tr> 
		<tr><td>DOB:</td><td><input type="text" name="studentDOB"/></td></tr> 
		<tr><td>Skill:</td><td>
		
			<select name="studentSkills" multiple>
				<option value="Core Java">Core Java</option>
				<option value="Spring">Sprig MVC</option>
				<option value="competitive">Competitive</option>
			</select>
		
		</td></tr> 
		 
		
		</table>
		
		<table>
		<tr><td>Country:</td><td><input tupe="text" name="studentAddress.country"/></td></tr>
		<tr><td>City:</td><td><input type="text" name="studentAddress.city"/></td></tr> 
		<tr><td>Street:</td><td><input type="text" name="studentAddress.street"/></td></tr> 
		<tr><td>Pincode:</td><td><input type="text" name="studentAddress.pincode"/></td></tr> 
		
		 
		<tr><td colspan="2"><input type="submit" value="click"></td></tr>
		</table>
	</form>
</body>
</html>