<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
	<meta name="generator" content="Hugo 0.84.0">
	<title>UiTM | Academic Inventory System</title>
	<link rel="icon" href="assets/images/UiTM_logo.png">
	<link href="assets/css/bootstrap.css" rel="stylesheet">
    <script>
        window.onload = function() {
    		document.getElementById("date_id").valueAsDate = new Date();
    	}
    </script>
</head>

<body style="background-color: #f2f2f2;">
	<div class="container col-lg-8" style="max-width: 950px;">
		<div class="card my-4">
			<div class="card-header fw-bold">Issue Report</div>
			<div class="card-body">
				<div class="row g-5">
					<form action="Add_Complaint_Servlet" method="post" class="needs-validation" novalidate onsubmit="send_email()">
						<c:if test="${session_status != null}">
							<c:choose>
								<c:when test="${session_status == 'Successfully added'}">
									<div class="text-center alert alert-success" role="alert">
										<c:out value="${session_status}"></c:out>
									</div>
								</c:when>
								<c:otherwise>
									<div class="text-center alert alert-danger" role="alert">
										<c:out value="${session_status}"></c:out>
									</div>
								</c:otherwise>
							</c:choose>
							<c:set var="session_status" value="${null}"></c:set>
						</c:if>
						<div class="row g-3">
							<div class="col-md-6">
								<label class="form-label">Full Name</label>
								<input style="text-transform: capitalize;" name="complaint_name" id="full_name" type="text" class="form-control" required>
								<div class="invalid-feedback"> Lecturer name is required. </div>
							</div>
							<div class="col-md-6">
								<label class="form-label">Phone Number</label>
								<input name="complaint_phonenum" id="phone_number" type="text" class="form-control" required>
								<div class="invalid-feedback"> Phone number is required. </div>
							</div>
							<div class="col-md-6">
								<label class="form-label">Location</label>
								<input name="complaint_location" id="location_id" type="text" class="form-control" value="${locationid}" readonly>
								<div class="invalid-feedback"> Location is required. </div>
							</div>
							<div class="col-md-6">
								<label class="form-label">Date</label>
								<input name="complaint_date" id="date_id" type="date" class="form-control">
								<input name="complaint_status" type="hidden" class="form-control" value="Not Complete">
								<div class="invalid-feedback"> Date is required. </div>
							</div>
							<div class="col-12">
								<label class="form-label">Detail</label>
								<textarea name="complaint_detail" id="detail" class="form-control" rows="5" style="resize: none;" required></textarea>
								<div class="invalid-feedback"> Detail is required. </div>
							</div>
							<hr class="my-4">
							<button class="w-100 btn btn-primary btn-lg" type="submit">Submit</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/form-validation.js"></script>
	<script>
		function send_email() {
			let full_name = document.getElementById("full_name").value;
			let phone_number = document.getElementById("phone_number").value;
			let location_id = document.getElementById("location_id").value;
			let detail = document.getElementById("detail").value;

			var data = JSON.stringify({
				"receiver_name" : "Khairil Azuan",
				"receiver_email" : "kxairilazuan@gmail.com",
				"compl_fullname" : full_name,
				"compl_phonenum" : phone_number,
				"compl_location" : location_id,
				"compl_detail"   : detail
			});

			var xhr = new XMLHttpRequest();

			xhr.addEventListener("readystatechange", function() {
				if (this.readyState === 4) {
					console.log(this.responseText);
				}
			});

			xhr.open("POST",
					"https://nodemailer-email.herokuapp.com/complaint_form");
			xhr.setRequestHeader("Content-Type", "application/json");

			xhr.send(data);
		}
	</script>
</body>

</html>