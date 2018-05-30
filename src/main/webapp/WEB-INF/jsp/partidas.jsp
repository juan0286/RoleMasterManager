<%-- 
    Document   : partidas
    Created on : 24-ene-2018, 23:49:53
    Author     : TiranoJuan
--%>

<%@ page import="com.websystique.spring.model.Master" %>
<%@ page import="com.websystique.spring.model.Campaign" %>


<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE html>
<html ng-app="cdgApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252" ng-controller="tpCont">
        <title>{{titlePage}}</title>        
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>


        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
            <!--              HEADER -->

            <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

            <!--                CUPERPO --> 



            <main class="mdl-layout__content mdl-color--grey-100">


                <div class="mdl-grid" ng-controller="listaPartidasCont">

                    <!--NUEVA PARTIDA-->

                    <div class="mdl-cell mdl-cell--12-col">
                        <div class="fullcard mdl-card mdl-shadow--4dp">
                            <div class="mdl-card__title">
                                <h3>Nueva Partida</h3>
                            </div>                            
                            <div class="mdl-card__actions mdl-card--border">
                                <button class="mdl-button mdl-js-button mdl-button--fab">                                    
                                    <a href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}/crearPartida">
                                        <i class="material-icons">add</i>
                                    </a>
                                </button>
                            </div>
                        </div>
                    </div>                                       


                    <!--LISTA DE PARTIDAS-->

                    <div class="mdl-cell mdl-cell--12-col" ng-repeat="p in partidas" >
                        <div class="fullcard mdl-card mdl-shadow--4dp">
                            <div class="mdl-card__title">
                                <span>{{p.nombre}}</span>
                            </div>                            
                            <div class="mdl-card__subtitle-text">
                                <span>{{p.fecha}}</span>
                            </div>
                            <div class="mdl-card__supporting-text">
                                {{p.resumen}}
                            </div>

                            <div class="mdl-card__actions mdl-card--border">
                                <a href="/RoleMasterManager/m/{{master}}/{{campaActual}}/partida/{{p.idPartida}}">
                                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                                        Ver
                                    </button>                                                                
                                </a>
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
                    $scope.titlePage = "partidas";
                    $scope.user = "${usuarioLogueado.nombre}";

                    $scope.hayPartida = true;
                }

                function listaPartidasCont($scope) {
                    $scope.partidas = [
                        {nombre: "Los capitanes contra Lucifer",
                            fecha: "25/01/2018",
                            resumen: "los capitanes llegan al santuario y los caballeros dorados se encuentan derrotados...",
                            idPartida: 1},
                        {nombre: "Los capitanes contra Abel",
                            fecha: "08/01/2018",
                            resumen: "Neo y los otros contraatacan...",
                            idPartida: 2},
                        {nombre: "Arucas mata a Rediant",
                            fecha: "12/12/2017",
                            resumen: "A poco tiempo de enfrentar a Grymus, se presenta frente a los capitanes...",
                            idPartida: 3}
                    ];
                    $scope.master = "${usuarioLogueado.usuario}";
                    $scope.campaActual = "${campaActual.getNombreURL()}";
                }
                miApp.controller('listaPartidasCont', listaPartidasCont);
                miApp.controller('tpCont', tpCont);
            })();
        </script>  
    </body>


</html>

