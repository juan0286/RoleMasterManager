<%-- 
    Document   : header
    Created on : 29-sep-2017, 22:40:57
    Author     : TiranoJuan
--%>
<script >
    $(document).ready(function () {
        $('#themeselect').find('a').click(function () {
            var value = $(this).attr('data-color');
            $('.navbar').removeClass().addClass('navbar ' + value);

            return false;
        });
    });
</script>  
<link href="<c:url value="/css/menuHeader.css" />" rel="stylesheet">
      <div class="header">
    <div id="logger">${usuarioLogueado.nombre}<a href="/desloguear">[Desloguear]</a></div>        
    <div id="titulo">
        <h2 >ROL WEB MANAGER</h2>

        <ul class="navbar color1">
            <li><a href="#"><i class="icon20 home"></i><span>Clima</span></a></li>
            <li><a href="#"><i class="icon20 upload"></i><span>Lugares</span></a></li>
            <li><a href="#"><i class="icon20 download"></i><span>PNJ</span></a></li>

            <c:choose>
                <c:when test="${campaActual != null}">   
                    <li class="drpdown"><a href="#"><i class="icon20 comments"></i><span>Campaña</span></a>
                        <ul class="drpcontent">
                            <li><a href="${campaActual.getNombreURL()}/crear">Crear</a></li>
                            <li><a href="${campaActual.getNombreURL()}/nuevoPremioPregunta">Premio Pregunta</a></li>
                            <li><a href="#">Armaduras</a></li>
                            <li><a href="#">Consumibles</a></li>
                            <li><a href="#">Mochilas</a></li>
                        </ul>
                    </li>
                </c:when> 
            </c:choose>

            <li><a href="#"><i class="icon20 login"></i><span>Login</span></a></li>
            <li class="drpdown"><a href="#"><i class="icon20 theme"></i><span>Tema</span></a>
                <ul class="drpcontent" id="themeselect">
                    <li><a href="#" data-color="color1">Naranja</a></li>
                    <li><a href="#" data-color="color2">Marino</a></li>
                    <li><a href="#" data-color="color3">Verde</a></li>
                    <li><a href="#" data-color="color4">Purpura</a></li>
                    <li><a href="#" data-color="color5">Rojo</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
