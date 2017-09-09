<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/demo.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script src="lib/jquery-3.2.1.min.js"></script>
</head>
<body>
<form id="form1" action="./RemoveAccount" method="post">
<button id="VPinvia2" type="submit">
Disdici
</button>
</form>
Il tuo abbonamento scade il:
<div id="scad">
</div>
<div id="list">
</div>

<script>
var mail=<%=request.getAttribute("mail")%>;
document.getElementById('form1').action = './RemoveAccount?mail=' + mail;
$.ajax({
    url: "./ListUsers",
    headers: {
  	  "Content-Type":"application/json",
    },
    type: 'GET',          
      dataType: 'json', 
      success: function(data){
         console.log(data);
         console.log("success");
         for(var i=0;i<data.length;i++)
        {
        	 if(data[i].mail==mail)
        		 {
            	 document.getElementById("scad").innerHTML=data[i].scad;
        		 }
         }
      },
      error: function(data){
      	console.log("error");
          console.log(data);
      }
	});
$( document ).ready(function(){
   	$.ajax({
        url: "./ListLetters",
        headers: {
      	  "Content-Type":"application/json",
        },
        type: 'GET',          
          dataType: 'json', 
          success: function(data){
             console.log(data);
             console.log("success");
             for(var i=0;i<data.length;i++)
            {
            	 var name=data[i].name;
            	 document.getElementById("list").innerHTML+="<a href=\""+name+".pdf\">"+name+"</a><br>"; 
            }
          },
          error: function(data){
          	console.log("error");
              console.log(data);
          }
  	});
   	
});
</script>
</body>
</html>