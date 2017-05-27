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
	<div class="container">
	<h1>Type</h1>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.types}" var="type">
							<tr>
								<td>${type.id}</td>
								<td>${type.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>