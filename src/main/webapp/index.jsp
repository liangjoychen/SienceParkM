<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script src="./javascript/jquery-1.11.3.min.js" ></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax( {    
		    url:"/project/subprojectcellect",   
		    data:{     
		    	user_id : 63,
		    	subproject_id :　22
		    },    
		    type:"POST",     
		    dataType:"text",    
		    success:function(data) {
	    	   $("#userinfo").html(data);
		     },    
		     error : function(data) {       
		    	 alert("错误");    
		     }    
	     });
	}); 
</script>
</head>
<body>
	<div id="userinfo"></div>
</body>
</html>