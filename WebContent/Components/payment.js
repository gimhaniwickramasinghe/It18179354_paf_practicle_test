$(document).ready(function() {
	if ($("#alertSuccess").text().trim()  == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================

$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validatePaymentForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------

	var type = ($("#hidpidSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "paymentAPI",
		type : type,
		data : $("#formPayment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onpaymentSaveComplete(response.responseText, status);
		}
	});

});

function onpaymentSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divpaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidpidSave").val("");
	$("#formPayment")[0].reset();
}

// REMOVE------------------------------
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "paymentAPI",
		type : "DELETE",
		data : "pid=" + $(this).data("pid"),
		dataType : "text",
		complete : function(response, status) {
			onpaymentDeleteComplete(response.responseText, status);
		}
	});
});

function onpaymentDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();

			$("#divpaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {

	$("#hidpidSave").val($(this).closest("tr").find('#hidpidUpdate').val());
	$("#pname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#ano").val($(this).closest("tr").find('td:eq(1)').text());
	$("#adate").val($(this).closest("tr").find('td:eq(2)').text());
	$("#amount").val($(this).closest("tr").find('td:eq(3)').text());
	$("#cname").val($(this).closest("tr").find('td:eq(4)').text());
	$("#cardno").val($(this).closest("tr").find('td:eq(5)').text());
	$("#expmonth").val($(this).closest("tr").find('td:eq(6)').text());
	$("#expyear").val($(this).closest("tr").find('td:eq(7)').text());
	$("#cvv").val($(this).closest("tr").find('td:eq(8)').text());

});

// CLIENTMODEL=========================================================================
function validatePaymentForm() {
	// pname
	if ($("#pname").val().trim() == "") {
		return "Insert patient name.";
	}

	// ano
	if ($("#ano").val().trim() == "") {
		return "Insert appointment no.";
	}

	// adate
	if ($("#adate").val().trim() == "") {
		return "Insert appointment date.";
	}

	// amount
	if ($("#amount").val().trim() == "") {
		return "Insert amount.";
	}

	// is numerical value
	var tmpamount = $("#amount").val().trim();
	if (!$.isNumeric(tmpamount)) {
		return "Insert a numerical value for amount.";
	}

	// convert to decimal price
	$("#amount").val(parseFloat(tmpamount).toFixed(2));

	// cname
	if ($("#cname").val().trim() == "") {
		return "Insert card name.";
	}
	// cardno
	if ($("#cardno").val().trim() == "") {
		return "Insert card no.";
	}
	
	// is numerical value
	var tmpcardno = $("#cardno").val().trim();
	if (!$.isNumeric(tmpcardno)) {
		return "Insert a numerical value for card no.";
	}

	// expmonth
	if ($("#expmonth").val().trim() == "") {
		return "Insert expire month.";
	}
	// expyear
	if ($("#expyear").val().trim() == "") {
		return "Insert expire year.";
	}
	
	// is numerical value
	var tmpexpyear = $("#expyear").val().trim();
	if (!$.isNumeric(tmpexpyear)) {
		return "Insert a numerical value for expire year.";
	}

	// cvv
	if ($("#cvv").val().trim() == "") {
		return "Insert cvv.";
	}

	// is numerical value
	var tmpcvv = $("#cvv").val().trim();
	if (!$.isNumeric(tmpcvv)) {
		return "Insert a numerical value for cvv.";
	}

	return true;
}