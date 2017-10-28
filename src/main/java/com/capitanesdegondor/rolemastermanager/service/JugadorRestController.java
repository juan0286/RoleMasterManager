package com.capitanesdegondor.rolemastermanager.service;

import com.capitanesdegondor.rolemastermanager.service.beans.NuevoUsuarioJugador;
import com.capitanesdegondor.rolemastermanager.validations.HiberRespuesta;
import com.capitanesdegondor.rolemastermanager.validations.Validations;
import com.google.api.client.util.Data;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.websystique.spring.HibernateDao;
import com.websystique.spring.model.BonoExp;
import com.websystique.spring.model.Campaign;
import com.websystique.spring.model.Jugador;
import com.websystique.spring.model.Objeto;
import com.websystique.spring.model.Personaje;
import com.websystique.spring.model.caractPj.Habilidades;
import com.websystique.spring.model.caractPj.Idioma;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.jboss.logging.annotations.ValidIdRange;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TiranoJuan
 */
@RestController
public class JugadorRestController {

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
    @RequestMapping(value = "/idiomas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Idioma> listAll_Idiomas() {
        Set<Idioma> set = HibernateDao.todosLosIdiomas();
        List<Idioma> list = new ArrayList<Idioma>(set);
        System.out.println("users.size(): " + list.size());

        return list;
    }

    @GetMapping(value = "/getjugador/{id}", produces = "application/json")
    public ResponseEntity getJugador(@PathVariable("id") String id) {

        Jugador j = new Jugador();
        j.setId_firebase("asadad");

        j.setId_jugador(Long.parseLong(id));
        j.setNombre("don pepe");
        j.setNombre_usuario("donPepe");
        j.setPerfil(1);

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
    @RequestMapping(value = "/newuserjugador", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity createJugador(@RequestBody NuevoUsuarioJugador nuj) {

        JsonObject json = new JsonObject();

        if (nuj != null
                && nuj.getEmail() != null
                && nuj.getId_firebase() != null
                && nuj.getName() != null) {
            Jugador j = new Jugador();
            j.setId_firebase(nuj.getId_firebase());
            j.setNombre(nuj.getName());
            j.setNombre_usuario(nuj.getEmail());
            j.setPerfil(Jugador.PERFIL_JUGADOR);

            HiberRespuesta hr = Validations.crearNuevoJugador(nuj);
            Jugador jn = (Jugador) hr.getResult();
            if (hr.isSuccess()) {
                json.addProperty("success", true);
                json.addProperty("id", jn.getId_jugador());
            } else {
                json.addProperty("success", false);
                json.addProperty("id", -1);
            }

        } else {
            json.addProperty("success", false);
            json.addProperty("id", -1);
        }

        return new ResponseEntity(json, HttpStatus.OK);
    }

    @DeleteMapping("/borrarjugador/{id}")
    public ResponseEntity deleteJugador(@PathVariable String id) {

        System.out.println("se supone que borro algo");
        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/putactualizarjugador/{id}")
    public ResponseEntity updateJugador(@PathVariable String id, @RequestBody Jugador j) {
        return new ResponseEntity(j, HttpStatus.OK);
    }

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
}
