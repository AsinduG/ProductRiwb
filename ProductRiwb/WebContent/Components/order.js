$(document).ready(function()
{
	if($("#alertSuccess").text().trim()=="")
	{
		$("#alertSuccess").hide();
	}
		$("#alertError").hide();
	});
	
	//SAVE Button function
	$(document).on("click" , "#btnSave" , function(event)
	{
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		var status = validateForm();
		if(status != true)
		{
			$("#alertError").text(status);
			$("#alertError").show();
			return;
		}
		 $("#alertSuccess").text("Saved successfully.");
		 $("#alertSuccess").show();
		
		 $("#orderForm")[0].reset();
	});
	
	function validateForm(){
		
		if($("#pid").val().trim() =="")
		{
			return "Project ID is required";	
		}
		if($("#researcherId").val().trim() =="")
		{
			return "researcher ID is required";	
		}
		if($("#date").val().trim() =="")
		{
			return "Date is required";	
		}
		if($("#time").val().trim() =="")
		{
			return "Time is required";	
		}
		if($("#totAmount").val().trim() =="")
		{
			return "Total Amount is required";	
		}
		var amount = $("#totAmount").val().trim();
		if (!$.isNumeric(amount))
		 {
		 	return "Insert a numerical value for total price.";
		 }

		if($("#status").val().trim() =="")
		{
			return "Status is required";	
		}
		else{
			return true;	
		}
			
				
	}
	
	