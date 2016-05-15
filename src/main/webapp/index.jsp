<html>
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/util.js'></script> 
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>  
<script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/ajaxController.js'></script>  

<script type="text/javascript">
function sayHello(){
	alert("hello");
	ajaxController.sayHello("我是js", function(data){  
	    alert(data);  
	});  
}
</script>
<body>
<h2>Hello World!</h2>
<input type="button" onclick="sayHello()" value="sayHello"/>
</body>
</html>
