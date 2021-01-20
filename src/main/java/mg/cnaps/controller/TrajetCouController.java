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

import mg.cnaps.models.TrajetCouMod;
import mg.cnaps.services.TrajetCouService;

@RestController
@CrossOrigin("*")
public class TrajetCouController {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(TrajetCouController.class);

	@Autowired
	TrajetCouService service;

	@PostMapping(path = "/listetrajetcourrier")
	public ResponseEntity<Object> liste() {
		try {
			List<TrajetCouMod> liste = service.findalltrajetcou();
			log.info("nbrligne: " + om.writeValueAsString(liste));
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/nbrpagetrajetcourrier")
	public ResponseEntity<Object> nombrePages() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/ajouttrajetcourrier")
	public void save(@RequestBody TrajetCouMod tcm) {
		try {
			service.save(tcm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping(path = "/supprtrajetcourrier")
	public void delete(@RequestBody TrajetCouMod tcm) {
		try {
			service.delete(tcm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(path = "/findbyrechtrajet")
	public ResponseEntity<Object> findById(String libelletrajetcou) {
		try {
			List<TrajetCouMod> acces = service.getByLibelleTrajetCou(libelletrajetcou);
			resultat = om.writeValueAsString(acces);
			log.info("nbrligne: " + resultat);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
