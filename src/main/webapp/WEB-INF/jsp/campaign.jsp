<%-- 
    Document   : campaign
    Created on : 29-sep-2017, 20:16:23
    Author     : TiranoJuan
--%>

<%@ page import="com.websystique.spring.model.Master" %>
<%@ page import="com.websystique.spring.model.Campaign" %>
<%@ page import="com.websystique.spring.model.caractPj.Hab_secundaria_desarrollo" %>


<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE html>
<html ng-app="cdgApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>${campaActual.getNombreURL()}</title>
        <script>
            $(document).ready(function () {
                $(".hoveroption").hover(
                        function () {
                            $(this).css("background-color", "#BCF5A9")
                            var id = "#table" + $(this).attr("name");
                            $(id).show();
                        }, function () {
                    $(this).css("background-color", "transparent")
                    var id = "#table" + $(this).attr("name");
                    $(id).hide();
                }
                )
            });
        </script>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>


        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
            <!--              HEADER -->

            <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

            <!--                CUPERPO --> 



            <main class="mdl-layout__content mdl-color--grey-100">
                <div class="mdl-grid" >

                    <!--          ----- Mundo Reusmen -----         --> 

                    <div class="mdl-cell mdl-cell--12-col">
                        <div class="fullcard mdl-card mdl-shadow--4dp">
                            <div class="mdl-card__title">
                                <span>${campaActual.getMundo().getNombre()}</span>
                            </div>                            
                            <div class="mdl-card__supporting-text">
                                ${campaActual.getHistoria().getFabula()}
                            </div>
                            <div class="mdl-card__actions mdl-card--border" style="height: 30px">
                                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                                    Más...
                                </button>
                            </div>
                        </div>
                    </div>

                    <!--          ----- Partida programada o nueva -----         --> 

                    <div class="mdl-cell mdl-cell--12-col" ng-show="hayPartida" >                        
                        <div class="fullcard mdl-card mdl-shadow--4dp">
                            <div class="mdl-card__title">
                                <span>Partida - Partida por llegar</span>
                            </div>                            
                            <div class="mdl-card__supporting-text">
                                Los capitanes estan por enfrentar a Grymus y el brujo arriba de un licantropo con Alas
                            </div>
                            <div class="mdl-card__actions mdl-card--border">
                                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                                    Iniciar
                                </button>                                
                                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                                    Posponer
                                </button>
                                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                                    Cancelar
                                </button>
                            </div>
                        </div>
                    </div>



                    <!--          ----- PERSONAJES -----         -->
                    <div class="mdl-cell mdl-cell--12-col" ng-controller="minPjsController">                        

                        <ul class="demo-list-three mdl-list">
                            
                            <li class="mdl-list__item mdl-list__item--three-line" ng-repeat="pj in pjs">
                                <div class="mdl-list__item-primary-content">
                                    <i class="material-icons mdl-list__item-avatar">person</i>
                                    <span>{{ pj.nombre}}</span>         
                                    <div class="mdl-list__item-text-body">
                                        <table>
                                            <tr>
                                                <td>Puntos de vida</td>
                                                <td>
                                                    <meter class="pvMeter" ng-value="pj.pvActual"  min="0" max="{{ pj.pvTotal}}" ></meter>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Puntos de Poder</td>
                                                <td><meter class="ppMeter" ng-value="pj.ppActual" min="0" max="{{ pj.ppTotal}}"></meter></td>
                                            </tr>                                            
                                        </table> 
                                    </div>
                                </div>
                                <a href="*">
                                    <span class="mdl-list__item-secondary-content">
                                        <a href="/RoleMasterManager/m/{{master}}/{{campaActual}}/{{ pj.nombre}}/hojaDePj" >Detalle </a>
                                    </span>
                                </a>
                            </li>  
                        </ul>
                    </div>

                    <%--<%@ include file="/WEB-INF/jsp/includes/jugadorItem.jsp" %>--%>
                    <div style="height: 300px;display: block;">
                        <!--Espacio Vacio-->
                    </div>

                </div>
                <div class="optionsButtonFloating">
                    <button id="menu-speed" class="mdl-button mdl-js-button mdl-button--fab mdl-button--colored">
                        <i class="material-icons">more_vert</i>
                    </button>
                    <ul class="mdl-menu mdl-menu--top-right mdl-js-menu mdl-js-ripple-effect" for="menu-speed">
                        <li class="mdl-menu__item"><a href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}/nuevaPartida">Nueva partida</a> </li>
                        <li class="mdl-menu__item"><a href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}/solicitudes">Ver Solicitudes</a> </li>
                        <li class="mdl-menu__item"><a href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}/invitarJugadores">Invitar Jugadores</a> </li>
                    </ul>
                </div>
            </main>
        </div>

    </body>
    <%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>
    <script>
        (function () {
            var miApp = angular.module('cdgApp');

            function tpCont($scope) {
                $scope.titlePage = "${campaActual.getNombre()}";
                $scope.user = "${usuarioLogueado.nombre}";
                
                $scope.hayPartida = true;
            }
            function minPjsController($scope) {
                $scope.pjs = [
                    {nombre:"Neo",pvActual:30,pvTotal:150,ppActual:5,ppTotal:30,actividad:-10},
                    {nombre:"Kyoros",pvActual:67,pvTotal:140,ppActual:30,ppTotal:45,actividad:-20}
                ];                
                $scope.master = "${usuarioLogueado.usuario}";
                $scope.campaActual = "${campaActual.getNombreURL()}";
            }
            
            
            miApp.controller('minPjsController', minPjsController);
            miApp.controller('tpCont', tpCont);
        })();
    </script>   
</html>
