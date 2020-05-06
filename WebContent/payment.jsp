<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.payment"%>

 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="ISO-8859-1"> 
<title>Payment Management</title> 
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/payment.js"></script> 
</head> 
<body> 
<div class="container">
	<div class="row">
		<div class="col">
 
 		<h1>Payment Management</h1>
	
		 <form id="formPayment" name="formPayment">
       
      Patient Name
        <input id="pname" name="pname" type="text" class="form-control form-control-sm">
       <br> 
       
      Appointment No
       <input id="ano" name="ano" type="text" class="form-control form-control-sm">
       <br>
       
      Appointment Date
        <input id="adate" name="adate" type="text" class="form-control form-control-sm">
        <br>
        
      Amount
        <input id="amount" name="amount" type="text" class="form-control form-control-sm">
       <br>
       
     Card Name
      <div class="col-50">
            <label for="fname">Accepted Cards</label>
            <div class="icon-container">
              <i class="fa fa-cc-visa" style="color:navy;"></i>
              <i class="fa fa-cc-amex" style="color:blue;"></i>
              <i class="fa fa-cc-mastercard" style="color:red;"></i>
              <i class="fa fa-cc-discover" style="color:orange;"></i>
            </div>
            
       <input id="cname" name="cname" type="text" class="form-control form-control-sm">
       <br> 
       
     Card No
       <input id="cardno" name="cardno" type="text" class="form-control form-control-sm">
       <br>
       
     Expire Month
       <input id="expmonth" name="expmonth" type="text" class="form-control form-control-sm">
        <br>
        
     Expire Year
     <input id="expyear" name="expyear" type="text"  class="form-control form-control-sm">
       <br>
       
      CVV
     <input id="cvv" name="cvv" type="text" class="form-control form-control-sm">
      <br>
      
      	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
      	
 			<input type="hidden" id="hidpidSave" name="hidpidSave" value="">
		</form>
		
		<div id=alertSuccess class="alert alert-success"></div>
		<div id=alertError class="alert alert-danger"></div>
		<br>
		<div id="divpaymentGrid">
		<%
			payment paymentObj = new payment(); 
			 out.print(paymentObj.readPayment());
		%> 
		</div>
	</div>
</div>
 	</div>
</body> 
</html> 













































