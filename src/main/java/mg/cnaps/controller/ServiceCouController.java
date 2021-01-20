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

import mg.cnaps.models.ServiceCouMod;
import mg.cnaps.services.ServiceCouService;

@RestController
@CrossOrigin("*")
public class ServiceCouController {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(ServiceCouController.class);

	@Autowired
	ServiceCouService servicec;

	@PostMapping(path = "/listeservicecourrier")
	public ResponseEntity<Object> liste() {
		try {
			List<ServiceCouMod> liste = servicec.getAll(1);
			log.info("nbrligne: " + om.writeValueAsString(liste));
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/nbrpageservicecourrier")
	public ResponseEntity<Object> nombrePages() {
		try {
			int page = servicec.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/ajoutservicecourrier")
	public void ajout(@RequestBody ServiceCouMod scm) {
		try {
			servicec.save(scm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping(path = "/supprservicecourrier")
	public void suppr(@RequestBody ServiceCouMod scm) {
		try {
			servicec.delete(scm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(path = "/findbyrech")
	public ResponseEntity<Object> findbyid(String libelleservicecou) {
		try {
			List<ServiceCouMod> acces = servicec.getByLibelleServCou(libelleservicecou);
			resultat = om.writeValueAsString(acces);
			log.info("nbrligne: " + resultat);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
