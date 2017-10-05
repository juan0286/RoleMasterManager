<%-- 
    Document   : include
    Created on : 29-sep-2017, 12:24:00
    Author     : TiranoJuan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link href="<c:url value="/css/login.css" />" rel="stylesheet">
<link href="<c:url value="/css/main.css" />" rel="stylesheet">
<script src="<c:url value="/js/jquery-1.4.3.js" />"></script>  
<script >
    $(document).ready(function() {
	$('#themeselect').find('a').click(function(){
		var value = $(this).attr('data-color');
		$('.navbar').removeClass().addClass('navbar '+ value);
		
		return false;
	});		
});
</script>  