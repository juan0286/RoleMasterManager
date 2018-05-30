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
        <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

        <h3>Campaña: ${campaActual.getNombre()}</h3>

        <table id="creadores" align="center" border="2" width="50%">

            <tr >                
                <td><a href="nuevoPersonaje">Crear Personaje</a></td>
                <td></td>
                <td></td>                
            </tr>
            <tr >
                <td><a href="nuevoObjetoTipo">Crear Objeto Tipo</a></td>
                <td><a href="nuevoObjeto">Crear Objeto</a></td>
                <td><a href="nuevoArma">Crear Arma</a></td>
            </tr>
            <tr >
                <td><a href="nuevoArea">Crear Area</a></td>
                <td ></td>
                <td ></td>
            </tr>                                 
        </table>

        <%@ include file="/WEB-INF/includes/footer.jsp" %>

    </body>
</html>
