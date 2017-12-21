<%-- 
    Document   : login
    Created on : 13-sep-2017, 12:38:40
    Author     : TiranoJuan
--%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%
    session.setAttribute("usuarioLogueado", null);
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Role Master Manager - Login</title>        
        <script>
            $(document).ready(function () {
                $('.message a').click(function () {
                    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
                });
                $("button").click(function () {
                    $("#allpage").show();
                });
            });

        </script>                      
        <link href="<c:url value="/css/login.css" />" rel="stylesheet">
        <link href="<c:url value="/css/main.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="login-page">
            <div class="form" >
                <form class="register-form" action="registrar" method="POST">
                    <span>Nombre</span>
                    <input type="text" placeholder="nombre" name="nombre"/>
                    <span>Usuario</span>
                    <input type="text" placeholder="username" name="username"/>
                    <span>Contraseña</span>
                    <input type="password" name="password" />
                    <span>Reingrese Contraseña</span>
                    <input type="password" name="passwordDos"/>
                    <span>Email</span>
                    <span class="error">${errorDetail}</span>
                    <input type="text" name="email"/>
                    <button>Crear</button>
                    <p class="message">Ya estas registrado? <a href="#">Logueate</a></p>
                </form>
                <form class="login-form" action="ingresar.htm" method="POST">
                    <span>Usuario</span>
                    <input type="text" name="username"/>
                    <span>Contraseña</span>
                    <input type="password" name="password"/>
                    <span class="error">${errorDetail}</span>
                    <button>loguear</button>
                    <p class="message">no estas registrado? <a href="#">Crea una cuenta</a></p>
                </form>
                
            </div>
        </div>
        <div id="allpage">
            <img src="<c:url value="/img/loading.gif" />" class="imgcenter" width="80" height="80"  />
        </div>
    </body>
</html>
