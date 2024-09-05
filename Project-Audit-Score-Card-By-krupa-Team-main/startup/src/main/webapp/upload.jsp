<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Table and Upload File</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="logo_header.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<!-- Custom CSS -->
<style>
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
<body>
	<div class="container ">
	<div class="bg-light card mt-5  p-4 mb-5">
	  <%@ include file="logo_header.jsp"%>
	  <h4 class="text-center"><i class='far fa-file-excel' style="font-size:36px;"></i>&nbsp;&nbsp;&nbsp;<i class='fas fa-arrow-right' style='font-size:36px'></i>&nbsp;&nbsp;&nbsp;<i class="fa fa-database" style="font-size:36px;"></i></h4>
		<form action="uploadexcelfile" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="tableName">Select Table:</label> 
				<select class="form-control" name="tableName" id="tableName" required>
					<option value="">Select a Table</option>			
					<option value="all">All</option>
					<option value="startup">Startup</option>
					<option value="saatracker">Startup Assessment Automation Tracker</option>
					<option value="samanual">Startup Assessment Manual</option>
					<option value="sscard">Startup Score Card</option>
				</select>
			</div>
			<div class="form-group">
				<label for="fileUpload">Upload File:</label>
				<input type="file" class="form-control" name="excel" id="fileUpload" required>
			</div>
			<div class="container text-center">
				<a href="Home.jsp" class="btn btn-secondary">&nbsp;&nbsp;Back&nbsp;&nbsp;</a>
				<input type="submit" class="btn btn-primary" value="Upload">
			</div>
		</form>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>

</body>
</html>
