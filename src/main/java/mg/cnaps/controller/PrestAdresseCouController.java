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

import mg.cnaps.models.PrestAdresseCouMod;
import mg.cnaps.services.PrestAdresseCouService;

@RestController
@CrossOrigin("*")
public class PrestAdresseCouController {
	
	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(PrestAdresseCouController.class);

	@Autowired
	PrestAdresseCouService service;
	
	@PostMapping(path = "/listeprestadcourrier")
	public ResponseEntity<Object> liste(int page) {
		try {
			List<PrestAdresseCouMod> liste = service.getAll(page);
			log.info("nbrligne: "+om.writeValueAsString(liste));
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = "/nbrpageprestadcourrier")
	public ResponseEntity<Object> nombredepage() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = "/ajoutprestadcourrier")
	public void ajout(@RequestBody PrestAdresseCouMod pam) {
		try {
			service.save(pam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping(path = "/supprprestadcourrier")
	public void suppr(@RequestBody PrestAdresseCouMod pam) {
		try {
			service.delete(pam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
