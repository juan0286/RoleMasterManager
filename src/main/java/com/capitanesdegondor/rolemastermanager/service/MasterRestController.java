package com.capitanesdegondor.rolemastermanager.service;

import com.capitanesdegondor.rolemastermanager.modelos.BonoExpForm;
import com.capitanesdegondor.rolemastermanager.service.beans.BonoExpDTO;
import com.capitanesdegondor.rolemastermanager.service.beans.SolicitudForCampaignDTO;
import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.CampaignAccessRequest;
import com.websystique.spring.model.bono.BonoExp;

import com.websystique.spring.model.Personaje;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TiranoJuan
 */
@RestController
public class MasterRestController {
    
    private static final Logger LOGGER = Logger.getLogger(MasterRestController.class.getName());
    
    @RequestMapping(value = "/getAllBonosExp/{idPj}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllBonosExp(@PathVariable("idPj") Long idPj) {
        
        List<BonoExp> bes = new ArrayList<>();
        
        JSONArray ja = new JSONArray();
        BonoExpDTO[] bedtos = null;
        Personaje pj = HibernateDao.obtenerPersonajePorId(idPj);
        
        if (pj == null) {
            
            return new ResponseEntity(bes, HttpStatus.NOT_FOUND);
            
        } else {
            int a = 0;
            
            Set<BonoExp> cs = HibernateDao.todosLosBonosExpDeUnPj(pj);
            bedtos = new BonoExpDTO[cs.size()];
            for (BonoExp be : cs) {
                bes.add(be);
                BonoExpDTO bed = new BonoExpDTO(be);
                ja.put(bed);
                bedtos[a] = bed;
                a++;
            }            
        }
        
        return new ResponseEntity(bedtos, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getSolicitudes/{idPj}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllSolicitudesFrom(@PathVariable("id_c") Long idC) {
        
        
        SolicitudForCampaignDTO[] lista = {};
        Campaign c = HibernateDao.obtenerCampaignPorId(idC);
        
        if (c == null) {
            
            return new ResponseEntity(lista, HttpStatus.NOT_FOUND);
            
        } else {
            int a = 0;
            
            Set<CampaignAccessRequest> cars = HibernateDao.todosLosCarDeUnaCampaign(c);
            lista = new SolicitudForCampaignDTO[cars.size()];
            for (CampaignAccessRequest be : cars) {
                
                SolicitudForCampaignDTO sfd = new SolicitudForCampaignDTO(be);                
                lista[a] = sfd;
                a++;
            }            
        }
        
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/addBonoExp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity putBERequest(
            @RequestBody BonoExpForm bef) {
        
        BonoExp be = new BonoExp();
        JSONObject json = new JSONObject();
        
        if (bef != null) {
            try {
                Date d = new Date();
                be.setFecha(d);
                
                be.setMotivo(bef.getMotivo());
                Personaje p = HibernateDao.obtenerPersonajePorId(bef.getId_pj());
                be.setPj(p);
                be.setBono(bef.getBono());
                be.setAplicado(false);
                
                HibernateDao.crearBonoExp(be);
                
            } catch (Exception ex) {
                
                json.put("resultado", false);
                return new ResponseEntity(json, HttpStatus.FAILED_DEPENDENCY);
                
            }
        } else {
            json.put("resultado", false);
            return new ResponseEntity(json, HttpStatus.FAILED_DEPENDENCY);
        }
        json.put("" + be.getId_bonoexp(), true);
        return new ResponseEntity(json, HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/buscarCampaigns", method = RequestMethod.GET, produces = "application/json")
//    public @ResponseBody
//    ResponseEntity buscarCampaigns(
//            @RequestParam("masterName") String master,
//            @RequestParam("campaignName") String campaign) {
//
//        List<CampaignDTO> lc = new ArrayList<>();
//        if (master.trim().length() > 0) {
//            Master m = HibernateDao.obtenerMasterPorUsuario(master);
//            if (m == null) {
//                return new ResponseEntity(lc, HttpStatus.NOT_FOUND);
//            }
//            Set<Campaign> cs = HibernateDao.todosLasCampaignsXMaster(m);
//            for (Campaign c : cs) {
//                CampaignDTO cdto = new CampaignDTO(c);
//                lc.add(cdto);
//            }
//
//        } else {
//
//            Set<Campaign> cs = HibernateDao.todosLasCampaignsXNombre(campaign);
//
//            for (Campaign c : cs) {
//                CampaignDTO cdto = new CampaignDTO(c);
//                lc.add(cdto);
//            }
//        }
//        CampaignListDTO r = new CampaignListDTO();
//        r.setCampaigns(lc);
//        return new ResponseEntity(r, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/campaignsForPlayer", method = RequestMethod.GET, produces = "application/json")
//    public @ResponseBody
//    ResponseEntity campaignsForPlayer(
//            @RequestParam("id_jugador") long id_jugador,
//            @RequestParam("masterName") String master,
//            @RequestParam("campaignName") String campaign) {
//
//        List<CampaignDTO> lc = new ArrayList<>();
//        Set<Campaign> cs = null;
//
//        if (master.trim().length() > 0) {
//            cs = HibernateDao.todosLasCampaignsNuevasXMaster(master, id_jugador, CampaignDao.TypeSearch.MASTER_NAME);
//        } else {
//            cs = HibernateDao.todosLasCampaignsNuevasXMaster(campaign, id_jugador, CampaignDao.TypeSearch.CAMPAIGN_NOMBRE);
//        }
//        if (cs != null) {
//            for (Campaign c : cs) {
//                CampaignDTO cdto = new CampaignDTO(c);
//                lc.add(cdto);
//            }
//        }
//
//        CampaignListDTO r = new CampaignListDTO();
//        r.setCampaigns(lc);
//        return new ResponseEntity(r, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/loginjugador", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody
//    ResponseEntity loguearJugador(@RequestBody NuevoUsuarioJugador nuj) {
//
//        /*
//            El servicio recibe un idFirebase, 
//            Si el usuario se esta registrando crea un usuario nuevo.            
//         */
//        //JsonObject json = new JsonObject();
//        List<CampaignAccessRequestDTO> cars = new ArrayList();
//        List<PersonajeDTO> ps = new ArrayList();
//        JugadorLogueado jl = new JugadorLogueado();
//
//        if (nuj != null
//                && nuj.getEmail() != null
//                && nuj.getId_firebase() != null
//                && nuj.getName() != null) {
//
//            Jugador j = HibernateDao.obtenerJugadorPorIdFirebase(nuj.getId_firebase());
//            if (j != null) {
//
//                for (CampaignAccessRequest car : j.getCampaigns()) {
//                    try {
//                        CampaignAccessRequestDTO card = new CampaignAccessRequestDTO(car);
//                        cars.add(card);
//                    } catch (Exception e) {
//                        LOGGER.log(Level.WARNING, "Problema casteando car " + car.toString());
//                        continue;
//                    }
//                }
//
//                Personaje p = j.getPj();
//                if (p != null) {
//                    PersonajeDTO pm = new PersonajeDTO(p);
//                    ps.add(pm);
//                }
//
//                jl.setIdJugador(j.getId_jugador());
//
//                jl.setCars(cars);
//                jl.setPersonajes(ps);
//
//                LOGGER.getLogger(Services.class.getName()).log(Level.FINE, "Loguin de id " + jl.getIdJugador());
//                return new ResponseEntity(jl, HttpStatus.OK);
//
//            } else {
//                j = new Jugador();
//                j.setId_firebase(nuj.getId_firebase());
//                j.setNombre(nuj.getName());
//                j.setNombre_usuario(nuj.getEmail());
//                j.setPerfil(Jugador.PERFIL_JUGADOR);
//
//                HiberRespuesta hr = Validations.crearNuevoJugador(nuj);
//                Jugador jn = (Jugador) hr.getResult();
//                if (hr.isSuccess()) {
//                    jl.setIdJugador(jn.getId_jugador());
//                    jl.setCars(cars);
//                    jl.setPersonajes(ps);
//                    return new ResponseEntity(jl, HttpStatus.OK);
//
//                }
//            }
//        }
//        JsonObject json = new JsonObject();
//        json.addProperty("success", false);
//        json.addProperty("id", -1);
//        return new ResponseEntity(json, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/borrarjugador/{id}")
//    public ResponseEntity deleteJugador(@PathVariable String id) {
//
//        System.out.println("se supone que borro algo");
//        return new ResponseEntity(id, HttpStatus.OK);
//
//    }
//
//    @RequestMapping(value = "/borrarCard", method = RequestMethod.POST, produces = "application/json")
//    public ResponseEntity borrar(@RequestBody CampaignAccessRequestDTO card) {
//        System.out.println("se supone que borro algo");
//        try {
//            HibernateDao.borrarCampaignAccessRequest(card.getId_car(), card.getId_jugador(), card.getCdto().getId_campaign());
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
//        }
//        JsonObject j = new JsonObject();
//        j.addProperty("Transaccion", "ok");
//        return new ResponseEntity(j, HttpStatus.OK);
//
//    }
//
//    @DeleteMapping("/anularCar")
//    public ResponseEntity deleteCampaignAccessRequest(
//            @RequestBody CampaignAccessRequestDTO card) {
//
//        System.out.println("se supone que borro algo");
//        try {
//            HibernateDao.borrarCampaignAccessRequest(card.getId_car(), card.getId_jugador(), card.getCdto().getId_campaign());
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
//        }
//        String hola = "si";
//        return new ResponseEntity(hola, HttpStatus.OK);
//
//    }
//
//    @PutMapping("/putactualizarjugador/{id}")
//    public ResponseEntity updateJugador(@PathVariable String id, @RequestBody Jugador j) {
//        return new ResponseEntity(j, HttpStatus.OK);
//    }
}
