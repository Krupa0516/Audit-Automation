<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Startup Form</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="logo_header.css">
</head>
<body>
	<div class="container border border-outline-black bg-light mt-5 card">
		<div class="card-body">
			<%@ include file="logo_header.jsp"%>
			<h1 class="text-center mt-5 card border border-outline-info">Startup
				Information Form</h1>
			<form action="updateservlet" method="post" class="card-body">
				<c:forEach items="${startup_data }" var="startup">
					<div class="form-group">
						<label for="startupname">Startup Name:</label> <input type="text"
							id="startupname" name="startupname" class="form-control"
							value="${startup.startupname }" disabled>
					</div>
					<div class="form-group">
						<label for="srno">Serial Number:</label> <input type="number"
							id="srno" name="srno" class="form-control"
							value="<c:out value="${startup.srno }" />" disabled>
					</div>
					<div class="form-group">
						<label for="startupcategory">Startup Category:</label> <input
							type="text" id="startupcategory" name="startupcategory"
							class="form-control" maxlength="100"
							value="${startup.startupcategory }">
					</div>
					<div class="form-group">
						<label for="startupcode">Startup Code:</label> <input type="text"
							id="startupcode" name="startupcode" class="form-control"
							maxlength="20" value="<c:out value="${startup.startupcode }" />">
					</div>
					<div class="form-group">
						<label for="website">Website:</label>
						<textarea id="website" name="website" class="form-control"
							maxlength="100"><c:out value="${startup.website }" /></textarea>
					</div>
					<div class="container text-center mt-4">
						<a href="view.jsp" class="btn btn-secondary">Back</a>
						&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary">Save</button>
					</div>
				</c:forEach>
			</form>
		</div>
	</div>
</body>
</html>
