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
    <script type="text/javascript">
		function create_table(sec_filter_value) {
			var table_div = document.getElementById("table_div");
			var table = document.createElement("table");
			table.id = "table_id";
			table.className = "display";
			
			var thead = document.createElement("thead");
			var tbody = document.createElement("tbody");
			var tr_th = document.createElement("tr");
			
			var th00 = document.createElement("th");
			th00.innerHTML = "ID Number";
			tr_th.appendChild(th00);
			
			var th01 = document.createElement("th");
			th01.innerHTML = "Status";
			tr_th.appendChild(th01);
			
			var th03 = document.createElement("th");
			th03.innerHTML = "Report <br> Date";
			tr_th.appendChild(th03);
			
			var th04 = document.createElement("th");
			th04.innerHTML = "Maintenance <br> Date";
			tr_th.appendChild(th04);
			
			var th07 = document.createElement("th");
			th07.innerHTML = "Department";
			tr_th.appendChild(th07);
			
			var th08 = document.createElement("th");
			th08.innerHTML = "Location";
			tr_th.appendChild(th08);
			
			var th09 = document.createElement("th");
			th09.innerHTML = "Staff";
			tr_th.appendChild(th09);
			
			var th10 = document.createElement("th");
			th10.innerHTML = "Supplier";
			tr_th.appendChild(th10);
			
			thead.appendChild(tr_th);
			table.appendChild(thead);
			
			<c:forEach items="${equipmentmaintenancelist}" var="eqmaintlist">
				var tr_td = document.createElement("tr");
				
				var td00 = document.createElement("td");
				td00.innerHTML = "<label class='fw-bold'>${eqmaintlist.equipment_idnum}</label>" + "<br>" + "<label class='text-black-50'>${eqmaintlist.equipment_type.eqtype_name}</label>";
				tr_td.appendChild(td00);
				
				var td01 = document.createElement("td");
				if("${eqmaintlist.equipment_status}" == 'Good') {
					td01.innerHTML = "<div class='alert alert-success text-center p-2' role='alert'>${eqmaintlist.equipment_status}</div>";
				}
				if("${eqmaintlist.equipment_status}" == 'Damage') {
					td01.innerHTML = "<div class='alert alert-danger text-center p-2' role='alert'>${eqmaintlist.equipment_status}</div>";
				}
				if("${eqmaintlist.equipment_status}" == 'Repaired') {
					td01.innerHTML = "<div class='alert alert-warning text-center p-2' role='alert'>${eqmaintlist.equipment_status}</div>";
				}
				if("${eqmaintlist.equipment_status}" == 'Disposed') {
					td01.innerHTML = "<div class='alert alert-dark text-center p-2' role='alert'>${eqmaintlist.equipment_status}</div>";
				}
				tr_td.appendChild(td01);
				
				var td04 = document.createElement("td");
				td04.innerHTML = "<fmt:formatDate pattern = 'dd-MM-yyyy' value = '${eqmaintlist.equipment_report_date}'/>" + "<br>" + "<label class='text-black-50'>${eqmaintlist.report_date_different}</label>";
				tr_td.appendChild(td04);
				
				var td05 = document.createElement("td");
				td05.innerHTML = "<fmt:formatDate pattern = 'dd-MM-yyyy' value = '${eqmaintlist.equipment_maint_date}'/>" + "<br>" + "<label class='text-black-50'>${eqmaintlist.maint_date_different}</label>";
				tr_td.appendChild(td05);
				
				var td07 = document.createElement("td");
				td07.innerHTML = "${eqmaintlist.department.department_name}" + "<br>" + "<label class='text-black-50'>${eqmaintlist.department_idnum}</label>";
				tr_td.appendChild(td07);
				
				var td08 = document.createElement("td");
				td08.innerHTML = "${eqmaintlist.location_idnum}";
				tr_td.appendChild(td08);
				
				var td09 = document.createElement("td");
				td09.innerHTML = "${eqmaintlist.staff_idnum}";
				tr_td.appendChild(td09);
				
				var td10 = document.createElement("td");
				td10.innerHTML = "${eqmaintlist.supplier_idnum}";
				tr_td.appendChild(td10);
				
				if(sec_filter_value == "${eqmaintlist.department_idnum}" || sec_filter_value == "") {
					tbody.appendChild(tr_td);
				}
			</c:forEach>
			
			table.appendChild(tbody);
			table.style.width = '100%';
			table_div.appendChild(table);
			
			datatable = $('#table_id').DataTable({
				"info": false
			});
		    datatable.draw();
		}
		
		function delete_table() {
			var div = document.getElementById('table_div');
			while(div.firstChild){
			    div.removeChild(div.firstChild);
			}
		}
		
		window.onload = function() {
			create_table("");
		}
		
		function filter_table(filter_value) {
			delete_table();
			create_table(filter_value);
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
				<a href="Redirect_Servlet?action=maintenance" class="nav-link active">
					<img src="assets/icons/settings_white.svg" class="pb-1 px-2">
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
		<div class="py-3 fs-3 fw-bold">Maintenance</div>
		<div class="card mb-2 w-100">
			<div class="card-header fw-bold">Equipment List</div>
			<div class="card-body">
				<div class="container">
					<div class="row g-5">
						<form action="Maintenance_Servlet" method="post" class="needs-validation" novalidate>
							<div class="row g-3">
								<div class="col-6">
									<label class="form-label">Department</label>
									<select name="department_idnum" onchange="filter_table(this.value)" class="form-select">
										<option value=""></option>
										<c:forEach items="${departmentlist}" var="deplist">
											<option value="<c:out value="${deplist.department_idnum}"></c:out>"><c:out value="${deplist.department_name}"></c:out></option>
										</c:forEach>
									</select>
								</div>
								<div class="col-6">
									<label class="form-label">Semester</label>
									<input name="semester" type="text" class="form-control">
								</div>
								<div class="col-12">
									<ul class="list-group">
										<li class="list-group-item">
											<input name="graph_maintenance_type" value="true" class="form-check-input mx-1" type="checkbox">
											<label class="form-check-label"> Equipment Type Graph</label>
										</li>
										<li class="list-group-item">
											<input name="graph_maintenance_status" value="true" class="form-check-input mx-1" type="checkbox">
											<label class="form-check-label"> Equipment Status Graph</label>
										</li>
									</ul>
								</div>
								<hr class="my-4">
								<button class="w-100 btn btn-primary btn-lg" type="submit">Submit</button>
							</div>
						</form>
					</div>
				</div>
				<div class="mt-5" id="table_div"></div>
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