<%-- 
    Document   : solicitudes
    Created on : 03-nov-2017, 17:23:09
    Author     : TiranoJuan
--%>

<%@ page import="com.websystique.spring.model.Master" %>
<%@ page import="com.websystique.spring.model.Campaign" %>
<%@ page import="com.websystique.spring.model.caractPj.Hab_secundaria_desarrollo" %>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>        
        <link href="<c:url value="/css/main.css" />" rel="stylesheet">
              <title>Solicitudes de Acceso a Campaña</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/views/header.jsp" %>
        <div id="center" >
            <h1>Solicitudes</h1>


            <c:forEach items="${cars}" var="jugador" >

                <div class="pjitem">
                    <table class="solicitudestable" border="0"  width="100%">                        
                        <tr>
                            <td><span>${car.getJugador().getNombre()}</span></td>
                            <td><span>${car.getFecha}</span></td>
                            <td>
                                <c:choose>
                                    <c:when test = "${car.getStatus() == CampaignAccessRequest.is()}">
                                        Si
                                    </c:when>
                                    <c:otherwise>
                                        No
                                    </c:otherwise>
                                </c:choose>
                            </td>                        
                            <td ><a href="bonoExp.do?pj=${pj.getId_pj()}">Dar Exp</a></td>
                        </tr>
                    </table>
                </div>                                    
            </c:forEach>
        </div>

    </body>
</html>
