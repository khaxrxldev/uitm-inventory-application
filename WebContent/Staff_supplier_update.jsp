<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	if(session.getAttribute("session_idnum") == null) {
		response.sendRedirect("index.jsp");
	}
%>
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
	<link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
	<div class="sidebar p-3 bg-accent">
		<div class="staff-name px-4 fs-5 fw-bold">
			<c:out value="${session_name}"></c:out>
		</div>
		<div class="staff-level px-4 fs-6 text-secondary mb-2">
			<c:out value="${session_level}"></c:out>
		</div>
		<hr>
		<ul class="mt-4 nav nav-pills flex-column mb-auto">
			<li>
				<a href="Redirect_Servlet?action=location" class="nav-link link-dark">
					<img src="assets/icons/location.svg" class="pb-1 px-2">
					<label class="link-name">Location</label>
				</a>
			</li>
			<li>
				<a href="Redirect_Servlet?action=equipmentav" class="nav-link link-dark">
					<img src="assets/icons/monitor.svg" class="pb-1 px-2">
					<label class="link-name">Audio Visual</label>
				</a>
			</li>
			<li>
				<a href="Redirect_Servlet?action=equipmentac" class="nav-link link-dark">
					<img src="assets/icons/bar_bottom.svg" class="pb-1 px-2">
					<label class="link-name">Academic</label>
				</a>
			</li>
			<li>
				<a href="Redirect_Servlet?action=equipmenttype" class="nav-link link-dark">
					<img src="assets/icons/devices.svg" class="pb-1 px-2">
					<label class="link-name">Equipment Type</label>
				</a>
			</li>
			<li>
				<a href="Redirect_Servlet?action=maintenance" class="nav-link link-dark">
					<img src="assets/icons/settings.svg" class="pb-1 px-2">
					<label class="link-name">Maintenance</label>
				</a>
			</li>
			<li>
				<a href="Redirect_Servlet?action=report" class="nav-link link-dark">
					<img src="assets/icons/folder.svg" class="pb-1 px-2">
					<label class="link-name">Report</label>
				</a>
			</li>
			<li>
				<a href="Redirect_Servlet?action=department" class="nav-link link-dark">
					<img src="assets/icons/building.svg" class="pb-1 px-2">
					<label class="link-name">Department</label>
				</a>
			</li>
			<li>
				<a href="Redirect_Servlet?action=supplier" class="nav-link active">
					<img src="assets/icons/file_white.svg" class="pb-1 px-2">
					<label class="link-name">Supplier</label>
				</a>
			</li>
			<c:if test="${session_level == 'Administrator'}">
				<li>
					<a href="Redirect_Servlet?action=staff" class="nav-link link-dark">
						<img src="assets/icons/group.svg" class="pb-1 px-2">
						<label class="link-name">Staff</label>
					</a>
				</li>
			</c:if>
			<li>
				<a href="Redirect_Servlet?action=account" class="nav-link link-dark">
					<img src="assets/icons/user.svg" class="pb-1 px-2">
					<label class="link-name">Account</label>
				</a>
			</li>
			<li>
				<a href="Redirect_Servlet?action=complaint" class="nav-link link-dark">
					<img src="assets/icons/mail.svg" class="pb-1 px-2">
					<label class="link-name">Complaint</label>
				</a>
			</li>
			<li>
				<a href="Logout_Servlet" class="nav-link link-dark" data-bs-toggle="modal" data-bs-target="#exampleModal"> <img src="assets/icons/log_out.svg" class="pb-1 px-2">
					<label class="link-name">Log out</label>
				</a>
			</li>
		</ul>
	</div>
	<div class="content p-3">
		<button onclick="window.location.href='Redirect_Servlet?action=supplier'" class="btn btn-sm btn-secondary bg-accent-light" style="border: none;"><img src="assets/icons/short_left.svg" class="py-1"></button>
		<div class="py-3 fs-3 fw-bold">Supplier</div>
		<div class="card mb-2 w-100">
			<div class="card-header fw-bold">Add Supplier</div>
			<div class="card-body">
				<div class="container">
					<div class="row g-5">
						<form action="Update_supplier_Servlet" method="post" class="needs-validation" novalidate>
							<c:if test="${session_status != null}">
								<c:choose>
									<c:when test="${session_status == 'Successfully updated'}">
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
								<div class="col-6">
									<label class="form-label">ID Number</label>
									<input name="supplier_idnum" value="<c:out value="${supplierinfo.supplier_idnum}"></c:out>" type="text" class="form-control" readonly>
									<div class="invalid-feedback"> ID number is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Name</label>
									<input style="text-transform: capitalize;" name="supplier_name" value="<c:out value="${supplierinfo.supplier_name}"></c:out>" type="text" class="form-control" required>
									<div class="invalid-feedback"> Name is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Phone number</label>
									<input name="supplier_phonenum" value="<c:out value="${supplierinfo.supplier_phonenum}"></c:out>" type="text" class="form-control">
									<div class="invalid-feedback"> Phone number is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Email</label>
									<input name="supplier_email" value="<c:out value="${supplierinfo.supplier_email}"></c:out>" type="text" class="form-control">
									<div class="invalid-feedback"> Email is required. </div>
								</div>
								<div class="col-12">
									<label class="form-label">Address</label>
									<textarea name="supplier_address" class="form-control" rows="5" style="resize: none;"><c:out value="${supplierinfo.supplier_address}"></c:out></textarea>
									<div class="invalid-feedback"> Address is required. </div>
								</div>
								<hr class="my-4">
								<button class="w-100 btn btn-primary btn-lg" type="submit">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Log out</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body text-center">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" onclick="window.location.href='Logout_Servlet'">Confirm</button>
				</div>
			</div>
		</div>
	</div>
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/form-validation.js"></script>
</body>

</html>