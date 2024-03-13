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
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="assets/js/datatables.js"></script>
    <link rel="stylesheet" href="assets/css/datatables.css">
    <script>
        $(document).ready(function() {
            $('#example').DataTable({
                "info": false
            });
        });
    </script>
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
				<a href="Redirect_Servlet?action=supplier" class="nav-link link-dark">
					<img src="assets/icons/file.svg" class="pb-1 px-2">
					<label class="link-name">Supplier</label>
				</a>
			</li>
			<li>
				<a href="Redirect_Servlet?action=staff" class="nav-link active">
					<img src="assets/icons/group_white.svg" class="pb-1 px-2">
					<label class="link-name">Staff</label>
				</a>
			</li>
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
		<button class="btn btn-sm btn-secondary bg-accent-light" style="border: none;"><img src="assets/icons/short_left.svg" class="py-1"></button>
		<div class="py-3 fs-3 fw-bold">Staff</div>
		<div class="card mb-2 w-100">
			<div class="card-header fw-bold">Add Staff</div>
			<div class="card-body">
				<div class="container">
					<div class="row g-5">
						<form action="Add_Staff_Servlet" method="post" class="needs-validation" novalidate>
							<c:if test="${session_status != null}">
								<c:choose>
									<c:when test="${session_status == 'Successfully added' || session_status == 'Successfully deleted'}">
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
									<input name="staff_idnum" type="text" class="form-control" required>
									<div class="invalid-feedback"> ID number is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Password</label>
									<input name="staff_password" type="text" class="form-control" required>
									<div class="invalid-feedback"> Password is required. </div>
								</div>
								<div class="col-12">
									<label class="form-label">Name</label>
									<input style="text-transform: capitalize;" name="staff_name" type="text" class="form-control" required>
									<div class="invalid-feedback"> Name is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Phone number</label>
									<input name="staff_phonenum" type="text" class="form-control">
									<div class="invalid-feedback"> Phone number is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Email</label>
									<input name="staff_email" type="text" class="form-control">
									<div class="invalid-feedback"> Email is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Deparment</label>
									<select name="department_idnum" class="form-select">
										<option value=""></option>
										<c:forEach items="${departmentlist}" var="deplist">
											<option value="<c:out value="${deplist.department_idnum}"></c:out>"><c:out value="${deplist.department_name}"></c:out></option>
										</c:forEach>
									</select>
									<div class="invalid-feedback"> Please select a valid deparment. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Level</label>
									<select name="staff_level" class="form-select" required>
										<option value=""></option>
										<option value="Administrator">Administrator</option>
										<option value="Normal Staff">Normal Staff</option>
									</select>
									<div class="invalid-feedback"> Please select a valid level. </div>
								</div>
								<hr class="my-4">
								<button class="w-100 btn btn-primary btn-lg" type="submit">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="card w-100">
			<div class="card-header fw-bold">Staff List</div>
			<div class="card-body">
				<table id="example" class="display" style="width:100%">
					<thead>
						<tr>
							<th>ID number</th>
							<th>Name</th>
							<th>Phone number</th>
							<th>Email</th>
							<th>Department</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${stafflist}" var="staflist">
							<tr>
								<td>
									<label class="fw-bold"><c:out value="${staflist.staff_idnum}"></c:out></label><br>
									<label class="text-black-50"><c:out value="${staflist.staff_level}"></c:out></label>
								</td>
								<td><c:out value="${staflist.staff_name}"></c:out></td>
								<td><c:out value="${staflist.staff_phonenum}"></c:out></td>
								<td><c:out value="${staflist.staff_email}"></c:out></td>
								<td>
									<c:out value="${staflist.department_idnum}"></c:out><br>
									<label class="text-black-50"><c:out value="${staflist.department.department_name}"></c:out></label>
								<td>
									<button onclick="window.location.href='Redirect_Servlet?action=staff_delete&id=<c:out value="${staflist.staff_idnum}"></c:out>'" class="btn btn-sm m-1 btn-danger" style="border: none;"><img src="assets/icons/delete_white.svg" class="py-1"></button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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