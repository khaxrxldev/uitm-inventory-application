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
		google.charts.setOnLoadCallback(drawStatusChart);
		
		function drawStatusChart() {
			var status_array = new Array();
			
			status_array[0] = ['Status', 'Total'];
			<c:forEach begin="0" end="${complaintstatusgraph.size()}" step="1" varStatus="loop" items="${complaintstatusgraph}" var="compstatusgraph">
				status_array["${loop.count}"] = ["${compstatusgraph.complaint_status}", +"${compstatusgraph.complaint_status_total}"];
			</c:forEach>
			
			var data = new google.visualization.arrayToDataTable(status_array);
			var options = {
				legend: { position: 'none'},
				bar: { groupWidth: '40%' },
				colors: ['#6f42c1']
			};
			
			var chart = new google.charts.Bar(document.getElementById('status_chart'));
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
                <div class="bg-accent w-100 p-3 fw-bold position-relative">COMPLAINT STATUS REPORT
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
                <table class="my-5">
                    <tr class="bg-accent">
                        <th>Lecturer Information</th>
						<th>Status</th>
						<th>Complaint <br> Date</th>
						<th>Detail</th>
                    </tr>
                    <c:forEach items="${complaintlist}" var="complist">
						<tr class="bg-white">
							<td>
								<label class="fw-bold"><c:out value="${complist.complaint_name}"></c:out></label><br>
								<label class="text-black-50"><c:out value="${complist.complaint_phonenum}"></c:out></label>
							</td>
							<td>
								<c:choose>
									<c:when test="${complist.complaint_status == 'Complete'}">
										<div class="fw-bold text-success">
											<c:out value="${complist.complaint_status}"></c:out>
										</div>
									</c:when>
									<c:when test="${complist.complaint_status == 'Not Complete'}">
										<div class="fw-bold text-danger">
											<c:out value="${complist.complaint_status}"></c:out>
										</div>
									</c:when>
									<c:when test="${complist.complaint_status == 'In Progress'}">
										<div class="fw-bold text-warning">
											<c:out value="${complist.complaint_status}"></c:out>
										</div>
									</c:when>
								</c:choose>
							</td>
							<td>
								<fmt:formatDate pattern = "dd-MM-yyyy" value = "${complist.complaint_date}"/><br>
								<label class="text-black-50"><c:out value="${complist.complaint_date_different}"></c:out></label>
							</td>
							<td><c:out value="${complist.complaint_detail}"></c:out></td>
						</tr>
					</c:forEach>
                </table>
				<c:if test="${graph_complaint_status == true}">
	                <div class="card my-3">
	                    <div class="card-header fw-bold">
	                        Status Graph
	                    </div>
	                    <div class="card-body p-5">
							<div id="status_chart" style="width: 100%; height: 320px; margin: 0 auto; display: inline-block;"></div>
	                    </div>
	                </div>
				</c:if>
            </div>
        </div>
    </div>
</body>

</html>