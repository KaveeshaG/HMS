// hide the divisions used to show the status messages on the page load
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide(); 
}); 

//Save button 
$(document).on("click", "#btnSave", function(event)
{
	// Clear status msgesx
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
		 
	// Form validation
	var status = validatePaymentForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid
	//$("#formPayment").submit();
	var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax (
			{  url : "PaymentsAPI", 
			   type : type,  
			   data : $ ("#formPayment").serialize(),  
			   dataType : "text",  
			   complete : function(response, status)  
			   {   
				   onPaymentsSaveComplete(response.responseText, status); 
			    } 
		  });
}); 


function onPaymentsSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divPaymentsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidPaymentIDSave").val("");
	$("#formPayment")[0].reset(); 
}

//CLIENTMODEL
function validatePaymentForm()
{
	

	if ($("#BillID").val().trim() == "")
	{
		return "Bill ID";
	} 
	if ($("#PaymentDate").val().trim() == "")
	{
		return "Insert Payment Date";
	}
	if ($("#PaymentAmount").val().trim() == "")
	{
		return "Insert Payment Amount";
	}
	
	if ($("#PaymentType").val().trim() == "")
	{
		return "Insert Payment Type";
	}
	if ($("#PaymentDescription").val().trim() == "")
	{
		return "InsertPayment Descriptione";
	}

	return true;
}

//update button
$(document).on("click", ".btnUpdate", function(event)
{
	alert($(this).closest("tr").find('#hidPaymentIDUpdate').val());
	$("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val());
	//$("#PaymentID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#BillID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#PaymentDate").val($(this).closest("tr").find('td:eq(1)').text());
	$("#PaymentAmount").val($(this).closest("tr").find('td:eq(2)').text());
	$("#PaymentType").val($(this).closest("tr").find('td:eq(3)').text());
	$("#PaymentDescription").val($(this).closest("tr").find('td:eq(4)').text());
});

//DELETE
$(document).on("click", ".btnRemove", function(event) {  
	
	$.ajax(  {   
		
		url : "PaymentsAPI",   
		type : "DELETE",   
		data : "PaymentID=" + $(this).data("paymentid"),   
		dataType : "text",   
		complete : function(response, status)   {    
			onPaymentsDeleteComplete(response.responseText, status);   
			
		}  
	}); 
	
}); 

function onPaymentsDeleteComplete(response, status) {  
	
	if (status == "success")  {   
		
		var resultSet = JSON.parse(response); 

			if (resultSet.status.trim() == "success")   {    
				
				$("#alertSuccess").text("Successfully deleted.");    
				$("#alertSuccess").show(); 
				$("#divPaymentsGrid").html(resultSet.data);   
				
			} else if (resultSet.status.trim() == "error")   {    
				
				$("#alertError").text(resultSet.data);    
				$("#alertError").show();   
				
			} 

	} else if (status == "error")  {   
		
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
		
	} else  {   
		
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
		
	} 
	
}
