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

import mg.cnaps.models.PrestataireCouMod;
import mg.cnaps.services.PrestataireCouService;

@RestController
@CrossOrigin("*")
public class PrestataireCouController {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(PrestataireCouController.class);

	@Autowired
	PrestataireCouService service;

	@PostMapping(path = "/listeprestatairecourrier")
	public ResponseEntity<Object> liste(int page) {
		try {
			List<PrestataireCouMod> liste = service.getAll(page);
			log.info("nbrligne: " + om.writeValueAsString(liste));
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/nbrpageprestatairecourrier")
	public ResponseEntity<Object> nombrePages() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/ajoutprestatairecourrier")
	public void ajout(@RequestBody PrestataireCouMod pcm) {
		try {
			service.save(pcm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping(path = "/supprprestatairecourrier")
	public void suppr(@RequestBody PrestataireCouMod pcm) {
		try {
			service.delete(pcm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
