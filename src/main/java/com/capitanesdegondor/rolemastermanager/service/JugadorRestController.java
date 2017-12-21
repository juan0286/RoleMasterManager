package com.capitanesdegondor.rolemastermanager.service;

import com.capitanesdegondor.rolemastermanager.service.beans.CampaignAccessRequestDTO;
import com.capitanesdegondor.rolemastermanager.service.beans.CampaignDTO;
import com.capitanesdegondor.rolemastermanager.service.beans.CampaignListDTO;
import com.capitanesdegondor.rolemastermanager.service.beans.JugadorLogueado;
import com.capitanesdegondor.rolemastermanager.service.beans.PersonajeDTO;
import com.capitanesdegondor.rolemastermanager.service.beans.NuevoUsuarioJugador;
import com.capitanesdegondor.rolemastermanager.validations.HiberRespuesta;
import com.capitanesdegondor.rolemastermanager.validations.Validations;
import com.google.api.client.util.Data;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.websystique.spring.HibernateDao;
import com.websystique.spring.dao.CampaignDao;
import com.websystique.spring.model.bono.BonoExp;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.CampaignAccessRequest;
import com.websystique.spring.model.Jugador;
import com.websystique.spring.model.Master;
import com.websystique.spring.model.objetos.Objeto;
import com.websystique.spring.model.Personaje;
import com.websystique.spring.model.caractPj.Habilidades;
import com.websystique.spring.model.caractPj.Idioma;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.logging.annotations.Param;
import org.jboss.logging.annotations.ValidIdRange;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TiranoJuan
 */
@RestController
public class JugadorRestController {

    private static final Logger LOGGER = Logger.getLogger(JugadorRestController.class.getName());

    @GetMapping(value = "/getCampaign/{search}/{str}", produces = "application/json")
    public ResponseEntity getJugador(
            @PathVariable("search") String search, @PathVariable("str") String str) {

        List<CampaignDTO> lc = new ArrayList<>();
        if (search.equals("masterName")) {
            String master = str;
            Master m = HibernateDao.obtenerMasterPorUsuario(master);
            if (m == null) {
                return new ResponseEntity(lc, HttpStatus.NOT_FOUND);
            }
            Set<Campaign> cs = HibernateDao.todosLasCampaignsXMaster(m);
            for (Campaign c : cs) {
                CampaignDTO cdto = new CampaignDTO(c);
                lc.add(cdto);
            }

        } else if (search.equals("campaignName")) {
            String campaign = str;
            Set<Campaign> cs = HibernateDao.todosLasCampaignsXNombre(campaign);

            for (Campaign c : cs) {
                CampaignDTO cdto = new CampaignDTO(c);
                lc.add(cdto);
            }
        }

        return new ResponseEntity(lc, HttpStatus.OK);
    }


