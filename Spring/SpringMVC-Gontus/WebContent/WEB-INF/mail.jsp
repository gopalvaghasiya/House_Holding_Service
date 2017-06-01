<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="/SpringMVC-Gontus/sendmail">
	
		<tr>
			<td>To:</td>
			<td><input type="text" name="recipient"></td>
		</tr>
		<tr>
			<td>Subject:</td>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<td>Meggage:</td>
			<td><input type="text" name="message"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" name="send"></td>
			
		</tr>
	</form>
</body>
</html>