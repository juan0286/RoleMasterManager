<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<c:url value="/css/main.css" />" rel="stylesheet">
<script src="<c:url value="/js/jquery-1.4.3.js" />"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<!--<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">-->
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.yellow-light_blue.min.css" />
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<script src="https://code.angularjs.org/1.6.6/angular.js" />"></script>  
<script src="https://code.angularjs.org/1.6.6/angular-route.js" />"></script>  
<script src="https://code.angularjs.org/1.6.6/angular-resource.js" />"></script>  
<script >
    $(document).ready(function() {
	$('#themeselect').find('a').click(function(){
		var value = $(this).attr('data-color');
		$('.navbar').removeClass().addClass('navbar '+ value);

		return false;
	});		
});
</script>  
