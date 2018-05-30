<%-- 
    Document   : header
    Created on : 29-sep-2017, 22:40:57
    Author     : TiranoJuan
--%>


<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header" ng-controller="tpCont">
    <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
        <div class="mdl-layout__header-row" >
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colore">
                <a  href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}">${campaActual.getNombreURL()}</a>
            </button>

            <span class="mdl-layout-title">{{titlePage}}</span>
            <div class="mdl-layout-spacer"></div> 
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
                <label class="mdl-button mdl-js-button mdl-button--icon" for="search">
                    <i class="material-icons">search</i>
                </label>
                <div class="mdl-textfield__expandable-holder">
                    <input class="mdl-textfield__input" type="text" id="search">
                    <label class="mdl-textfield__label" for="search">Enter your query...</label>
                </div>
            </div>
            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
                <i class="material-icons">more_vert</i>
            </button>
            <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
                <li class="mdl-menu__item">About</li>
                <li class="mdl-menu__item">Contact</li>
                <li class="mdl-menu__item">Legal information</li>
            </ul>
        </div>
    </header>
    <div class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
        <header class="demo-drawer-header">
            <img width="150" height="150" src="<c:url value='/img/logo_stnd.jpg' />" class="demo-avatar">
            <div class="demo-avatar-dropdown">
                <span>{{ user}}</span>
                <div class="mdl-layout-spacer"></div>
                <button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                    <i class="material-icons" role="presentation">arrow_drop_down</i>
                    <span class="visuallyhidden">Accounts</span>
                </button>
                <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
                    <li class="mdl-menu__item">${usuarioLogueado.usuario}</li>              
                    <li class="mdl-menu__item"><i class="material-icons">add</i>Add another account...</li>
                </ul>
            </div>
        </header>
        <nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
            <a class="mdl-navigation__link" href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}/partidas">Partidas</a>
            <a class="mdl-navigation__link" href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}/mundo">Mundo</a>
            <a class="mdl-navigation__link" href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}/jugadores">Jugadores</a>
            <a class="mdl-navigation__link" href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}//Solicitudes">Solicitudes</a>          
            <a class="mdl-navigation__link" href="/RoleMasterManager/m/${usuarioLogueado.usuario}/${campaActual.getNombreURL()}/nuevoPremioPregunta">Premio Preguntas</a>          
            <div class="mdl-layout-spacer"></div>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>
        </nav>
    </div>
