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

        <h3>Campaña: ${campaActual.getNombre()}</h3>


        <c:choose>
            <c:when test="${campaActual.getJugadores().size() != 0}">
                <div id="jugadores">
                    <h2> Jugadores </h2>
                    <c:forEach items="${campaActual.getJugadores()}" var="jugador" >
                        <div class="jugadorItem">                       
                            <c:set var = "pj" value = "${jugador.getPj()}"/>

                            <div class="pjitem">
                                <table class="pjtable" border="0"  width="100%">
                                    <tr>
                                        <td colspan="8"><span>${pj.getNombre()}</span></td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><span>PV</span></td>
                                        <td colspan="6"><span>${pj.getHabilidades().getDesarrolloFisico()}</span></td>
                                        <td colspan="2" ><a href="bonoExp.do?pj=${pj.getId_pj()}">Dar Exp</a></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><span>PP</span></td>
                                        <td colspan="6"><span>${pj.getHabilidades().getPuntosPoder()}</span></td>
                                        <td colspan="2"><a href="enviarMje.htm?pj=${pj.getId_pj()}">Enviar Mje</a></td>
                                    </tr>
                                    <tr>
                                        <td class="hoveroption" name="desc${pj.getId_pj()}">Descripcion</td>
                                        <td class="hoveroption" name="carct${pj.getId_pj()}">Caracterisiticas</td>                                        
                                        <td class="hoveroption" name="armaduras${pj.getId_pj()}">Hab Armadura</td>
                                        <td class="hoveroption" name="armas${pj.getId_pj()}">Hab Armas</td>
                                        <td class="hoveroption" name="generales${pj.getId_pj()}">Hab Generales</td>                                    
                                        <td class="hoveroption" name="subte${pj.getId_pj()}">Hab Subterfugio</td>
                                        <td class="hoveroption" name="magicas${pj.getId_pj()}">Hab Magicas</td>
                                        <td class="hoveroption" name="secundarias${pj.getId_pj()}">Hab Secundarias</td>
                                        <td class="hoveroption" name="estaticas${pj.getId_pj()}">Hab Estaticas</td>   
                                        <td class="hoveroption" name="resistencias${pj.getId_pj()}">Resistencias</td>                                                                             
                                    </tr>                                    
                                    <tr >                                        
                                        <td colspan="10">
                                            <div id="tabledesc${pj.getId_pj()}" style="display: none; width: 50%;">
                                                <div style="display: block; float: left;">
                                                    <table class="pjdetail" style="display: block;">
                                                        <tr><td>Exp.</td><td>${pj.getExp()}</td></tr>
                                                        <tr><td>nivel</td><td>${pj.getNivel()}</td></tr>
                                                        <tr><td>raza</td><td>${pj.getRaza()}</td></tr>
                                                        <tr><td>altura</td><td>${pj.getAltura()}</td></tr>
                                                        <tr><td>peso</td><td>${pj.getPeso()}</td></tr>
                                                        <tr><td>genero</td><td>${pj.getGenero()}</td></tr>
                                                        <tr><td>edad</td><td>${pj.getEdad()}</td></tr>

                                                    </table>
                                                </div>
                                                <div style="display: block; width: 50%;">
                                                    <table class="pjdetail">    
                                                        <tr><td>motivacion</td><td>${pj.getMotivacion()}</td></tr>
                                                        <tr><td>alineamiento</td><td>${pj.getAlineamiento()}</td></tr>
                                                        <tr><td>estado_civico</td><td>${pj.getEstado_civico()}</td></tr>
                                                        <tr><td>familia</td><td>${pj.getFamilia()}</td></tr>
                                                        <tr><td>diosesAdorados</td><td>${pj.getDiosesAdorados()}</td></tr>
                                                        <tr><td>religion</td><td>${pj.getReligion()}</td></tr>                                                        
                                                    </table>
                                                </div>
                                            </div>
                                            <div id="tablecarct${pj.getId_pj()}" style="display: none; width: 30%;">
                                                <div style="display: block; float: left;">

                                                    <table class="pjdetail"  border="1"  style="display: block;">
                                                        <tr><td>FUE</td><td>${pj.getCaracteristicas().getFue()}</td></tr>
                                                        <tr><td>AGI</td><td>${pj.getCaracteristicas().getAgi()}</td></tr>
                                                        <tr><td>CON</td><td>${pj.getCaracteristicas().getCon()}</td></tr>
                                                        <tr><td>INTE</td><td>${pj.getCaracteristicas().getInte()}</td></tr>
                                                        <tr><td>I</td><td>${pj.getCaracteristicas().getI()}</td></tr>
                                                        <tr><td>PRE</td><td>${pj.getCaracteristicas().getPre()}</td></tr>
                                                        <tr><td>APA</td><td>${pj.getCaracteristicas().getApa()}</td></tr>

                                                    </table>
                                                </div>                                                
                                            </div>
                                            <div id="tablearmaduras${pj.getId_pj()}" style="display: none; width: 30%;">
                                                <div style="display: block; float: left;">
                                                    <table class="pjdetail"  border="1"  style="display: block;">
                                                        <tr><td>SA</td><td>${pj.getHabilidades().getHabArmadura().getSa()}</td></tr>
                                                        <tr><td>C</td><td>${pj.getHabilidades().getHabArmadura().getC()}</td></tr>
                                                        <tr><td>CE</td><td>${pj.getHabilidades().getHabArmadura().getCe()}</td></tr>
                                                        <tr><td>CM</td><td>${pj.getHabilidades().getHabArmadura().getCm()}</td></tr>
                                                        <tr><td>CO</td><td>${pj.getHabilidades().getHabArmadura().getCo()}</td></tr>
                                                    </table>
                                                </div>                                                
                                            </div>
                                            <div id="tablearmas${pj.getId_pj()}" style="display: none; width: 30%;">
                                                <div style="display: block; float: left;">
                                                    <table class="pjdetail"  border="1"  style="display: block;">
                                                        <tr><td>Filo</td><td>${pj.getHabilidades().getHabArmas().getFilo()}</td></tr>                                                       
                                                        <tr><td>Contundentes</td><td>${pj.getHabilidades().getHabArmas().getContundentes()}</td></tr>                                                       
                                                        <tr><td>Dos Manos</td><td>${pj.getHabilidades().getHabArmas().getDosManos()}</td></tr>                                                       
                                                        <tr><td>Arrojadizas</td><td>${pj.getHabilidades().getHabArmas().getArrojadizas()}</td></tr>                                                       
                                                        <tr><td>Proyectiles</td><td>${pj.getHabilidades().getHabArmas().getProyectiles()}</td></tr>                                                       
                                                        <tr><td>Asta</td><td>${pj.getHabilidades().getHabArmas().getAsta()}</td></tr>                                                       
                                                        <tr><td>Pelea</td><td>${pj.getHabilidades().getHabArmas().getPelea()}</td></tr>
                                                    </table>
                                                    
                                                </div>
                                            </div>
                                            <div id="tablegenerales${pj.getId_pj()}" style="display: none; width: 30%;">
                                                <div style="display: block; float: left;">

                                                    <table class="pjdetail"  border="1"  style="display: block;">
                                                        <tr><td>Montar</td><td>${pj.getHabilidades().getHabGenerales().getMontar()}</td></tr>
                                                        <tr><td>Trepar</td><td>${pj.getHabilidades().getHabGenerales().getTrepar()}</td></tr>
                                                        <tr><td>Nadar</td><td>${pj.getHabilidades().getHabGenerales().getNadar()}</td></tr>
                                                        <tr><td>Rastrear</td><td>${pj.getHabilidades().getHabGenerales().getRastrear()}</td></tr>                                                        
                                                    </table>
                                                </div>
                                            </div>
                                            <div id="tablesubte${pj.getId_pj()}" style="display: none; width: 30%;">
                                                <div style="display: block; float: left;">
                                                    <table class="pjdetail"  border="1"  style="display: block;">
                                                        <tr><td>Emboscar</td><td>${pj.getHabilidades().getHabSubterfugio().getEmboscar()}</td></tr>
                                                        <tr><td>Acechar Esconderse</td><td>${pj.getHabilidades().getHabSubterfugio().getAcecharEsconderse()}</td></tr>
                                                        <tr><td>Abrir Cerraduras</td><td>${pj.getHabilidades().getHabSubterfugio().getAbrirCerraduras()}</td></tr>
                                                        <tr><td>Desactivar Trampas</td><td>${pj.getHabilidades().getHabSubterfugio().getDesactivarTrampas()}</td></tr>
                                                    </table>                                                    
                                                </div>
                                            </div>
                                            <div id="tablemagicas${pj.getId_pj()}" style="display: none; width: 30%;">
                                                <div style="display: block; float: left;">
                                                    <table class="pjdetail"  border="1"  style="display: block;">
                                                        <tr><td>Usar Objetos</td><td>${pj.getHabilidades().getHabMagicas().getUsarObjetos()}</td></tr>
                                                        <tr><td>Leer Runas</td><td>${pj.getHabilidades().getHabMagicas().getLeerRunas()}</td></tr>
                                                        <tr><td>Sortilegios Dirigidos</td><td>${pj.getHabilidades().getHabMagicas().getSortilegiosDirgidos()}</td></tr>
                                                        <tr><td>Sortilegio Base</td><td>${pj.getHabilidades().getHabMagicas().getSortilegiosBase()}</td></tr>
                                                        
                                                    </table>                                                    
                                                </div>
                                            </div>
                                            <div id="tablesecundarias${pj.getId_pj()}" style="display: none; width: 30%;">
                                                <div style="display: block; float: left;">
                                                    <table class="pjdetail"  border="1"  style="display: block;">
                                                        <c:forEach items="${pj.getHabilidades().getHabSecundarias()}" var="habSec" >
                                                            <tr>
                                                                <td>${habSec.getHab_secundaria().getNombre()}</td>
                                                                <td>${habSec.getValor()}</td>
                                                            </tr>
                                                        </c:forEach>                                                        
                                                    </table>
                                                </div>
                                            </div>
                                            <div id="tableestaticas${pj.getId_pj()}" style="display: none; width: 30%;">
                                                <div style="display: block; float: left;">
                                                    <table class="pjdetail"  border="1"  style="display: block;">
                                                        <tr><td>Desarrollo Fisico</td><td>${pj.getHabilidades().getDesarrolloFisico()}</td></tr>
                                                        <tr><td>Percepcion</td><td>${pj.getHabilidades().getPercepcion()}</td></tr>
                                                        <tr><td>Liderazgo e influencia</td><td>${pj.getHabilidades().getLiderazgoInfluencia()}</td></tr>
                                                        <tr><td>Percepcion</td><td>${pj.getHabilidades().getPercepcion()}</td></tr>
                                                        <tr><td>BD</td><td>${pj.getHabilidades().getBonificacionDefensiva()}</td></tr>
                                                    </table> 
                                                </div>
                                            </div>
                                            <div id="tableresistencias${pj.getId_pj()}" style="display: none; width: 30%;">
                                                <div style="display: block; float: left;">
                                                    <table class="pjdetail"  border="1"  style="display: block;">                                                        
                                                        <tr><td>Calor</td><td>${pj.getHabilidades().getResistencias().getCalor()}</td></tr>
                                                        <tr><td>Frio</td><td>${pj.getHabilidades().getResistencias().getFrio()}</td></tr>
                                                        <tr><td>Canalizacion</td><td>${pj.getHabilidades().getResistencias().getCanalizacion()}</td></tr>
                                                        <tr><td>Escencia</td><td>${pj.getHabilidades().getResistencias().getEscencia()}</td></tr>
                                                        <tr><td>Mentlismo</td><td>${pj.getHabilidades().getResistencias().getMentalismo()}</td></tr>
                                                        <tr><td>Venenos</td><td>${pj.getHabilidades().getResistencias().getVenenos()}</td></tr>
                                                        <tr><td>Enfermedad</td><td>${pj.getHabilidades().getResistencias().getEnfermedades()}</td></tr>
                                                    </table>
                                                </div>
                                            </div>                                            
                                        </td>
                                    </tr>
                                </table>                                                    
                            </div>                            
                        </div>
                        <br />
                    </c:forEach>
                </div>    
                <div id="mundo">
                    <span>${campaActual.getMundo()}</span>
                </div>    
                <br />
            </c:when>                    
        </c:choose>
        <div class="campaignItem">
            <a href="invitarJugador.htm"> Invitar Jugador</a>
        </div>
        <%@ include file="/WEB-INF/jsp/views/footer.jsp" %>

    </body>
</html>
