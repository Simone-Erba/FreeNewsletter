<!DOCTYPE html>
<html lang="en">
    <head>
        <title>SO question 2370960</title>
    </head>
    <body>
    <div id="node-id"></div>

    <script>
    console.log("Hello");
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
    var mess=getParam("message");
    var node = document.getElementById('node-id');
    
    node.innerHTML="<iframe src=\"./letter.pdf&embedded=true\" style=\"width:600px; height:500px;\" frameborder=\"0\"></iframe>";
</script>
    </body>
</html>