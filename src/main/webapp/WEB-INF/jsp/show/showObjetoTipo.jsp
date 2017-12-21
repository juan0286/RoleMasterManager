<%-- 
    Document   : darExp
    Created on : 04-oct-2017, 11:37:33
    Author     : TiranoJuan
--%>

<%@ page import="com.websystique.spring.model.Master" %>
<%@ page import="com.websystique.spring.model.Campaign" %>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>        
        <link href="<c:url value="/css/main.css" />" rel="stylesheet">
        <title>Nuevo Tipo de Objeto</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/views/header.jsp" %>
        <div id="center" >
            <div > <a href="verCampaign?id=${campaActual.getId_campaign()}">VOLVER</a></div>

            <table border="2" >                
                <tr>                    
                    <td>Nombre</td>
                    <td>${to.nombre}</td>
                </tr>
                <tr>                    
                    <td>Tamaño Estandar</td>
                    <td>${to.tam_stnd}</td>
                </tr>
                <tr>                    
                    <td>Degradado</td>
                    <td>${to.degradado}</td>
                </tr>
                <tr>                    
                    <td>Peso Estandard</td>
                    <td>${to.peso_stnd}</td>
                </tr>
                <tr>                    
                    <td>Durabilidad Stnd</td>
                    <td>${to.durabilidad_stnd}</td>
                </tr>
            </table>
                <h3>Funcion que Agrega Infos</h3>
                <span>A desarrollar...</span>
        </div>
    </body>
</html>