    @RequestMapping(value = "/putCar", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody
    ResponseEntity putCampaignAccessRequest(
            @RequestBody CampaignAccessRequestDTO card) {

        JSONObject json = new JSONObject();
        try {
            CampaignAccessRequest car = new CampaignAccessRequest();
            Date d = new Date();
            car.setFecha(d);
            car.setStatus(CampaignAccessRequest.estado.ESPERA);
            HibernateDao.crearCar(car,card.getId_jugador(), card.getCdto().getId_campaign());

        } catch (Exception ex) {
            json.put("resultado", false);
            return new ResponseEntity(json, HttpStatus.OK);
        }

        json.put("resultado", true);

        return new ResponseEntity(json,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/buscarCampaigns", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity buscarCampaigns(
            @RequestParam("masterName") String master,
            @RequestParam("campaignName") String campaign) {

        List<CampaignDTO> lc = new ArrayList<>();
        if (master.trim().length() > 0) {
            Master m = HibernateDao.obtenerMasterPorUsuario(master);
            if (m == null) {
                return new ResponseEntity(lc, HttpStatus.NOT_FOUND);
            }
            Set<Campaign> cs = HibernateDao.todosLasCampaignsXMaster(m);
            for (Campaign c : cs) {
                CampaignDTO cdto = new CampaignDTO(c);
                lc.add(cdto);
            }

        } else {

            Set<Campaign> cs = HibernateDao.todosLasCampaignsXNombre(campaign);

            for (Campaign c : cs) {
                CampaignDTO cdto = new CampaignDTO(c);
                lc.add(cdto);
            }
        }
        CampaignListDTO r = new CampaignListDTO();
        r.setCampaigns(lc);
        return new ResponseEntity(r, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/campaignsForPlayer", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity campaignsForPlayer(
            @RequestParam("id_jugador") long id_jugador,
            @RequestParam("masterName") String master,
            @RequestParam("campaignName") String campaign) {

        List<CampaignDTO> lc = new ArrayList<>();
        Set<Campaign> cs = null;
        
        if (master.trim().length() > 0) {            
            cs = HibernateDao.todosLasCampaignsNuevasXMaster(master,id_jugador,CampaignDao.TypeSearch.MASTER_NAME);
        } else {
            cs = HibernateDao.todosLasCampaignsNuevasXMaster(campaign,id_jugador,CampaignDao.TypeSearch.CAMPAIGN_NOMBRE);
        }
        if (cs != null){
            for (Campaign c : cs) {
                CampaignDTO cdto = new CampaignDTO(c);
                lc.add(cdto);
            }
        }
        
        CampaignListDTO r = new CampaignListDTO();
        r.setCampaigns(lc);
        return new ResponseEntity(r, HttpStatus.OK);
    }

    @RequestMapping(value = "/loginjugador", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity loguearJugador(@RequestBody NuevoUsuarioJugador nuj) {

        /*
            El servicio recibe un idFirebase, 
            Si el usuario se esta registrando crea un usuario nuevo.            
         */
        //JsonObject json = new JsonObject();
        List<CampaignAccessRequestDTO> cars = new ArrayList();
        List<PersonajeDTO> ps = new ArrayList();
        JugadorLogueado jl = new JugadorLogueado();

        if (nuj != null
                && nuj.getEmail() != null
                && nuj.getId_firebase() != null
                && nuj.getName() != null) {

            Jugador j = HibernateDao.obtenerJugadorPorIdFirebase(nuj.getId_firebase());
            if (j != null) {

                for (CampaignAccessRequest car : j.getCampaigns()) {
                    try{
                    CampaignAccessRequestDTO card = new CampaignAccessRequestDTO(car);
                    cars.add(card);
                    } catch (Exception e){
                        LOGGER.log(Level.WARNING, "Problema casteando car " + car.toString());
                        continue;
                    }
                }

                Personaje p = j.getPj();
                if (p != null) {
                    PersonajeDTO pm = new PersonajeDTO(p);
                    ps.add(pm);
                }

                jl.setIdJugador(j.getId_jugador());

                jl.setCars(cars);
                jl.setPersonajes(ps);

                LOGGER.getLogger(Services.class.getName()).log(Level.FINE, "Loguin de id " + jl.getIdJugador());
                return new ResponseEntity(jl, HttpStatus.OK);

            } else {
                j = new Jugador();
                j.setId_firebase(nuj.getId_firebase());
                j.setNombre(nuj.getName());
                j.setNombre_usuario(nuj.getEmail());
                j.setPerfil(Jugador.PERFIL_JUGADOR);

                HiberRespuesta hr = Validations.crearNuevoJugador(nuj);
                Jugador jn = (Jugador) hr.getResult();
                if (hr.isSuccess()) {
                    jl.setIdJugador(jn.getId_jugador());
                    jl.setCars(cars);
                    jl.setPersonajes(ps);
                    return new ResponseEntity(jl, HttpStatus.OK);

                }
            }
        }
        JsonObject json = new JsonObject();
        json.addProperty("success", false);
        json.addProperty("id", -1);
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @DeleteMapping("/borrarjugador/{id}")
    public ResponseEntity deleteJugador(@PathVariable String id) {

        System.out.println("se supone que borro algo");
        return new ResponseEntity(id, HttpStatus.OK);

    }

    @RequestMapping(value = "/borrarCard", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity borrar(@RequestBody CampaignAccessRequestDTO card) {
            System.out.println("se supone que borro algo");
        try{
            HibernateDao.borrarCampaignAccessRequest(card.getId_car(), card.getId_jugador(), card.getCdto().getId_campaign());
        }catch(Exception e ){
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
        JsonObject j = new JsonObject();
        j.addProperty("Transaccion", "ok");
        return new ResponseEntity(j,HttpStatus.OK);

    }
    
    
    
    @DeleteMapping("/anularCar")
    public ResponseEntity deleteCampaignAccessRequest(
            @RequestBody CampaignAccessRequestDTO card) {

        System.out.println("se supone que borro algo");
        try{
            HibernateDao.borrarCampaignAccessRequest(card.getId_car(), card.getId_jugador(), card.getCdto().getId_campaign());
        }catch(Exception e ){
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
        String hola ="si";
        return new ResponseEntity(hola,HttpStatus.OK);

    }

    @PutMapping("/putactualizarjugador/{id}")
    public ResponseEntity updateJugador(@PathVariable String id, @RequestBody Jugador j) {
        return new ResponseEntity(j, HttpStatus.OK);
    }

    
    //    @PostMapping(value = "/customers")
//    public ResponseEntity createJugador(@RequestBody NuevoUsuarioJugador nuj) {
//
//        Jugador j = new Jugador();
//        j.setId_firebase(nuj.getId_firebase());
//        j.setId_jugador(34);
//        j.setNombre(nuj.getName());
//        j.setNombre_usuario(nuj.getEmail());
//        j.setPerfil(2);
//
//        return new ResponseEntity(j, HttpStatus.OK);
//    }
//    
// @RequestMapping(value = "/postnuevoJugador", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody
//    ResponseEntity createJugador( @RequestBody NuevoUsuarioJugador nuj ) {
////        Jugador j = new Jugador();
////        j.setId_firebase(nuj.getId_firebase());
////        j.setId_jugador(34);
////        j.setNombre(nuj.getName());
////        j.setNombre_usuario(nuj.getEmail());
////        j.setPerfil(2);
//
//        Personaje p = HibernateDao.obtenerPersonajePorId(5L);
//        Habilidades h = p.getHabilidades();
//        //Campaign o = HibernateDao.obtenerCampaignPorId(2L);
//        return new ResponseEntity(h, HttpStatus.OK);
//    }
//    @PutMapping(value= "/newuserjugador", produces = "application/json")
//    public ResponseEntity updateJugador(@RequestBody NuevoUsuarioJugador nuj) {
//        Object obj = null;
//        if (nuj != null 
//                && nuj.getEmail() != null
//                && nuj.getId_firebase() != null
//                && nuj.getName() != null
//                ){
//            Jugador j = new Jugador();
//            j.setId_firebase(nuj.getId_firebase());
//            j.setNombre(nuj.getName());
//            j.setNombre_usuario(nuj.getEmail());
//            j.setPerfil(Jugador.PERFIL_JUGADOR);
//            
//            HiberRespuesta hr = Validations.crearNuevoJugador(nuj);
//            if (hr.isSuccess()){                
//                obj = new Object[]{true,j.getId_jugador()};
//            }
//            HibernateDao.crearJugador(j);
//        } else {
//            obj = new Object[]{false,0};                        
//        }
//            
//        
//        return new ResponseEntity(obj, HttpStatus.OK);
//    }
    
    
//	@Autowired
//	private JugadorDAO customerDAO;
//    @GetMapping("/customers")
//    public List getJugadors() {
////		List<String> l = new ArrayList<String>();
////                l.add("resp2");
////                List messages = Arrays.asList("Hello", "World!", "How", "Are", "You");
//        Set<Jugador> set = HibernateDao.todosLos
//        List<Idioma> list = new ArrayList<Idioma>(set);
//        return list;
//    }
    // GET LISTA DE OBJETOS
//    @RequestMapping(value = "/idiomas", method = RequestMethod.GET, produces = "application/json")
//    public @ResponseBody
//    List<Idioma> listAll_Idiomas() {
//        Set<Idioma> set = HibernateDao.todosLosIdiomas();
//        List<Idioma> list = new ArrayList<Idioma>(set);
//        System.out.println("users.size(): " + list.size());        
//        return list;
//    }
}
