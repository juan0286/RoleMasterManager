<%-- 
    Document   : campaign
    Created on : 29-sep-2017, 20:16:23
    Author     : TiranoJuan
--%>

<%@ page import="com.websystique.spring.model.Master" %>
<%@ page import="com.websystique.spring.model.Campaign" %>
<%@ page import="com.websystique.spring.model.caractPj.Hab_secundaria_desarrollo" %>


<%@ include file="/WEB-INF/jsp/views/include.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>${campaActual.getNombre()}</title>
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
        <%@ include file="/WEB-INF/jsp/views/header.jsp" %>

        <h3>Campa�a: ${campaActual.getNombre()}</h3>

        <a href="${campaActual.nombre}/solicitudes">Ver Solicitudes</a>        
        
        <div class="campaignItem">
            <a href="invitarJugador.htm"> Invitar Jugador</a>
        </div>
                            
        <%@ include file="/WEB-INF/jsp/views/jugadorItem.jsp" %>
                            
        <%@ include file="/WEB-INF/jsp/views/footer.jsp" %>

    </body>
</html>
