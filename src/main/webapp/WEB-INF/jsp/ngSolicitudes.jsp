<%-- 
    Document   : ngCrearBonoExp
    Created on : 10-ene-2018, 21:02:58
    Author     : TiranoJuan
--%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

<html ng-app="cdgApp">
    <head>
        <meta charset="UTF-8" />
        <title>Solicitudes de Campaña</title>        
    </head>

    <body ng-controller="ListaController" ng-init="id_c = 1" >

        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
            <!--              HEADER -->

            <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

            <!--                CUPERPO --> 
            <main class="mdl-layout__content mdl-color--grey-100">  
                <!--                <div class="genericForm" ng-controller="BonosExpController" ng-init="nameList = 'bonosExp'">
                                    <span class="titleForm">Nuevo Bono</span>
                                    <input id="idpj" value="${pj.getId_pj()}" ng-model="idpj" class="mdl-textfield__input" type="number" style="display: none;" />
                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                        <label for="bono" class="mdl-textfield__label">Bono</label>
                                        <input id="bono" ng-model="bono" class="mdl-textfield__input" type="number" required />
                                        
                                    </div>
                                    <br/>
                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                        <label for="motivo" class="mdl-textfield__label">Motivo:</label>
                                        <input id="motivo" ng-model="motivo" type="text" class="mdl-textfield__input" required />
                                    </div>
                                    <br/>
                                    <button id="addBtn" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" ng-click="addBono()">Dar Bono</button>
                                    
                                </div>-->


                <div class="genericList" ng-init="getLista('solicitudes')">
                    <table  style=" width:100%; margin-top: 20px;" class="mdl-data-table mdl-js-data-table mdl-data-table mdl-shadow--2dp">
                        <thead>
                            <tr>
                                <th><span>FECHA</span></th>
                                <th><span>JUGADOR</span></th>
                                <th><span>USUARIO</span></th>                                
                                <th><span>ESTADO</span></th>
                                <th colspan="2"><span>OPCIONES</span></th>

                            </tr>
                        </thead>
                        <tbody>
                             long id_solicitud;   
                            <tr ng-repeat="s in lista| orderBy:'-fecha'">
                                <td>{{s.fecha}}</td>                          
                                <td>{{s.nombre_jugador}}</td>
                                <td>{{s.nombre_usuario}}</td>
                                <td>{{s.status}}</td>                                
                                <td><button class="mdl-button mdl-js-button mdl-button--primary" {{s.aceptable}}> Aceptar</button> </td>
                                <td><button class="mdl-button mdl-js-button mdl-button--primary" {{s.cancelar}}> Cancelar</button> </td>
                            </tr>

                        </tbody>
                    </table>

                    <div id="spinner" style="margin-left: auto; margin-right: auto;" class="mdl-spinner mdl-spinner--single-color mdl-js-spinner"></div>
                </div>
                <div aria-live="assertive" aria-atomic="true" aria-relevant="text" class="mdl-snackbar mdl-js-snackbar">
                    <div class="mdl-snackbar__text"></div> 
                    <button type="button" class="mdl-snackbar__action"></button>
                </div>
                <!--
                            <div class="container" ng-show="films != null">
                                <div class="film-info-bottom" ng-repeat="be in bonos">
                                    <h4><span> {{be.year}} - {{film.title}} - {{film.director}} </span></h4>
                                </div>
                            </div>-->
            </main>
        </div>
        <!--<script src="<c:url value="/js/app.js" />"></script>-->  


        <%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>  
        <script>
                    (function () {
                        var miApp = angular.module('cdgApp');

                        function tpCont($scope) {
                            $scope.titlePage = "Solicitudes de Campaign";
                            $scope.user = "El usuario";                            
                        }
                        miApp.controller('tpCont', tpCont);
                    })();
        </script>        
    </body>
</html>