<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<title>Dashboard</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">

		<c:if test="${not empty requestScope.errors}">
			<%--Display with a List of ObjectError --%>
			<c:forEach var="error" items="${requestScope.errors}">
				<div class="container">
					<div class="alert alert-danger">
						<c:out value="${error.getCode()}" />
					</div>
				</div>

			</c:forEach>
		</c:if>
		
		<c:if test="${not empty requestScope.listStringErrors}">
			<%-- Display a list of String --%>
			<c:forEach var="error" items="${requestScope.listStringErrors}">
				<div class="container">
					<div class="alert alert-danger">
						<c:out value="${error}" />
					</div>
				</div>

			</c:forEach>
		</c:if>

		<h1>Type</h1>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.types}" var="type">
					<tr>
						<td>${type.name}</td>
						<td><a href='editType?type=<c:out value="${type.id}"/>'>edit</a>
						<td><a href='deleteType?type=<c:out value="${type.id}"/>'>delete</a>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="resources/js/jquery.1.12.4.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>