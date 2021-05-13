$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 	$("#alertError").hide();
	});
	// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
		{
		// Clear alerts---------------------
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		// Form validation-------------------
		var status = validateItemForm();
		if (status != true)
		 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
		 }
		// If valid------------------------
		var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
		 $.ajax(
		 {
		 url : "ItemsAPI",
		 type : type,
		 data : $("#orderForm").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onItemSaveComplete(response.responseText, status);
		 }
		 });
	
		function onItemSaveComplete(response, status)
	{
		if (status == "success")
		 {
		 	var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
			 $("#alertSuccess").text("Successfully saved.");
			 $("#alertSuccess").show();
			 $("#divItemsGrid").html(resultSet.data);
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
	
	}
	
		});

	// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	 $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	 $("#pid").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#researcherId").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#date").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#time").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#totAmount").val($(this).closest("tr").find('td:eq(4)').text());
	 $("#status").val($(this).closest("tr").find('td:eq(5)').text());
	});
// CLIENT-MODEL================================================================

	$(document).on("click", ".btnRemove", function(event)
	{
	 $.ajax(
	 {
	 url : "ItemsAPI",
	 type : "DELETE",
	 data : "orderId=" + $(this).data("itemid"),
	 dataType : "text",
	 complete : function(response, status)
	 {
	 onItemDeleteComplete(response.responseText, status);
	 }
	 });

	function onItemDeleteComplete(response, status)
	{
		if (status == "success")
		 {
		 	var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 $("#divItemsGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
		 } else if (status == "error")
		 {
			 $("#alertError").text("Error while deleting.");
			 $("#alertError").show();
		 } else
		 {
			 $("#alertError").text("Unknown error while deleting..");
			 $("#alertError").show();
		 }
	}
	});
	function validateItemForm()
	{
		// CODE
		if ($("#pid").val().trim() == "")
		 {
		 	return "Insert Project ID.";
		 }
		// NAME
		if ($("#researcherId").val().trim() == "")
		 {
		 	return "Insert researcherId.";
		 } 
	
		// PRICE-------------------------------
		if ($("#date").val().trim() == "")
		 {
		 	return "Insert Date.";
		 }
		if ($("#time").val().trim() == "")
		 {
		 	return "Insert Time.";
		 }
		// is numerical value
		var tmpPrice = $("#totAmount").val().trim();
		if (!$.isNumeric(tmpPrice))
		 {
		 	return "Insert a numerical value for total Amount.";
		 }
		// DESCRIPTION------------------------
		if ($("#status").val().trim() == "")
		 {
		 	return "Insert status.";
		 }
		
		
		return true;

	
	
	
	
}
