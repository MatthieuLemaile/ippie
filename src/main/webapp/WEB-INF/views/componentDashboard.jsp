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
	<jsp:include page="header.jsp"/>
	<div class="container">
	<h1>Component</h1>
	
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
	
	<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Type</th>
					<th>model Name</th>
					<th>State</th>
					<th>State Details</th>
					<th>introduced</th>
					<th>discontinued</th>
					<th>details</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.components}" var="compo">
					<tr>
						<td>${compo.name}</td>
						<td>${compo.type }</td>
						<td>${compo.model }</td>
						<td>${compo.state }</td>
						<td>${compo.stateDetails }</td>
						<td>${compo.introduced }</td>
						<td>${compo.discontinued }</td>						
						<td>${compo.details }</td>
						<td><a href='editComponent?ComponentId=<c:out value="${compo.id}"/>'>edit</a>
						<td><a href='deleteComponent?ComponentId=<c:out value="${compo.id}"/>'>delete</a>
					</tr>
				</c:forEach>
			</tbody>
			<tfooter>
				<tr>
					<th>Name</th>
					<th>Type</th>
					<th>model Name</th>
					<th>State</th>
					<th>State Details</th>
					<th>introduced</th>
					<th>discontinued</th>
					<th>details</th>
					<th></th>
				</tr>
			</tfooter>
		</table>
	</div>
	<script
		src="resources/js/jquery.1.12.4.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>