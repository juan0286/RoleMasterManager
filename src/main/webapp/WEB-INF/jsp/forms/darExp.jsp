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
        <title>Nuevo Bono Exp</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>
        <div id="center" >
            <div > <a href="verCampaign?id=${campaActual.getId_campaign()}">VOLVER</a></div>
            <h1>Bono Experiencia</h1>

            <form:form modelAttribute="bonoExpForm">
                <form:input path="bono"/> <br/>
                <form:errors path="bono" cssClass="error"/><br/>
                <form:input path="motivo"/> <br/>            
                <form:errors path="motivo" cssClass="error"/><br/>
                <input type="submit" value="Enviar"/><br/>             
                <%--<form:errors path="*" cssClass="error"/>--%>
            </form:form> 

            <h3>Bonos De experiencia</h3>
            <div style="display: block; float: left;">
                <table  border="1"  style="display: block;">  
                    <tr>
                        <td>Bono</td>
                        <td>Motivo</td>
                        <td>Fecha</td>                            
                        <td>Aplicado</td>                            
                    </tr>
                    <c:forEach items="${bes}" var="be" >

                        <tr>
                            <td>${be.getBono()}</td>
                            <td>${be.getMotivo()}</td>
                            <td>${be.getFecha()}</td>                            
                            <td>
                                <c:choose>
                                    <c:when test = "${be.isAplicado()}">
                                        Si
                                    </c:when>
                                    <c:otherwise>
                                        No
                                    </c:otherwise>
                                </c:choose>
                            </td>                            
                        </tr>
                    </c:forEach>                                                        
                </table>
            </div>

            <!--            <form action="crearbonoExp.do" method="POST">
                            <p>
                                <span>Bono de Experiencia para </span>
                                <input type="text" placeholder="Campaña" name="bono"/>
                            </p>
                            <p>
                                <span>Motivo del Bono</span>
                                <input type="text" placeholder="motivo" name="motivo"/>
                            </p>
                                <input type="hidden" value="" name="idpj"/>
                            
                            <button>Enviar</button>
                            <p><span class="error">${expError}</span> </p>           
                        </form>-->
        </div>

    </body>
</html>
