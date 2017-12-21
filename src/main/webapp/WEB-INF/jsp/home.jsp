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
        <h2> Campañas </h2>
        <div id="campignsZone">
            <c:choose>
                <c:when test="${tieneCampas == true}">                    
                    <c:forEach items="${usuarioLogueado.getCampaigns()}" var="campaign" >

                        <div class="campaignItem">
                            <a href="${usuarioLogueado.usuario}/${campaign.getNombreURL()}">
                            <!--<a href="verCampaign?id=${campaign.getId_campaign()}">-->
                                <span>${campaign.getNombre()}</span>
                            </a>
                        </div>
                    </c:forEach>                    
                </c:when>                    
            </c:choose>
            <div class="campaignItem" style="display: table;">
                <a href="${usuarioLogueado.usuario}/nuevaCampaign"> 
                    <!--<span style="display: table-row;height: 50%">Crear campaña</span>-->
                    <img src="<c:url value="/img/iconPlus.png" />"  width="70" height="70" 
                         style="margin-top: 5%; display: block;
                         margin: auto;"/>
                </a>
            </div>
        </div>
    </body>
</html>
