<%--
  Created by IntelliJ IDEA.
  User: Jerry
  Date: 4/5/14
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.--%>
<%@ page import="com.websystique.spring.model.Master" %>
<%@ page import="com.websystique.spring.model.Campaign" %>

<%@ include file="/WEB-INF/jsp/views/include.jsp" %>
<html>
    <head>
<!--        <link href="<c:url value="/css/login.css" />" rel="stylesheet">
        <link href="<c:url value="/css/main.css" />" rel="stylesheet">-->
        <title>Home</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/views/header.jsp" %>

        <h2>Master ${usuarioLogueado.usuario}</h2>
        <div id="campignsZone">
            <c:choose>
                <c:when test="${tieneCampas == true}">
                    <h2> Campañas </h2>
                    <c:forEach items="${usuarioLogueado.getCampaigns()}" var="campaign" >
                        <div class="campaignItem">
                            <a href="verCampaign?id=${campaign.getId_campaign()}">
                                <span>${campaign.getNombre()}</span>
                                
                            </a>
                        </div>
                        <br />
                    </c:forEach>
                    <br />
                </c:when>                    
            </c:choose>
            <div class="campaignItem">
                <a href="nuevaCampaign.htm"> Crear campaña</a>
            </div>
        </div>
    </body>
</html>
