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
				<a href="Redirect_Servlet?action=location" class="nav-link active">
					<img src="assets/icons/location_white.svg" class="pb-1 px-2">
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
		<button onclick="window.location.href='Redirect_Servlet?action=location'" class="btn btn-sm btn-secondary bg-accent-light" style="border: none;"><img src="assets/icons/short_left.svg" class="py-1"></button>
		<div class="py-3 fs-3 fw-bold">Location</div>
		<div class="card mb-2 w-100">
			<div class="card-header fw-bold">Update Location</div>
			<div class="card-body">
				<div class="container">
					<div class="row g-5">
						<form action="Update_Location_Servlet" method="post" class="needs-validation" novalidate>
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
									<input name="location_idnum" value="<c:out value="${locationinfo.location_idnum}"></c:out>" type="text" class="form-control" readonly>
									<div class="invalid-feedback"> ID number is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Block Name</label>
									<input style="text-transform: capitalize;" name="location_block" value="<c:out value="${locationinfo.location_block}"></c:out>" type="text" class="form-control" required>
									<div class="invalid-feedback"> Block name is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Maintenance Date</label>
									<input name="location_maint_date" value="<c:out value="${locationinfo.location_maint_date}"></c:out>" type="text" onfocus="(this.type='date')" class="form-control">
									<div class="invalid-feedback"> Maintenance date is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Maintenance Status</label>
									<select name="location_maint_status" class="form-select">
										<option <c:if test="${locationinfo.location_maint_status == ''}"><c:out value="selected"></c:out></c:if> value=""></option>
										<option <c:if test="${locationinfo.location_maint_status == 'Complete'}"><c:out value="selected"></c:out></c:if> value="Complete">Complete</option>
										<option <c:if test="${locationinfo.location_maint_status == 'Not Complete'}"><c:out value="selected"></c:out></c:if> value="Not Complete">Not Complete</option>
										<option <c:if test="${locationinfo.location_maint_status == 'In Progress'}"><c:out value="selected"></c:out></c:if> value="In Progress">In Progress</option>
									</select>
									<div class="invalid-feedback"> Please select a valid status. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Level</label>
									<select name="location_level" class="form-select">
										<option <c:if test="${locationinfo.location_level == ''}"><c:out value="selected"></c:out></c:if> value=""></option>
										<option <c:if test="${locationinfo.location_level == 'Ground Floor'}"><c:out value="selected"></c:out></c:if> value="Ground Floor">Ground Floor</option>
										<option <c:if test="${locationinfo.location_level == 'Level 1'}"><c:out value="selected"></c:out></c:if> value="Level 1">Level 1</option>
										<option <c:if test="${locationinfo.location_level == 'Level 2'}"><c:out value="selected"></c:out></c:if> value="Level 2">Level 2</option>
										<option <c:if test="${locationinfo.location_level == 'Level 3'}"><c:out value="selected"></c:out></c:if> value="Level 3">Level 3</option>
										<option <c:if test="${locationinfo.location_level == 'Level 4'}"><c:out value="selected"></c:out></c:if> value="Level 4">Level 4</option>
									</select>
									<div class="invalid-feedback"> Please select a valid level. </div>
								</div>
								<div class="col-6">
									<label class="form-label">BYOD Status</label>
									<select name="location_byod" class="form-select">
										<option <c:if test="${locationinfo.location_byod == ''}"><c:out value="selected"></c:out></c:if> value=""></option>
										<option <c:if test="${locationinfo.location_byod == 'true'}"><c:out value="selected"></c:out></c:if> value="true">Yes</option>
										<option <c:if test="${locationinfo.location_byod == 'false'}"><c:out value="selected"></c:out></c:if> value="false">No</option>
									</select>
									<div class="invalid-feedback"> Please select a valid status. </div>
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
			<div class="card-header fw-bold">Equipment List</div>
			<div class="card-body">
				<table id="example" class="display" style="width:100%">
					<thead>
						<tr>
							<th>ID Number</th>
							<th>Status</th>
							<th>Report Date</th>
							<th>Maintenance Date</th>
							<th>Staff</th>
							<th>Location</th>
							<th>Department</th>
							<th>Supplier</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${locationequipmentlist}" var="loceqlist">
							<tr>
								<td>
									<label class="fw-bold"><c:out value="${loceqlist.equipment_idnum}"></c:out></label><br>
									<label class="text-black-50"><c:out value="${loceqlist.equipment_type.eqtype_name}"></c:out></label>
								</td>
								<td>
									<c:choose>
										<c:when test="${loceqlist.equipment_status == 'Good'}">
											<div class="alert alert-success text-center p-2" role="alert">
												<c:out value="${loceqlist.equipment_status}"></c:out>
											</div>
										</c:when>
										<c:when test="${loceqlist.equipment_status == 'Damage'}">
											<div class="alert alert-danger text-center p-2" role="alert">
												<c:out value="${loceqlist.equipment_status}"></c:out>
											</div>
										</c:when>
										<c:when test="${loceqlist.equipment_status == 'Repaired'}">
											<div class="alert alert-warning text-center p-2" role="alert">
												<c:out value="${loceqlist.equipment_status}"></c:out>
											</div>
										</c:when>
										<c:when test="${loceqlist.equipment_status == 'Disposed'}">
											<div class="alert alert-dark text-center p-2" role="alert">
												<c:out value="${loceqlist.equipment_status}"></c:out>
											</div>
										</c:when>
									</c:choose>
								</td>
								<td>
									<fmt:formatDate pattern = "dd-MM-yyyy" value = "${loceqlist.equipment_report_date}"/><br>
									<label class="text-black-50"><c:out value="${loceqlist.report_date_different}"></c:out></label>
								</td>
								<td>
									<fmt:formatDate pattern = "dd-MM-yyyy" value = "${loceqlist.equipment_maint_date}"/><br>
									<label class="text-black-50"><c:out value="${loceqlist.maint_date_different}"></c:out></label>
								</td>
								<td><c:out value="${loceqlist.staff_idnum}"></c:out></td>
								<td>
									<c:out value="${loceqlist.location_idnum}"></c:out><br>
									<label class="text-black-50"><c:out value="${loceqlist.location.location_block}"></c:out></label>
								</td>
								<td><c:out value="${loceqlist.department_idnum}"></c:out></td>
								<td><c:out value="${loceqlist.supplier_idnum}"></c:out></td>
								<td>
									<c:if test="${loceqlist.department_idnum == 'UiTM_AV'}">
										<button onclick="window.location.href='Redirect_Servlet?action=equipmentav_update&id=<c:out value="${loceqlist.equipment_idnum}"></c:out>'" class="btn btn-sm m-1 btn-secondary" style="border: none;"><img src="assets/icons/edit_white.svg" class="py-1"></button>
									</c:if>
									<c:if test="${loceqlist.department_idnum == 'UiTM_AC'}">
										<button onclick="window.location.href='Redirect_Servlet?action=equipmentac_update&id=<c:out value="${loceqlist.equipment_idnum}"></c:out>'" class="btn btn-sm m-1 btn-secondary" style="border: none;"><img src="assets/icons/edit_white.svg" class="py-1"></button>
									</c:if>
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