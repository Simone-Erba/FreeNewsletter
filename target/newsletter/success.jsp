<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="Style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script src="lib/jquery-3.2.1.min.js"></script>
</head>
<body>
<div id="succ">
</div>
<script>
function getParam(variable)
{
	var query=window.location.search.substring(1);
	var vars=query.split("&");
	for(var i=0;i<vars.length;i++)
	{
		var pair=vars[i].split("=");
		if(pair[0]==variable)
		{
			return pair[1];	
		}
	}
}
var payment=getParam("paymentId");
console.log(payment);
var access;
var token=getParam("token");
console.log(token);
var email=getParam("email");
console.log(email);
var payer=getParam("PayerID");
console.log(payer);
$.ajax({
    url: "https://api.sandbox.paypal.com/v1/oauth2/token",
   headers: {"Accept":"application/json","Accept-Language":"en_US","Authorization": "Basic " + btoa("AS3VDXnpJNrT9JLiIY9hUBoEJcB6c9VVCSULCl3VGO61uL0xfh4p7d6zO1-k3veNAMXEIXbC8LZRceau" + ":" + "EJHVz-y4jhUAnQ9yN4DqNdmQLrl7msBqtwVX2dL_95t69jZgHodJkj_5qmPuLSLkpT6AkABm1vs2te1g")},
    type: 'POST', // performing a POST request
data: {
"grant_type": "client_credentials",
},
    dataType: 'json', 
    success: function(data){
       access=data.access_token;
       console.log(access);
$.ajax({	
    url: 'https://api.sandbox.paypal.com/v1/payments/payment/'+payment+'/execute/',
    headers:{
    	"Content-Type":"application/json","Authorization":"Bearer "+access,
    },
    dataType: 'json', 

    data:"{\"payer_id\":\""+payer+"\"}",
    type: 'POST',
    success: function(data){
    	console.log(data);
    	console.log("success");
    	
    	$.ajax({
            type: 'POST',
            url: './SubscribeAccount',
            data: 'email='+email,
            error: function(response) {
                // Gets called when an error occurs with error details in variable response
            },
            success: function(response) {
                // Gets called when the action is successful with server response in variable response
            	console.log("utente registrato");
            }
        });
    	document.getElementById('succ').innerHTML += 'Payment executed! You are now registered in the newsletter<br><a href="./Index.html">Home</a>';
    },
    error: function(data){
        console.log(data);
        console.log("error");
        //process the JSON data etc
    }
})
    },
    error: function(data)
    {
    	console.log("error");
    	console.log(data);
    }
    })
</script>
</body>
</html>