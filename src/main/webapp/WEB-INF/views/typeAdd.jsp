<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<h1>Add Type</h1>

		<c:if test="${not empty errors}">
			<%--Display with a List of ObjectError --%>
			<c:forEach var="error" items="${requestScope.errors}">
				<div class="container">
					<div class="alert alert-danger">
						<c:out value="${error.getCode()}" />
					</div>
				</div>

			</c:forEach>
		</c:if>

		<form:form method="POST" action="addType" modelAttribute="TypeDto">
			<fieldset>
				<div class="form-group">
					<form:label path="name">
								Type :
							</form:label>

					<form:input path="name" type="text" class="form-control" id="name"
						placeholder="Type" required="required" autofocus="autofocus" />
				</div>
			</fieldset>
			<div class="actions pull-right">
				<input type="submit" value="Add type" id="submit"
					class="btn btn-primary"> or <a
					href="${pageContext.request.contextPath}/homepage"
					class="btn btn-default">Cancel </a>
			</div>
			<input type="hidden" id="_csrf" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>