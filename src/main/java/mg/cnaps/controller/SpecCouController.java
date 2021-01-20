package mg.cnaps.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.SpecCouMod;
import mg.cnaps.services.SpecCouService;

@RestController
@CrossOrigin("*")
public class SpecCouController {
	
	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(SpecCouController.class);

	@Autowired
	SpecCouService service;
	
	@PostMapping(path = "/listespeccourrier")
	public ResponseEntity<Object> getListe(int page){
		try {
			List<SpecCouMod> liste = service.getAll(page);
			log.info("renvoie: "+om.writeValueAsString(liste));
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = "/nbrpagespeccourrier")
	public ResponseEntity<Object> nombrepages() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = "/ajoutspeccourrier")
	public void ajout(@RequestBody SpecCouMod scm) {
		try {
			service.save(scm);
		} catch (Exception e) {
			e.printStackTrace();
	    }
		
	}
	
	@DeleteMapping(path = "/supprspeccourrier")
	public void suppr(@RequestBody SpecCouMod spec) {
		try {
			service.delete(spec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
