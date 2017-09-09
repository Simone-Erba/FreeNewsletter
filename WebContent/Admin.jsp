<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/demo.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script src="lib/jquery-3.2.1.min.js"></script></head>
<body>
	Carica una nuova newsletter:
	<br><form action="./UploadPdf" method="post" enctype="multipart/form-data">
  FILE <input type="file" name="file1">
  <input type="submit" value="Upload">
  </form>
  <br>
  <br>
  <form action="./ListUsers" method="post">
		</form>
		<div id="users">
</div><br><br>
		<div id="list">
</div>
<script>
$( document ).ready(function() {
$.ajax({
        url: "./ListUsers",
        headers: {
      	  "Content-Type":"application/json",
        },
        type: 'GET',          
          dataType: 'json', 
          success: function(data){
             console.log(data.length);
             console.log("success");
             for(var i=0;i<data.length;i++)
            {
            	 var scad=data[i].scad;
            	 var name=data[i].mail;
            	 console.log(scad);
					console.log(name);
            	 document.getElementById("users").innerHTML+=name+"  scadenza  "+scad+"<br>"; 
            
            }
          },
          error: function(data){
          	console.log("error");
              console.log(data);
          }
  	});
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
		var n=2;
         for(var i=0;i<data.length;i++)
        {
        	 var name=data[i].name;
        	 document.getElementById("list").innerHTML+="<a href=\""+name+".pdf\">"+name+"</a>            <a href=\"./RemovePdf?name="+name+"\">delete<a/><br>carica una nuova versione:   <form action=\"./RemovePdfNoXml?name="+name+"&&n="+n+"\" method=\"post\" enctype=\"multipart/form-data\">FILE <input type=\"file\" name=\"file"+n+"\"><input type=\"submit\" value=\"Upload\"></form><br>"; 
        	n++;
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