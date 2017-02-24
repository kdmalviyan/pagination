<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<spring:url value="https://code.jquery.com/jquery-1.12.1.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>
<spring:url value="/resources/js/jquery.dataTables.js" var="datatable" />
<script src="${datatable}"></script>
<spring:url value="/resources/css/jquery.dataTables.css"
	var="jquerydataTables" />
<link href="${jquerydataTables}" rel="stylesheet" />
<spring:url value="/resources/css/jquery.dataTables.min.css"
	var="jquerydataTablesMin" />
<link href="${jquerydataTablesMin}" rel="stylesheet" />
<spring:url value="/resources/css/common.css" var="common" />
<link href="${common}" rel="stylesheet" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="example" class="display" cellspacing="0" width="100%"
		style="overflow-x: auto">
		<thead>
			<tr>
				<th>username</th>
				<th>firstName</th>
				<th>lastName</th>
				<th>age</th>
				<th>line1</th>
				<th>line2</th>
				<th>city</th>
				<th>pincode</th>
				<th>state</th>
				<th>country</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<td><a href="users?pageNum=${pageNum - 1}" id="previous">Previous</a></td>
				<td>${pageNum + 1 }</td>
				<td><a href="users?pageNum=${pageNum + 1}" id="next">Next</a></td>
			</tr>
		</tfoot>
	</table>
</body>
</html>
<script type="text/javascript">
	$(document).ready(function() {
		var data = eval('${users}');
		var table = $('#example').DataTable({
			"aaData" : data,
			"bPaginate" : false,
			"bInfo" : false,
			"aoColumns" : [ {
				"mData" : "username"
			}, {
				"mData" : "firstName"
			}, {
				"mData" : "lastName"
			}, {
				"mData" : "age"
			}, {
				"mData" : "address.line1"
			}, {
				"mData" : "address.line2"
			}, {
				"mData" : "address.city"
			}, {
				"mData" : "address.pincode"
			}, {
				"mData" : "address.state"
			}, {
				"mData" : "address.country"
			} ]
		});
		
		var page ='${page}';
		if(page == "first"){
			$('#previous').hide();
		}else if(page == "last"){
			$('#next').hide();
		}
	});
</script>