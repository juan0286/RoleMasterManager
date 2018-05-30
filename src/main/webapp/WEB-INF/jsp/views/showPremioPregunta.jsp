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
        <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>
        <div id="center" >
            <div > <a href="verCampaign?id=${campaActual.getId_campaign()}">VOLVER</a></div>

            <h2>${pp.titulo}</h2>
            <table border="2" >  
                <tr>                    
                    <td>Fecha de disparo</td>
                    <td>${pp.fecha}</td>
                </tr>
                <tr>                    
                    <td>Consigna</td>
                    <td>${pp.consigna}</td>
                </tr>
                <tr>                    
                    <td>Tiempo para responder</td>
                    <td>${pp.timeResponse} segundos</td>
                </tr>
            </table>
            <table border="1" class="addItems">
                <tr>
                    <th align="center">Nº</th>
                    <th>Sentencia</th>
                    <th>Respuesta Correcta</th>                        
                </tr>                        

                <c:forEach items="${pp.opciones}" var="opcion" varStatus="status">
                    <tr>
                        <td align="center">${status.count}</td>

                        <c:choose>
                            <c:when test="${opcion.correcta}" >
                                <td align="center"><input disabled value="${opcion.correcta}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td align="center"><input  disabled value="${opcion.correcta}"/></td>
                            </c:otherwise>
                        </c:choose>                       
                    </tr>                        
                </c:forEach>
            </table>
        </div>
    </body>
</html>
