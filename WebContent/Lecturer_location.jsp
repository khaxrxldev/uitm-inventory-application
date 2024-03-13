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
    <style type="text/css">
    	#complaint_button {
    		display: none;
    	}
    	table.dataTable.hover tbody tr:hover, table.dataTable.display tbody tr:hover #complaint_button {
		  	display: block;
		}
    </style>
</head>

<body style="background-color: #f2f2f2;">
	<div class="container col-lg-10" style="max-width: 100%;">
		<div class="card my-4">
			<div class="card-header fw-bold">Location List</div>
			<div class="card-body" style="overflow-x: auto;">
				<table id="example" class="display" style="width: 100%">
					<thead>
						<tr>
							<th>Location</th>
							<th>Block</th>
							<th>Display</th>
							<th>Adapter</th>
							<th>Remote</th>
							<th>Chair</th>
							<th>Table</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${locationlist}" var="loclist">
							<tr>
								<td>
									<c:out value="${loclist.location_idnum}"></c:out>
									<c:if test="${loclist.location_byod == true}">
										<span class="mx-1 badge rounded-pill bg-danger">BYOD</span>
									</c:if>
								</td>
								<td>
									<c:out value="${loclist.location_block}"></c:out><br>
									<label class="text-black-50"><c:out value="${loclist.location_level}"></c:out></label>
								</td>
								<td>
									<c:forEach items="${equipmentdisplaylist}" var="eqdisplist">
										<c:if test="${loclist.location_idnum == eqdisplist.location_idnum}">
											<c:out value="${eqdisplist.equipment_type.eqtype_name}"></c:out><br>
											<label class="text-black-50"><c:out value="[ ${eqdisplist.equipment_idnum} ]"></c:out></label><br>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${equipmentdisplaylist}" var="eqdisplist">
										<c:if test="${loclist.location_idnum == eqdisplist.location_idnum}">
											<c:out value="${eqdisplist.equipment_converter}"></c:out><br>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${equipmentdisplaylist}" var="eqdisplist">
										<c:if test="${loclist.location_idnum == eqdisplist.location_idnum}">
											<c:out value="${eqdisplist.equipment_remote}"></c:out><br>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${equipmentcapacitylist}" var="eqcaplist">
										<c:if test="${loclist.location_idnum == eqcaplist.location_idnum && eqcaplist.equipment_type.eqtype_name == 'Chair'}">
											<c:out value="${eqcaplist.equipment_quantity}"></c:out><br>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${equipmentcapacitylist}" var="eqcaplist">
										<c:if test="${loclist.location_idnum == eqcaplist.location_idnum && eqcaplist.equipment_type.eqtype_name == 'Table'}">
											<c:out value="${eqcaplist.equipment_quantity}"></c:out><br>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<button onclick="window.location.href='Redirect_Servlet?action=lecturer_complaint&locid=<c:out value="${loclist.location_idnum}"></c:out>'" id="complaint_button" type="button" class="btn btn-warning">Report</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

</html>