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

<%@ include file="/WEB-INF/jsp/views/include.jsp" %>
<!DOCTYPE html>
<html>
    <head>        
        <link href="<c:url value="/css/main.css" />" rel="stylesheet">
        <script type="text/javascript">
            $(document).ready(function () {
                $('.btn').click(
                        function () {
                            id = $(this).parent().parent().get(0).id;
                            a = $(this).val();
                            $("#img" + id).attr("src", "<c:url value="/img/waiting128.gif" />");                            
                            $.ajax({
                                url: '/RoleMasterManager/solicitar',
                                type: 'POST',
                                data: JSON.stringify({
                                    id_car: id,
                                    accion: a}),
                                processData: false,
                                contentType: "application/json",
                                success: function (data) {
                                    if (data.map.statusRequest == "ACEPTADA") {
                                        $("#img" + id).attr("src", "<c:url value="/img/checkok.png" />");
                                        $("#btn_a"+id).attr('disabled',true);
                                        $("#btn_c"+id).attr("disabled",false)
                                    } else if (data.map.statusRequest == "RECHAZADA") {
                                        $("#img" + id).attr("src", "<c:url value="/img/checkdenied.png" />");
                                        $("#btn_a"+id).attr('disabled',false);
                                        $("#btn_c"+id).attr("disabled",true)
                                    } else {
                                        $("#img" + id).attr("src", "<c:url value="/img/reloj_de_arena.png" />");
                                        $("#btn_a"+id).attr('disabled',false);
                                        $("#btn_c"+id).attr("disabled",false)
                                    }                                    
                                },
                                error: function () {
                                    alert("Se produjo un error, no se pudo aplicar la solicitud");
                                    $("#img" + id).attr("src", "<c:url value="/img/reloj_de_arena.png" />");
                                }
                            })
                            event.preventDefault();
                        }
                );
               
            });

        </script>
        <title>Solicitudes de Acceso a Campaña</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/views/header.jsp" %>
        <div id="center" >
            <h1>Solicitudes</h1>

            <table class="m/solicitudestable" border="2" >                        
                <tr>
                    <th><span>JUGADOR</span></th>
                    <th><span>USUARIO</span></th>
                    <th><span>FECHA</span></th>
                    <th><span>ESTADO</span></th>
                    <th colspan="2"><span>OPCIONES</span></th>
                </tr>                
                <c:forEach items="${cars}" var="car" >
                    <tr id='${car.getId_car()}'>
                        <td><span>${car.getJugador().getNombre()}</span></td>
                        <td><span>${car.getJugador().getNombre_usuario()}</span></td>
                        <td><span>${car.getFecha()}</span></td>                                                                          
                        <c:choose>
                            <c:when test = "${car.getStatus() == "ESPERA"}" >
                                <td><img id='img${car.getId_car()}' width="20" height="20" src="<c:url value="/img/reloj_de_arena.png" />" /></td>
                                <td><button id='btn_a${car.getId_car()}' value="aceptar" class="btn">Aceptar</button></td>
                                <td><button id='btn_c${car.getId_car()}' value="cancelar" class="btn">Rechazar</button></td>                                
                            </c:when>
                            <c:when test = "${car.getStatus() == "ACEPTADA"}">
                                <td><img id='img${car.getId_car()}' width="20" height="20" src="<c:url value="/img/checkok.png" />" /></td>
                                <td><button id='btn_a${car.getId_car()}' value="aceptar" class="btn" disabled>Aceptar</button></td>
                                <td><button id='btn_c${car.getId_car()}' value="cancelar" class="btn">Rechazar</button></td>                                
                            </c:when>
                            <c:when test = "${car.getStatus() == "RECHAZADA"}">
                                <td><img id='img${car.getId_car()}' width="20" height="20" src="<c:url value="/img/checkdenied.png" />" /></td>
                                <td><button id='btn_a${car.getId_car()}' value="aceptar" class="btn" >Aceptar</button></td>
                                <td><button id='btn_c${car.getId_car()}' value="cancelar" class="btn" disabled>Rechazar</button></td>                                
                            </c:when>
                            <c:when test = "${car.getStatus() == "NUEVA"}">
                                <td><img id='img${car.getId_car()}' width="20" height="20" src="<c:url value="/img/reloj_de_arena.png" />" /></td>
                                <td><button id='btn_a${car.getId_car()}' value="aceptar" class="btn" >Aceptar</button></td>
                                <td><button id='btn_c${car.getId_car()}' value="cancelar" class="btn" >Rechazar</button></td>                                
                            </c:when>
                            <c:otherwise>
                                <td><img id='img${car.getId_car()}' width="20" height="20" src="<c:url value="/img/reloj_de_arena.png" />" /></td>
                                <td><button id='btn_a${car.getId_car()}' value="aceptar" class="btn" disabled>Aceptar</button></td>
                                <td><button id='btn_c${car.getId_car()}' value="cancelar" class="btn" disabled>Rechazar</button></td>                                                              
                            </c:otherwise>
                        </c:choose>                                                                       
                    </tr>

                </c:forEach>
            </table>
        </div>

    </body>
</html>
