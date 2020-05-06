<%@page import="com.hcs.controller.Payments" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Payment Details</h1>
				<form id="formPayment" name="formPayment">
					BillID: <input id="BillID" name="BillID" type="text"
						class="form-control form-control-sm"> <br>
					PaymentDate: <input id="PaymentDate" name="PaymentDate" type="date"
						class="form-control form-control-sm"> PaymentAmount: <input
						id="PaymentAmount" name="PaymentAmount" type="text"
						class="form-control form-control-sm"> <br>
					PaymentType : <input id="PaymentType" name="PaymentType"
						type="text" class="form-control form-control-sm"> <br>
					Payment Description: <input id="PaymentDescription"
						name="PaymentDescription" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidPaymentIDSave" name="hidPaymentIDSave" hidPaymentIDSave"" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divPaymentsGrid">
				<%
					Payments PaymentObj = new Payments();
					out.print(PaymentObj.readPayments());
					%>
				</div>

			</div>
		</div>
	</div>

</body>
</html>