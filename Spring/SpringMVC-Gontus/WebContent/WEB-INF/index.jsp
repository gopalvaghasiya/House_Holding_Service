<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset = "UTF-8">
 <title>externalStyle.html</title>
 <link href="<c:url value="/resources/css/ec.css"/>" rel="stylesheet" type="text/css" />
<body >
 <h1 > External Style</h1>
 <p>
  This page has styles set for paragraphs, body, and header 1.
 </p>
	
	<%@ include file="table.jsp" %>	

</body>
</html>
	