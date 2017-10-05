<%-- 
    Document   : crearCampaign
    Created on : 26-sep-2017, 0:07:48
    Author     : TiranoJuan
--%>
<%@ page import="com.websystique.spring.model.Master" %>
<%@ page import="com.websystique.spring.model.Campaign" %>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>        
        <link href="<c:url value="/css/main.css" />" rel="stylesheet">
        <title>Crear Campaña</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/views/header.jsp" %>
        <div id="center" >
            <h1>Crear Campaña</h1>
            <form action="salvarcampaign.htm" method="POST">
                <p>
                    <span>Nombre de Campaña</span>
                    <input type="text" placeholder="Campaña" name="nombreCampaign"/>
                </p>
                <p>
                    <span>Nombre de Mundo</span>
                    <input type="text" placeholder="Mundo" name="nombreMundo"/>
                </p>
                <p>
                    <span>Fecha en el Mundo</span>
                    <input type="text" placeholder="dd/mm/yyyy" name="fechaMundo"/>
                </p>
                <button>Crear</button>
                <p><span class="error">${errorNewCampaign}</span> </p>           
            </form>
        </div>

    </body>
</html>
