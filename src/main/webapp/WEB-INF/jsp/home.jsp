<%--
  Created by IntelliJ IDEA.
  User: Jerry
  Date: 4/5/14
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.--%>
<%@ page import="com.websystique.spring.model.Master" %>
<%@ page import="com.websystique.spring.model.Campaign" %>

<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<html ng-app="cdgApp" >
    <head>
<!--        <link href="<c:url value="/css/login.css" />" rel="stylesheet">-->
        <link href="<c:url value="/css/main.css" />" rel="stylesheet">
        <title>Home</title>
    </head>
    <body>

        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
            <!--              HEADER -->

            <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

            <!--                CUPERPO --> 


            <main class="mdl-layout__content mdl-color--grey-100">

                <div class="mdl-grid" >

                    <c:choose>
                        <c:when test="${tieneCampas == true}">                    
                            <c:forEach items="${usuarioLogueado.getCampaigns()}" var="campaign" >
                                <div class="mdl-cell mdl-cell--4-col">
                                    <div class="mdl-card mdl-shadow--4dp">
                                        <div class="mdl-card__title">
                                            ${campaign.getNombre()}
                                        </div>
                                        <div class="mdl-card__supporting-text">
                                            Descripcion de la historia, algo asi
                                        </div>
                                        <div class="mdl-card__actions mdl-card--border">
                                            <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaign.getNombreURL()}">Abrir</a>
                                        </div>
                                    </div>                                    
                                </div>

                            </c:forEach>                    
                        </c:when>                    
                    </c:choose>

                    <div class="mdl-cell mdl-cell--4-col">
                        <div class="mdl-card mdl-shadow--4dp">
                            <div class="mdl-card__title">
                                ${campaign.getNombre()}
                            </div>
                            <div class="mdl-card__media"><img src="<c:url value="/img/iconPlus.png" />" width="173" height="157" border="0"
                                                              alt="" style="padding:10px;">
                            </div>                        
                            <div class="mdl-card__actions mdl-card--border">
                                <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" href="${usuarioLogueado.usuario}/nuevaCampaign"> Nueva</a>
                            </div>
                        </div>   
                    </div>

                </div>              
            </main>
        </div>


        <%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>
        <script>
                    (function () {
                        var miApp = angular.module('cdgApp');

                        function tpCont($scope) {
                            $scope.titlePage = 'Campañas';
                            $scope.user = "El usuario";
                        }
                        miApp.controller('tpCont', tpCont);
                    })();
                </script> 
    </body>

</html>
