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
        
        window.onload = function() {
    		document.getElementById("date_id").valueAsDate = new Date();
    	}
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
				<a href="Redirect_Servlet?action=equipmentav" class="nav-link active">
					<img src="assets/icons/monitor_white.svg" class="pb-1 px-2">
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
		<button class="btn btn-sm btn-secondary bg-accent-light" style="border: none;"><img src="assets/icons/short_left.svg" class="py-1"></button>
		<div class="py-3 fs-3 fw-bold">Audio Visual</div>
		<div class="card mb-2 w-100">
			<div class="card-header fw-bold">Add Equipment</div>
			<div class="card-body">
				<div class="container">
					<div class="row g-5">
						<form action="Add_Equipment_AV_Servlet" method="post" class="needs-validation" novalidate enctype="multipart/form-data">
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
								<div class="col-4">
									<label class="form-label">ID Number</label>
									<input name="equipment_idnum" type="text" class="form-control" required>
									<div class="invalid-feedback"> ID number is required. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Type</label>
									<select name="eqtype_idnum" class="form-select" required>
										<option value=""></option>
										<c:forEach items="${equipmenttypelist}" var="eqtypelist">
											<c:if test="${eqtypelist.department_idnum == 'UiTM_AV'}">
												<option value="<c:out value="${eqtypelist.eqtype_idnum}"></c:out>"><c:out value="${eqtypelist.eqtype_name}"></c:out></option>
											</c:if>
										</c:forEach>
									</select>
									<div class="invalid-feedback"> Please select a valid type. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Status</label>
									<select name="equipment_status" class="form-select" required>
										<option value=""></option>
										<option value="Good">Good</option>
										<option value="Damage">Damage</option>
										<option value="Repaired">Repaired</option>
										<option value="Disposed">Disposed</option>
									</select>
									<div class="invalid-feedback"> Please select a valid status. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Department</label>
									<input name="department_idnum" type="text" class="form-control" value="UiTM_AV" readonly>
									<div class="invalid-feedback"> Please select a valid department. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Location</label>
									<select name="location_idnum" class="form-select" required>
										<option value=""></option>
										<c:forEach items="${locationlist}" var="loclist">
											<option value="<c:out value="${loclist.location_idnum}"></c:out>"><c:out value="${loclist.location_idnum}"></c:out></option>
										</c:forEach>
									</select>
									<div class="invalid-feedback"> Please select a valid location. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Staff ID Number</label>
									<input name="staff_idnum" type="text" class="form-control" value="<c:out value="${session_idnum}"></c:out>" readonly>
									<div class="invalid-feedback"> Staff ID number is required. </div>
								</div>
								<div class="col-6">
									<label class="form-label">Supplier</label>
									<select name="supplier_idnum" class="form-select" required>
										<option value=""></option>
										<c:forEach items="${supplierlist}" var="suplist">
											<option value="<c:out value="${suplist.supplier_idnum}"></c:out>"><c:out value="${suplist.supplier_name}"></c:out></option>
										</c:forEach>
									</select>
									<div class="invalid-feedback"> Please select a valid supplier. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Insert Date</label>
									<input name="equipment_insert_date" id="date_id" type="date" class="form-control">
									<div class="invalid-feedback"> Insert date is required. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Report Date</label>
									<input name="equipment_report_date" type="text" onfocus="(this.type='date')" class="form-control">
									<div class="invalid-feedback"> Report date is required. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Maintenance Date</label>
									<input name="equipment_maint_date" type="text" onfocus="(this.type='date')" class="form-control">
									<div class="invalid-feedback"> Maintenance date is required. </div>
								</div>
								<div class="col-12">
									<label class="form-label">Description</label>
									<textarea name="equipment_description" class="form-control" rows="5" style="resize: none;"></textarea>
									<div class="invalid-feedback"> Description is required. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Brand</label>
									<input name="equipment_brand" type="text" class="form-control" required>
									<div class="invalid-feedback"> Brand is required. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Model</label>
									<input name="equipment_model" type="text" class="form-control" required>
									<div class="invalid-feedback"> Model is required. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Price</label>
									<input name="equipment_price" type="number" class="form-control">
									<div class="invalid-feedback"> Price is required. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Financial Number</label>
									<input name="equipment_financialnum" type="text" class="form-control">
									<div class="invalid-feedback"> Financial number is required. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Treasure Code</label>
									<input name="equipment_treasurecode" type="text" class="form-control">
									<div class="invalid-feedback"> Treasure Code is required. </div>
								</div>
								<div class="col-4">
									<label class="form-label">Officials ID Number</label>
									<input name="equipment_officialnum" type="text" class="form-control">
									<div class="invalid-feedback"> Officials ID Number is required. </div>
								</div>
								<div class="col-3">
									<label class="form-label">Remote</label>
									<input name="equipment_remote" type="text" class="form-control">
									<div class="invalid-feedback"> Remote is required. </div>
								</div>
								<div class="col-3">
									<label class="form-label">Converter</label>
									<input name="equipment_converter" type="text" class="form-control">
									<div class="invalid-feedback"> Converter is required. </div>
								</div>
								<div class="col-6">
									<label for="formFile" class="form-label">Image</label>
									<input class="form-control" type="file" name="equipment_image">
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
							<th>Report <br> Date</th>
							<th>Maintenance <br> Date</th>
							<th>Staff</th>
							<th>Location</th>
							<th>Supplier</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${equipmentlist}" var="eqlist">
							<tr>
								<td>
									<label class="fw-bold"><c:out value="${eqlist.equipment_idnum}"></c:out></label><br>
									<label class="text-black-50"><c:out value="${eqlist.equipment_type.eqtype_name}"></c:out></label>
								</td>
								<td>
									<c:choose>
										<c:when test="${eqlist.equipment_status == 'Good'}">
											<div class="alert alert-success text-center p-2" role="alert">
												<c:out value="${eqlist.equipment_status}"></c:out>
											</div>
										</c:when>
										<c:when test="${eqlist.equipment_status == 'Damage'}">
											<div class="alert alert-danger text-center p-2" role="alert">
												<c:out value="${eqlist.equipment_status}"></c:out>
											</div>
										</c:when>
										<c:when test="${eqlist.equipment_status == 'Repaired'}">
											<div class="alert alert-warning text-center p-2" role="alert">
												<c:out value="${eqlist.equipment_status}"></c:out>
											</div>
										</c:when>
										<c:when test="${eqlist.equipment_status == 'Disposed'}">
											<div class="alert alert-dark text-center p-2" role="alert">
												<c:out value="${eqlist.equipment_status}"></c:out>
											</div>
										</c:when>
									</c:choose>
								</td>
								<td>
									<fmt:formatDate pattern = "dd-MM-yyyy" value = "${eqlist.equipment_report_date}"/><br>
									<label class="text-black-50"><c:out value="${eqlist.report_date_different}"></c:out></label>
								</td>
								<td>
									<fmt:formatDate pattern = "dd-MM-yyyy" value = "${eqlist.equipment_maint_date}"/><br>
									<label class="text-black-50"><c:out value="${eqlist.maint_date_different}"></c:out></label>
								</td>
								<td><c:out value="${eqlist.staff_idnum}"></c:out></td>
								<td>
									<c:out value="${eqlist.location_idnum}"></c:out><br>
									<label class="text-black-50"><c:out value="${eqlist.location.location_block}"></c:out></label>
								</td>
								<td><c:out value="${eqlist.supplier_idnum}"></c:out></td>
								<td>
									<button onclick="window.location.href='Redirect_Servlet?action=equipmentav_update&id=<c:out value="${eqlist.equipment_idnum}"></c:out>'" class="btn btn-sm m-1 btn-secondary" style="border: none;"><img src="assets/icons/edit_white.svg" class="py-1"></button>
									<button onclick="window.location.href='Redirect_Servlet?action=equipmentav_delete&id=<c:out value="${eqlist.equipment_idnum}"></c:out>&dept=<c:out value="${eqlist.department_idnum}"></c:out>'" class="btn btn-sm m-1 btn-danger" style="border: none;"><img src="assets/icons/delete_white.svg" class="py-1"></button>
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