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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/views/include.jsp" %>
<!DOCTYPE html>
<html>
    <head>               
        <link href="<c:url value="/css/main.css" />" rel="stylesheet">  
        <link href="<c:url value="/css/calendar-green.css" />" rel="stylesheet">  
        <script src="<c:url value="/JSCal2/js/calendar.js" />"></script>         
        <script src="<c:url value="/JSCal2/js/calendar-setup.js" />"></script>         
        <script src="<c:url value="/JSCal2/js/dateSelector.js" />"></script>         
        <title>Nueva Premio Pregunta</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/views/header.jsp" %>        
        <div id="center" >
            <div > <a href="verCampaign?id=${campaActual.getId_campaign()}">VOLVER</a></div>
            <spring:url value="/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}" var="volver" />

            <h2>Crear un premio Pregunta</h2>
            <form:form modelAttribute="premioPreguntaForm">
                <table>
                    <tr>
                        <td>Titulo</td>
                        <td><form:input path="titulo"/></td>
                    </tr>                
                    <tr>
                        <td colspan="2"><form:errors path="titulo" cssClass="error"/></td>
                    </tr>

                    <tr>
                        <td>Fecha de disparo</td>
                        <td><form:input path="fecha" id="campo_fecha"/><button type="button" id="calendar-trigger">Seleccionar</button></td>
                    </tr>                
                    <tr>
                        <td colspan="2"><form:errors path="fecha" cssClass="error"/></td>
                    </tr>

                    <tr>
                        <td>Consigna</td>
                        <td><form:input path="consigna"/></td>
                    </tr>                
                    <tr>
                        <td colspan="2"><form:errors path="consigna" cssClass="error"/></td>
                    </tr>

                    <tr>
                        <td>Tiempo para responder(Segundos)</td>
                        <td><form:input path="timeResponse"/></td>
                    </tr>                
                    <tr>
                        <td colspan="2"><form:errors path="timeResponse" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td>Cantidad de ganadores posible</td>
                        <td><form:input path="cantGanadores"/></td>
                    </tr>                
                    <tr>
                        <td colspan="2"><form:errors path="cantGanadores" cssClass="error"/></td>
                    </tr>

                    <tr>
                        <td>Activar WaitForAll?</td>
                        <td><form:checkbox path="waitForAll"/></td>
                    </tr>                                    
                </table>

                <h3>Opciones</h3>
                <table border="1" class="addItems">
                    <tr>
                        <th align="center">Nº</th>
                        <th>Sentencia</th>
                        <th>Respuesta




                    </tr>