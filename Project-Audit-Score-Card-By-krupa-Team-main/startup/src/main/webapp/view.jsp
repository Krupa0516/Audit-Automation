<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@page import="com.startup.model.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display The Data</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="logo_header.css">
<script src='https://kit.fontawesome.com/a076d05399.js'
	crossorigin='anonymous'></script>
<style type="text/css">
.test {
	width: 100px;
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

.test:hover {
	overflow: visible;
	white-space: normal;
	height: auto;
	word-wrap: break-word;
	white-space: pre-wrap;
}

.container {
	margin-top: 50px;
}

.form-group label {
	font-weight: bold;
}

.btn {
	margin-top: 10px;
}
</style>
</head>
<body class="body">
	<div class="container">
		<div class="bg-light card mt-5 p-4 mb-5">
			  <%@ include file="logo_header.jsp"%>
			<h4 class="text-center">
				<i class="fa fa-database" style="font-size: 36px;"></i>
			</h4>
			<form action="getdata" class=" " method="post">
				<div class="form-group">
					<label for="tableName">Select Table:</label> <select
						class="form-control" name="tableName" id="tableName" required>
						<option value="">Select a Table</option>
						<option value="startup">Startup</option>
						<option value="saatracker">Startup Assessment Automation
							Tracker</option>
						<option value="samanual">Startup Assessment Manual</option>
						<option value="sscard">Startup Score Card</option>
					</select>
				</div>
				<div class="form-group">
					<label for="startupname">Select StartupName:</label> <select
						name="startupname" id="startupname" required class="form-control">
						<option value="">Select a StartupName</option>
						<c:forEach items="${startupnames}" var="startup">
							<option value="<c:out value="${startup }" />"><c:out
									value="${startup }" /></option>
						</c:forEach>
					</select>
				</div>
				<div class="container text-center mt-2">
					<a href="Home.jsp" class="  btn btn-secondary">Back</a> <input
						type="submit" class="btn btn-primary" value="View">
				</div>

			</form>
		</div>

		<c:if test="${not empty tablename }">
			<c:if test="${tablename=='startup' }">
				<div class="container text-center">
					<table border="1" class="table table-bordered border-primary ">
						<thead class="bg-light">
							<tr>
								<th colspan="7">Start-up</th>
							</tr>
							<tr>
								<th style="background-color: rgba(0, 255, 0, 0.1);">Update</th>
								<th style="background-color: rgba(255, 0, 0, 0.1);">Delete</th>
								<th>Start-up-Name</th>
								<th>Sr.No.</th>
								<th>Start-up-Category</th>
								<th>Start-up-Code</th>
								<th>Web-site</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${startup_data }" var="startup">
								<tr>
									<td><a href="update.jsp" class="btn btn-outline-success">Edit</a></td>
									<td><a href="<%=request.getContextPath()%>/deleteservlet"
										class="btn btn-outline-danger">delete</a></td>
									<td><pre class="test">${startup.startupname }</pre></td>
									<td><pre class="test">${startup.srno }</pre></td>
									<td><pre class="test">${startup.startupcategory }</pre></td>
									<td><pre class="test">${startup.startupcode }</pre></td>
									<td><pre class="test">
											<a class="text-decoration-none" href="${startup.website }">${startup.website }</a>
										</pre></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${tablename=='saatracker' }">
				<div class="container text-center">
					<button class="btn btn-outline-warning"
						onclick="location.href='DownloadPDFServlet'">Download PDF</button>
				</div>
				<div class="container text-center">
					<table border="1" class="table table-bordered border-primary ">
						<thead>
							<tr>
								<th colspan="6">Startup Assessment Automation Tracker</th>
							</tr>
							<tr>
								<th>Srno</th>
								<th>Start-up-Name</th>
								<th>Date-Of-Process</th>
								<th>linkURL</th>
								<th>Process Area</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${saatracker_data}" var="saatracker">
								<tr>
									<td><pre class="test">${saatracker.id }</pre></td>
									<td><pre class="test">${saatracker.startupname }</pre></td>
									<td><pre class="test">${saatracker.dateofprocess }</pre></td>
									<td><pre class="test">
											<a class="text-decoration-none" href="${saatracker.linkURL }">${saatracker.linkURL }</a>
										</pre></td>
									<td><pre class="test">${saatracker.processarea }</pre></td>
									<td><pre class="test">${saatracker.status }</pre></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${tablename=='samanual' }">
				<div class="container text-center">
					<table border="1" class="table table-bordered border-primary ">
						<thead>
							<tr>
								<th colspan="7">Startup Assessment Manual</th>
							</tr>
							<tr>
								<th>Id</th>
								<th>Start-up-Name</th>
								<th>Process Area</th>
								<th>Number Of Link</th>
								<th>Actual Score</th>
								<th>Remark</th>
								<th>Update</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${samanual_data}" var="samanual">
								<form action="update_data" method="post">
									<tr>
										<td><pre class="test">${samanual.id }</pre></td>
										<td><pre class="test">${samanual.startupname }</pre></td>
										<td><pre class="test">${samanual.processarea }</pre></td>
										<td><pre class="test">${samanual.number_of_link }</pre></td>
										<td><input class="form-control" type="text"
											name="actualscore" value="${samanual.actualscore}"></td>
										<td><textarea class="form-control" name="remark">${samanual.remark}</textarea></td>
										<td><input type="hidden" name="tableName"
											value="samanual" /><input type="hidden" name="startupname"
											value="${samanual.startupname }" />
											<button type="submit" class="btn btn-outline-success"
												name="id" value="${samanual.id}">Save</button></td>
									</tr>
								</form>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${tablename=='sscard' }">
				<div class="container text-center">
					<button class="btn btn-outline-warning"
						onclick="location.href='DownloadPDFServlet'">Download PDF</button>
				</div>
				<div class="container text-center">
					<table border="1" class="table table-bordered border-primary ">
						<thead class="thead">
							<tr>
								<th colspan="10">Startup Score Card</th>
							</tr>
							<tr>
								<th>Srno</th>
								<th>Start-up-Name</th>
								<th>Dimension</th>
								<th>Perspective</th>
								<th>Process Area</th>
								<th>Weight</th>
								<th>Max-score</th>
								<th>Actual score</th>
								<th>Remark</th>
								<th>Date Process</th>
							</tr>
						</thead>
						<tbody class="tbody">
							<c:forEach items="${sscard_data}" var="sscard">
								<tr>
									<td><pre class="test">${sscard.id }</pre></td>
									<td><pre class="test">${sscard.startupname }</pre></td>
									<td><pre class="test">${sscard.dimension }</pre></td>
									<td><pre class="test">${sscard.perspective }</pre></td>
									<td><pre class="test">${sscard.processarea }</pre></td>
									<td><pre class="test">${sscard.weight }</pre></td>
									<td><pre class="test">${sscard.maxscore }</pre></td>
									<td><pre class="test">${sscard.actualscore }</pre></td>
									<td><pre class="test">${sscard.remark }</pre></td>
									<td><pre class="test">${sscard.date_process }</pre></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

		</c:if>
	</div>
</body>
</html>