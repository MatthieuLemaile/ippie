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
<title>Ajout Composant</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h1>Ajout composant</h1>

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

		<form:form method="POST" action="addComponent" modelAttribute="ComponentDto">
			<fieldset>
				<div class="form-group">
					<form:label path="name">
								Composant :
							</form:label>

					<form:input path="name" type="text" class="form-control" id="name"
						placeholder="Composant" required="required" autofocus="autofocus" />
				</div>
				
				<div class="form-group">
					<form:label path="modelId">
								Model :
							</form:label>

					<form:select class="form-control" path="modelId" id="modelId">
								<form:option value="0">--</form:option>
								<c:forEach var="model" items="${requestScope.models}">
									<form:option value="${model.id}">
										<c:out value="${model.name}" />
										<c:out value="(${model.type})" />
									</form:option>
								</c:forEach>
					</form:select>
				</div>
				
				<div class="form-group">
					<form:label path="introduced">
								Date d'acquisition :
							</form:label>

					<form:input path="introduced" type="date" class="form-control" id="introduced"
						required="required"/>
				</div>
				
				<div class="form-group">
					<form:label path="discontinued">
								Date de retrait :
							</form:label>

					<form:input path="discontinued" type="date" class="form-control" id="discontinued"/>
				</div>
				
				<div class="form-group">
					<form:label path="details">
								Détails :
							</form:label>

					<form:input path="details" type="text" class="form-control" id="details"
						placeholder="additional details"/>
				</div>
				
				<%-- <div class="form-group">
					<form:label path="stateId">
								État :
							</form:label>

					<form:select class="form-control" path="stateId" id="stateId">
								<form:option value="0">--</form:option>
								<c:forEach var="state" items="${requestScope.states}">
									<form:option value="${state.id}">
										<c:out value="${state.name}" />
									</form:option>
								</c:forEach>
					</form:select>
				</div> --%>
				
				<div class="form-group">
					<form:label path="stateDetails">
								Plus de détails sur l'état :
							</form:label>

					<form:input path="stateDetails" type="text" class="form-control" id="stateDetails"
						placeholder="additional details about the state" />
				</div>
				
			</fieldset>
			<div class="actions pull-right">
				<input type="submit" value="Add Component" id="submit"
					class="btn btn-primary"> or <a
					href="${pageContext.request.contextPath}/homepage"
					class="btn btn-default">Cancel </a>
			</div>
		</form:form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>