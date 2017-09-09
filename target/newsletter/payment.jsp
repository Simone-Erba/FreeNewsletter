
<!DOCTYPE html>

<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script src="lib/jquery-3.2.1.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <script src="https://www.paypalobjects.com/api/checkout.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    
</head>

<body>

    Insersci la tua mail: <input name="email" id="email" type="email" required><br><br>

Subscribe to the newsletter:
Cost: 10 $<br>
    <div id="paypal-button-container"></div>
    
    <br>
    <div class="container-fluid">
 
        <div class="creditCardForm">
            <div class="heading">
                <h1>Credit Card Purchase</h1>
            </div>
            <div class="payment">
                <form>
                    <div class="form-group owner">
                        <label for="owner">name</label>
                        <input type="text" class="form-control" id="name">
                         <label for="owner">surname</label>
                        <input type="text" class="form-control" id="surname">
                    </div>
                    <div class="form-group CVV">
                        <label for="cvv">CVV</label>
                        <input type="text" class="form-control" id="cvv">
                    </div>
                    <div class="form-group" id="card-number-field">
                        <label for="cardNumber">Card Number</label>
                        <input type="text" class="form-control" id="cardNumber">
                    </div>
                    <div class="form-group" id="expiration-date">
                        <label>Expiration Date</label>
                        <select id="month">
                            <option value="01">January</option>
                            <option value="02">February </option>
                            <option value="03">March</option>
                            <option value="04">April</option>
                            <option value="05">May</option>
                            <option value="06">June</option>
                            <option value="07">July</option>
                            <option value="08">August</option>
                            <option value="09">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select>
                        <select id="year">
                            <option value="2016"> 2016</option>
                            <option value="2017"> 2017</option>
                            <option value="2018"> 2018</option>
                            <option value="2019"> 2019</option>
                            <option value="2020"> 2020</option>
                            <option value="2021"> 2021</option>
                        </select>
                    </div>
                    <div class="form-group" id="credit_cards">
                        <img src="images/visa.jpg" id="visa">
                        <img src="images/mastercard.jpg" id="mastercard">
                        <img src="images/amex.jpg" id="amex">
                    </div>

                </form>
            </div>
        </div>
            <div id="paypal-button-container2"></div>

        <p class="examples-note">Here are some dummy credit card numbers and CVV codes so you can test out the form:</p>

        <div class="examples">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Type</th>
                            <th>Card Number</th>
                            <th>Security Code</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Visa</td>
                            <td>4716108999716531</td>
                            <td>257</td>
                        </tr>
                        <tr>
                            <td>Master Card</td>
                            <td>5281037048916168</td>
                            <td>043</td>
                        </tr>
                        <tr>
                            <td>American Express</td>
                            <td>342498818630298</td>
                            <td>3156</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
    email=document.getElementById("email");
	
		
		paypal.Button.render({
            env: 'sandbox', // sandbox | production
            // PayPal Client IDs - replace with your own
            // Create a PayPal app: https://developer.paypal.com/developer/applications/create
            client: {
                sandbox:    'AS3VDXnpJNrT9JLiIY9hUBoEJcB6c9VVCSULCl3VGO61uL0xfh4p7d6zO1-k3veNAMXEIXbC8LZRceau',
                production: '<insert production client id>'
            },
            commit: true,
            payment: function(data, actions) {
                email=document.getElementById("email");
            	var str="{\"intent\": \"sale\",\"payer\": {\"payment_method\": \"credit_card\",\"funding_instruments\": [{\"credit_card\": {\"number\":\""+document.getElementById("cardNumber").value+"\",\"type\": \"visa\",\"expire_month\":\""+ document.getElementById("month").value+"\",\"expire_year\":\""+ document.getElementById("year").value+"\",\"cvv2\":\""+ document.getElementById("cvv").value+"\",\"first_name\":\""+ document.getElementById("name").value+"\",\"last_name\":\""+ document.getElementById("surname").value+"\",\"billing_address\": {\"line1\": \"111 First Street\",\"city\": \"Saratoga\",\"state\": \"CA\",\"postal_code\": \"95070\",\"country_code\": \"US\"}}}]},\"transactions\": [{\"amount\": {\"total\": \"0.01\",\"currency\": \"USD\", \"details\": {\"subtotal\": \"0.01\",\"tax\": \"0.00\",\"shipping\": \"0.00\"}},\"description\": \"The payment transaction description.\"}]}";
        		console.log(str);
		$.ajax({
	        url: "https://api.sandbox.paypal.com/v1/oauth2/token",
	        headers: {"Accept":"application/json","Accept-Language":"en_US","Authorization": "Basic " + btoa("AS3VDXnpJNrT9JLiIY9hUBoEJcB6c9VVCSULCl3VGO61uL0xfh4p7d6zO1-k3veNAMXEIXbC8LZRceau" + ":" + "EJHVz-y4jhUAnQ9yN4DqNdmQLrl7msBqtwVX2dL_95t69jZgHodJkj_5qmPuLSLkpT6AkABm1vs2te1g")},
	        type: 'POST', // performing a POST request
	  data: {
	    "grant_type": "client_credentials",
	  },
	        dataType: 'json', 
	        success: function(data){
	        	alert("success");
	          var access=data.access_token;
	          console.log(access);
	        	   $.ajax({
	       	       url: "https://api.sandbox.paypal.com/v1/payments/payment",
	       	       headers: {"Content-Type":"application/json","Authorization": "Bearer " + access},
	       	       type: 'POST', // performing a POST request
	       	  data: str,
	       	  dataType: 'json', 
		        success: function(data){
		        	 console.log(data);
	                   console.log("success");
	                   id=data.transactions[0].related_resources[0].sale.id;
	                   console.log(id);
	                   $.ajax({
	                	   url: "https://api.sandbox.paypal.com/v1/payments/sale/"+id,
	           	        headers: {"Content-Type":"application/json","Authorization":"Bearer " + access},
	           	        type: 'GET', // performing a POST request
	           	     dataType: 'json', 
	     	        success: function(data){
	     	        console.log(data);
	     	        console.log("ci sono");
	     	       window.location.replace("./SubscribeAccount?mail="+email.value);
	     	        },
	     	        error: function(data)
	                   {
	     	        	console.log(data);
	                   },
	                   });
		        },
		        error: function(data){
		        	console.log("error");
		        	console.log(data);
		        },
		});
	},
    error: function(data){
    	console.log("error");
    	console.log(data);
    },
		});
	        },
            onAuthorize: function(data, actions) {
            alert("autorized");
            }
		 }, '#paypal-button-container2');
				
		
    var id;
    var url;
        paypal.Button.render({

            env: 'sandbox', // sandbox | production

            // PayPal Client IDs - replace with your own
            // Create a PayPal app: https://developer.paypal.com/developer/applications/create
            client: {
                sandbox:    'AS3VDXnpJNrT9JLiIY9hUBoEJcB6c9VVCSULCl3VGO61uL0xfh4p7d6zO1-k3veNAMXEIXbC8LZRceau',
                production: '<insert production client id>'
            },

            // Show the buyer a 'Pay Now' button in the checkout flow
            commit: true,

            // payment() is called when the button is clicked
            payment: function(data, actions) {

                // Make a call to the REST api to create the payment
				$.ajax({
        url: "https://api.sandbox.paypal.com/v1/oauth2/token",
 
       headers: {"Accept":"application/json","Accept-Language":"en_US","Authorization": "Basic " + btoa("AS3VDXnpJNrT9JLiIY9hUBoEJcB6c9VVCSULCl3VGO61uL0xfh4p7d6zO1-k3veNAMXEIXbC8LZRceau" + ":" + "EJHVz-y4jhUAnQ9yN4DqNdmQLrl7msBqtwVX2dL_95t69jZgHodJkj_5qmPuLSLkpT6AkABm1vs2te1g")},
        type: 'POST', // performing a POST request
 
  data: {
    "grant_type": "client_credentials",
  },
        dataType: 'json', 
        success: function(data){
           var access=data.access_token;
           console.log(access);
           
        	$.ajax({
              url: "https://api.sandbox.paypal.com/v1/payments/payment",
              headers: {
            	  "Content-Type":"application/json","Authorization":"Bearer "+access
              },
              type: 'POST',
              data:"{\"intent\": \"sale\",\"redirect_urls\": {\"return_url\": \"http://localhost:8080/newsletter/success.jsp?email="+email.value+"\",\"cancel_url\": \"http://localhost:8080/newsletter/error.html\"},\"payer\": {\"payment_method\": \"paypal\"},\"transactions\": [{\"amount\": {\"total\": \"0.01\",\"currency\": \"USD\"}}]}",
                
                dataType: 'json', 
                success: function(data){
                   console.log(data);
                   console.log("success");
                   id=data.id;
                   url=data.links[1].href;
                   
                   window.location.replace(url+"&acc="+access);
                },
                error: function(data){
                	console.log("error");
                    console.log(data);
                }
        	})
        },
        error: function(data){
        	console.log("error");
            console.log(data);
        },
})               
                return actions.payment.create({
                    payment: {
                        transactions: [
                            {
                                amount: { total: '0.01', currency: 'USD' }
                            }
                        ]
                    }
               // return data.id;
                });
            },

            // onAuthorize() is called when the buyer approves the payment
            onAuthorize: function(data, actions) {
            	console.log("fuori");
                // Make a call to the REST api to execute the payment
                return actions.payment.execute().then(function() {
                	console.log("dentro");
                	$.ajax({
                			
                        url: 'https://api.sandbox.paypal.com/v1/payments/payment/'+id+'/execute',
                        headers:{
                        	"Content-Type":"application/json","Authorization":"Bearer "+access
                        },
                        data:"{\"payer_id\":\""+data.payerID+"\"}",
                        type: 'GET',
                        success: function(data){                        	console.log(data);
                        	console.log("success");
                            console.log(data);
                        },
                        error: function(data){
                            console.log(data);
                            console.log("error");
                            //process the JSON data etc
                        },
                })
                    window.alert('Payment Complete!');
                });
            }

        }, '#paypal-button-container');
    </script>
</body>