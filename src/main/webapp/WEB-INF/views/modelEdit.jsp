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
<title>Edit Model</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h1>Add Model</h1>

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

		<form:form method="POST" action="editModel" modelAttribute="ModelDto">
			<fieldset>
				<div class="form-group">
					<form:label path="name">
								Model :
							</form:label>

					<form:input path="name" type="text" class="form-control" id="name"
						placeholder="Model" required="required" autofocus="autofocus" />
				</div>
				
				<div class="form-group">
					<form:label path="typeId">
								Type :
							</form:label>

					<form:select class="form-control" path="typeId" id="typeId">
								<form:option value="0">--</form:option>
								<c:forEach var="type" items="${requestScope.types}">
									<form:option value="${type.id}">
										<c:out value="${type.name}" />
									</form:option>
								</c:forEach>
					</form:select>
				</div>
			</fieldset>
			<div class="actions pull-right">
				<input type="submit" value="Edit Model" id="submit"
					class="btn btn-primary"> or <a
					href="${pageContext.request.contextPath}/modelDashboard"
					class="btn btn-default">Cancel </a>
			</div>
		</form:form>
	</div>
	<script
		src="resources/js/jquery.1.12.4.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>