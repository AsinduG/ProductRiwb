<%@page import="com.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/order.js"></script>

<meta charset="ISO-8859-1">
<title>Order View</title>
</head>
<body>
	<div class="container"><div class="row"><div class="col-8">
	<h2>Products' Orders</h2>
	<form id ='orderForm' name = 'orderForm' method = 'POST' action = 'order.jsp'>
		Product ID :
		<input type= 'text' name='pid' id='pid' class="form-control form-control-sm">
		Researcher ID :
		<input type= 'text' name='researcherId' id='researcherId' class="form-control form-control-sm">
		Date :
		<input type= 'text' name='date' id='date' class="form-control form-control-sm" placeholder="Year/Month/Day">
		Time :
		<input type= 'text' name='time' id='time' class="form-control form-control-sm" placeholder="Hour:Minute:Seconds"
		pattern="[0-9]{3}" required="required"/>
		Total Amount :
		<input type= 'text' name='totAmount' id='totAmount' class="form-control form-control-sm">
		Status :
		<input type= 'text' name='status' id='status' class="form-control form-control-sm">
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave"name="hidItemIDSave" value="">
	</form>
	<div id='alertSuccess' class='alert alert-success'></div>
	<div id='alertError' class='alert alert-danger'></div>
	<br>
	<div id="divItemsGrid">
	 <%
	 Product obj = new Product();
	 out.print(obj.viewOrder());
	 %>
	</div>
	</div></div></div>
</body>
</html>