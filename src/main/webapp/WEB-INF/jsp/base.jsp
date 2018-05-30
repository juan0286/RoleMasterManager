<%-- 
    Document   : campaign
    Created on : 29-sep-2017, 20:16:23
    Author     : TiranoJuan
--%>

<%@ page import="com.websystique.spring.model.Master" %>
<%@ page import="com.websystique.spring.model.Campaign" %>


<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE html>
<html ng-app="cdgApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>${campaActual.getNombreURL()}</title>        
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>


        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
            <!--              HEADER -->

            <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

            <!--                CUPERPO --> 



            <main class="mdl-layout__content mdl-color--grey-100">
                <div class="mdl-grid" >
                    <div class="mdl-cell mdl-cell--12-col">

                    </div>                                       

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

            miApp.controller('tpCont', tpCont);
        })();
    </script>   
</html>
