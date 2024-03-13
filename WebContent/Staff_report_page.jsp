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
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		google.charts.load('current', {'packages':['bar']});
		google.charts.setOnLoadCallback(drawAVTypeChart);
		google.charts.setOnLoadCallback(drawHEATypeChart);
		google.charts.setOnLoadCallback(drawAVStatusChart);
		google.charts.setOnLoadCallback(drawHEAStatusChart);
		google.charts.setOnLoadCallback(drawAVSupplierChart);
		google.charts.setOnLoadCallback(drawHEASupplierChart);
		google.charts.setOnLoadCallback(drawBrandChart);
		
		function drawAVTypeChart() {
			var type_array = new Array();
			
			type_array[0] = ['Type', 'Total'];
			<c:forEach begin="0" end="${avequipmenttypegraph.size()}" step="1" varStatus="loop" items="${avequipmenttypegraph}" var="eqtypegraph">
				type_array["${loop.count}"] = ["${eqtypegraph.eqtype_name}", +"${eqtypegraph.eqtype_total}"];
			</c:forEach>
			
			var data = new google.visualization.arrayToDataTable(type_array);
			var options = {
				legend: { position: 'none'},
				bar: { groupWidth: '40%' },
				colors: ['#6610f2']
			};
			
			var chart = new google.charts.Bar(document.getElementById('av_type_chart'));
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		
		function drawHEATypeChart() {
			var type_array = new Array();
			
			type_array[0] = ['Type', 'Total'];
			<c:forEach begin="0" end="${heaequipmenttypegraph.size()}" step="1" varStatus="loop" items="${heaequipmenttypegraph}" var="eqtypegraph">
				type_array["${loop.count}"] = ["${eqtypegraph.eqtype_name}", +"${eqtypegraph.eqtype_total}"];
			</c:forEach>
			
			var data = new google.visualization.arrayToDataTable(type_array);
			var options = {
				legend: { position: 'none'},
				bar: { groupWidth: '40%' },
				colors: ['#6610f2']
			};
			
			var chart = new google.charts.Bar(document.getElementById('hea_type_chart'));
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		
		function drawAVStatusChart() {
			var status_array = new Array();
			
			status_array[0] = ['Status', 'Total'];
			<c:forEach begin="0" end="${avequipmentstatusgraph.size()}" step="1" varStatus="loop" items="${avequipmentstatusgraph}" var="eqstatgraph">
				status_array["${loop.count}"] = ["${eqstatgraph.equipment_status}", +"${eqstatgraph.equipment_status_total}"];
			</c:forEach>
			
			var data = new google.visualization.arrayToDataTable(status_array);
			var options = {
				legend: { position: 'none'},
				bar: { groupWidth: '40%' },
				colors: ['#6f42c1']
			};
			
			var chart = new google.charts.Bar(document.getElementById('av_status_chart'));
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		
		function drawHEAStatusChart() {
			var status_array = new Array();
			
			status_array[0] = ['Status', 'Total'];
			<c:forEach begin="0" end="${heaequipmentstatusgraph.size()}" step="1" varStatus="loop" items="${heaequipmentstatusgraph}" var="eqstatgraph">
				status_array["${loop.count}"] = ["${eqstatgraph.equipment_status}", +"${eqstatgraph.equipment_status_total}"];
			</c:forEach>
			
			var data = new google.visualization.arrayToDataTable(status_array);
			var options = {
				legend: { position: 'none'},
				bar: { groupWidth: '40%' },
				colors: ['#6f42c1']
			};
			
			var chart = new google.charts.Bar(document.getElementById('hea_status_chart'));
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		
		function drawAVSupplierChart() {
			var supplier_array = new Array();
			
			supplier_array[0] = ['Supplier', 'Total'];
			<c:forEach begin="0" end="${avsuppliergraph.size()}" step="1" varStatus="loop" items="${avsuppliergraph}" var="supgraph">
				supplier_array["${loop.count}"] = ["${supgraph.supplier_name}", +"${supgraph.supplier_total}"];
			</c:forEach>
			
			var data = new google.visualization.arrayToDataTable(supplier_array);
			var options = {
				legend: { position: 'none'},
				bar: { groupWidth: '40%' },
				colors: ['#dc3545']
			};
			
			var chart = new google.charts.Bar(document.getElementById('av_supplier_chart'));
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		
		function drawHEASupplierChart() {
			var supplier_array = new Array();
			
			supplier_array[0] = ['Supplier', 'Total'];
			<c:forEach begin="0" end="${heasuppliergraph.size()}" step="1" varStatus="loop" items="${heasuppliergraph}" var="supgraph">
				supplier_array["${loop.count}"] = ["${supgraph.supplier_name}", +"${supgraph.supplier_total}"];
			</c:forEach>
			
			var data = new google.visualization.arrayToDataTable(supplier_array);
			var options = {
				legend: { position: 'none'},
				bar: { groupWidth: '40%' },
				colors: ['#dc3545']
			};
			
			var chart = new google.charts.Bar(document.getElementById('hea_supplier_chart'));
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		
		function drawBrandChart() {
			var brand_array = new Array();
			
			brand_array[0] = ['Brand', 'Total'];
			<c:forEach begin="0" end="${equipmentbrandgraph.size()}" step="1" varStatus="loop" items="${equipmentbrandgraph}" var="eqbrgraph">
				brand_array["${loop.count}"] = ["${eqbrgraph.equipment_brand}", +"${eqbrgraph.equipment_brand_total}"];
			</c:forEach>
			
			var data = new google.visualization.arrayToDataTable(brand_array);
			var options = {
				legend: { position: 'none'},
				bar: { groupWidth: '40%' },
				colors: ['#d63384']
			};
			
			var chart = new google.charts.Bar(document.getElementById('brand_chart'));
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
	</script>
    <style type="text/css">
        table {
            width: 100%;
            page-break-before: auto;
        	page-break-after: always;
        }
        th, td {
            border: 1px solid;
            border-collapse: collapse;
            padding: 5px 10px;
        }
        tr {
        	page-break-inside: avoid;
        }
        .graph-card {
        	page-break-inside: avoid;
        	page-break-after: always;
        }
        @media print {
            body { 
                -webkit-print-color-adjust: exact;
            }
            .print-btn {
                display: none;
            }
        }
    </style>
</head>

<body class="bg-white">
    <div class="report-container my-4">
        <div class="row g-5">
            <div class="col-12">
                <div class="bg-accent w-100 p-3 fw-bold position-relative">EQUIPMENT INVENTORY REPORT
                    <button class="print-btn btn btn-sm btn-secondary bg-accent-light position-absolute top-50 translate-middle end-0" style="border: none; float: right" onclick="window.print();"><img src="assets/icons/printer.svg" class="py-1"></button>
                </div>
                <img class="m-3" width="150em" src="assets/images/UiTM_logo_full.png">
                <div class="report-info">
                    Universiti Teknologi MARA <br>
                    Cawangan Johor <br>
                    Kampus Segamat <br><br>
                    Bahagian Hal Ehwal <br>
                    Akademik
                </div>
                <div class="m-3">
                    <div class="row g-5">
                        <div class="col-2 fs-6 fw-bold">Date</div>
                        <div class="col-4 fs-6">: <c:out value="${reportdate}"></c:out></div>
                    </div>
                    <div class="row g-5">
                        <div class="col-2 fs-6 fw-bold">Time</div>
                        <div class="col-4 fs-6">: <c:out value="${reporttime}"></c:out></div>
                    </div>
                    <div class="row g-5">
                        <div class="col-2 fs-6 fw-bold">Filter by</div>
                        <div class="col-4 fs-6">: <c:out value="${reportfilter}"></c:out></div>
                    </div>
                    <div class="row g-5">
                        <div class="col-2 fs-6 fw-bold">Semester</div>
                        <div class="col-4 fs-6">: <c:out value="${reportsemester}"></c:out></div>
                    </div>
                    <div class="row g-5">
                        <div class="col-2 fs-6 fw-bold">Staff</div>
                        <div class="col-4 fs-6">: [<c:out value="${session_idnum}"></c:out>] <c:out value="${session_name}"></c:out></div>
                    </div>
                </div>
                <c:if test="${table_status != 'No Table'}">
					<table class="my-5">
						<tr class="bg-accent">
	                        <th>ID number</th>
							<th>Status</th>
							<th>Report <br> Date</th>
							<th>Maintenance <br> Date</th>
							<th>Department</th>
							<th>Location</th>
							<th>Staff</th>
							<th>Supplier</th>
						</tr>
						<c:forEach items="${equipmentreportlist}" var="eqreplist">
							<tr class="bg-white">
								<td>
									<label class="fw-bold"><c:out value="${eqreplist.equipment_idnum}"></c:out></label><br>
									<label class="text-black-50"><c:out value="${eqreplist.equipment_type.eqtype_name}"></c:out></label>
								</td>
								<td>
									<c:choose>
										<c:when test="${eqreplist.equipment_status == 'Good'}">
											<div class="fw-bold text-success">
												<c:out value="${eqreplist.equipment_status}"></c:out>
											</div>
										</c:when>
										<c:when test="${eqreplist.equipment_status == 'Damage'}">
											<div class="fw-bold text-danger">
												<c:out value="${eqreplist.equipment_status}"></c:out>
											</div>
										</c:when>
										<c:when test="${eqreplist.equipment_status == 'Repaired'}">
											<div class="fw-bold text-warning">
												<c:out value="${eqreplist.equipment_status}"></c:out>
											</div>
										</c:when>
										<c:when test="${eqreplist.equipment_status == 'Disposed'}">
											<div class="fw-bold text-dark">
												<c:out value="${eqreplist.equipment_status}"></c:out>
											</div>
										</c:when>
									</c:choose>
								</td>
								<td>
								<fmt:formatDate pattern = "dd-MM-yyyy" value = "${eqreplist.equipment_report_date}"/><br>
									<label class="text-black-50"><c:out value="${eqreplist.report_date_different}"></c:out></label>
								</td>
								<td>
								<fmt:formatDate pattern = "dd-MM-yyyy" value = "${eqreplist.equipment_maint_date}"/><br>
									<label class="text-black-50"><c:out value="${eqreplist.maint_date_different}"></c:out></label>
								</td>
								<td>
									<label class="fw-bold"><c:out value="${eqreplist.department_idnum}"></c:out></label><br>
									<label class="text-black-50 text-wrap"><c:out value="${eqreplist.department.department_name}"></c:out></label>
								</td>
								<td><c:out value="${eqreplist.location_idnum}"></c:out></td>
								<td><c:out value="${eqreplist.staff_idnum}"></c:out></td>
								<td><c:out value="${eqreplist.supplier_idnum}"></c:out></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${av_graph_type == true}">
	                <div class="graph-card card my-3">
	                    <div class="card-header fw-bold">
	                        Audio-Visual Type Graph
	                    </div>
	                    <div class="card-body">
							<div id="av_type_chart" style="width: 100%; height: 350px; margin: 0 auto; display: inline-block;"></div>
	                    </div>
	                </div>
				</c:if>
				<c:if test="${hea_graph_type == true}">
	                <div class="graph-card card my-3">
	                    <div class="card-header fw-bold">
	                        Academic Type Graph
	                    </div>
	                    <div class="card-body">
							<div id="hea_type_chart" style="width: 100%; height: 350px; margin: 0 auto; display: inline-block;"></div>
	                    </div>
	                </div>
				</c:if>
				<c:if test="${av_graph_status == true}">
	                <div class="graph-card card my-3">
	                    <div class="card-header fw-bold">
	                        Audio-Visual Status Graph
	                    </div>
	                    <div class="card-body">
							<div id="av_status_chart" style="width: 100%; height: 350px; margin: 0 auto; display: inline-block;"></div>
	                    </div>
	                </div>
				</c:if>
				<c:if test="${hea_graph_status == true}">
	                <div class="graph-card card my-3">
	                    <div class="card-header fw-bold">
	                        Academic Status Graph
	                    </div>
	                    <div class="card-body">
							<div id="hea_status_chart" style="width: 100%; height: 350px; margin: 0 auto; display: inline-block;"></div>
	                    </div>
	                </div>
				</c:if>
				<c:if test="${av_graph_supplier == true}">
	                <div class="graph-card card my-3">
	                    <div class="card-header fw-bold">
	                        Audio-Visual Supplier Graph
	                    </div>
	                    <div class="card-body">
							<div id="av_supplier_chart" style="width: 100%; height: 350px; margin: 0 auto; display: inline-block;"></div>
	                    </div>
	                </div>
				</c:if>
				<c:if test="${hea_graph_supplier == true}">
	                <div class="graph-card card my-3">
	                    <div class="card-header fw-bold">
	                        Academic Supplier Graph
	                    </div>
	                    <div class="card-body">
							<div id="hea_supplier_chart" style="width: 100%; height: 350px; margin: 0 auto; display: inline-block;"></div>
	                    </div>
	                </div>
				</c:if>
				<c:if test="${graph_brand == true}">
	                <div class="graph-card card my-3">
	                    <div class="card-header fw-bold">
	                        Brand Graph
	                    </div>
	                    <div class="card-body">
							<div id="brand_chart" style="width: 100%; height: 350px; margin: 0 auto; display: inline-block;"></div>
	                    </div>
	                </div>
				</c:if>
            </div>
        </div>
    </div>
</body>

</html>