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
     
            <form:form modelAttribute="ObjetoTipoForm">
                <table>
                    <tr>
                        <td>Nombre</td>
                        <td><form:input path="nombre"/></td>
                    </tr>                
                    <tr>
                        <td colspan="2"><form:errors path="nombre" cssClass="error"/></td>
                    </tr>
                    
                    <tr>
                        <td>Tamaño</td>
                        <td><form:input path="tam_stnd"/></td>
                    </tr>                
                    <tr>
                        <td colspan="2"><form:errors path="tam_stnd" cssClass="error"/></td>
                    </tr>
                
                    <tr>
                        <td>Peso</td>
                        <td><form:input path="peso_stnd"/></td>
                    </tr>                
                    <tr>
                        <td colspan="2"><form:errors path="peso_stnd" cssClass="error"/></td>
                    </tr>
                
                    <tr>
                        <td>Degradado</td>
                        <td><form:input path="degradado"/></td>
                    </tr>                
                    <tr>
                        <td colspan="2"><form:errors path="degradado" cssClass="error"/></td>
                    </tr>
                
                    <tr>
                        <td>Durabilidad</td>
                        <td><form:input path="durabilidad_stnd"/></td>
                    </tr>                
                    <tr>
                        <td colspan="2"><form:errors path="durabilidad_stnd" cssClass="error"/></td>
                    </tr>                
               
                </table>
                <input type="submit" value="Guardar"/><br/>                         
            </form:form> 

        </div>

    </body>
</html>
