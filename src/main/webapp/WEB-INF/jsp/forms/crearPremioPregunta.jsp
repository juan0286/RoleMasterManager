<%-- 
    Document   : darExp
    Created on : 04-oct-2017, 11:37:33
    Author     : TiranoJuan
--%>

<%@ page import="com.websystique.spring.model.Master" %>
<%@ page import="com.websystique.spring.model.Campaign" %>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE html>
<html ng-app="cdgApp">
    <head>               
        <link href="<c:url value="/css/main.css" />" rel="stylesheet">  
        <link href="<c:url value="/css/calendar-green.css" />" rel="stylesheet">          
        <title>Nueva Premio Pregunta</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
            <!--              HEADER -->

            <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

            <!--                CUPERPO --> 

            <main class="mdl-layout__content mdl-color--grey-100">
                <div class="mdl-grid" >

                    <c:out value="${error}" />
                    
                    <form:form modelAttribute="premioPreguntaForm">
                        <table>
                            <tr>
                                <td>Titulo</td>
                                <td><form:input path="titulo"/></td>
                            </tr>                
                            <tr>
                                <td colspan="2"><form:errors path="titulo" cssClass="error"/></td>
                            </tr>

                            <tr>
                                <td>Fecha de disparo</td>
                                <td><form:input path="fecha" id="campo_fecha"/><button type="button" id="calendar-trigger">Seleccionar</button></td>
                            </tr>                
                            <tr>
                                <td colspan="2"><form:errors path="fecha" cssClass="error"/></td>
                            </tr>

                            <tr>
                                <td>Consigna</td>
                                <td><form:input path="consigna"/></td>
                            </tr>                
                            <tr>
                                <td colspan="2"><form:errors path="consigna" cssClass="error"/></td>
                            </tr>

                            <tr>
                                <td>Tiempo para responder(Segundos)</td>
                                <td><form:input path="timeResponse"/></td>
                            </tr>                
                            <tr>
                                <td colspan="2"><form:errors path="timeResponse" cssClass="error"/></td>
                            </tr>
                            <tr>
                                <td>Cantidad de ganadores posible</td>
                                <td><form:input path="cantGanadores"/></td>
                            </tr>                
                            <tr>
                                <td colspan="2"><form:errors path="cantGanadores" cssClass="error"/></td>
                            </tr>

                            <tr>
                                <td>Activar WaitForAll?</td>
                                <td><form:checkbox path="waitForAll"/></td>
                            </tr>                                    
                        </table>

                        <h3>Opciones</h3>
                        <table border="1" class="addItems">
                            <tr>
                                <th align="center">Nº</th>
                                <th>Sentencia</th>
                                <th>Respuesta

                                    <c:forEach items="${premioPreguntaForm.opciones}" var="opcion" varStatus="status">
                                <tr>
                                    <td align="center">${status.count}</td>
                                    <td align="center"><form:input path="opciones[${status.index}].sentencia" name="opcion[${status.index}].sentencia" value="${opcion.sentencia}"/></td>
                                    <td align="center"><form:checkbox path="opciones[${status.index}].correcta" name="opcion[${status.index}].correcta" /></td>                                                  
                                </tr>                        
                            </c:forEach>
                            <spring:bind path="personajes">
                                <div class="sas">
                                    <label class="col-sm-2 control-label">Personajes a quienes enviar</label>
                                    <div class="col-sm-10">
                                        <form:checkboxes path="personajes" items="${premioPreguntaForm.personajes}"
                                                         element="label class='checkbox-inline'" />
                                        <br />
                                        <form:errors path="personajes" class="control-label" />
                                    </div>
                                </div>
                            </spring:bind>
                            </br>
                            <tr>
                                <td colspan="3" align="center"><input type="button" id="addOpcion" value="Agregar Opcion..." /></td>                            
                            </tr>                        
                        </table>
                        </br>
                        <input type="submit" value="Crear"/><br/>                         
                    </form:form> 
                </div>
            </main>
        </div>
    </body>
    <%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>
    <script>
        (function () {
            var miApp = angular.module('cdgApp');

            function tpCont($scope) {
                $scope.titlePage = "Premio preguntas";
                $scope.user = "El usuario";
            }
           
            miApp.controller('tpCont', tpCont);
        })();
    </script> 
</html>