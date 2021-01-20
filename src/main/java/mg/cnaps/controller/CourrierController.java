package mg.cnaps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.CourrierMod;
import mg.cnaps.models.ParamCourrier;
import mg.cnaps.models.ParamListeAllCourrier;
import mg.cnaps.repository.CourrierRepository;
import mg.cnaps.services.CourrierService;
import mg.cnaps.utils.DateUtil;

@RestController
@CrossOrigin("*")
public class CourrierController {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(CourrierController.class);
	@Autowired
	CourrierService service;

	@Autowired
	CourrierRepository repository;

	@PostMapping(path = "/listeallcourrier")
	public ResponseEntity<Object> liste(@RequestBody ParamListeAllCourrier allCourrier) {
		try {
			Pageable pageable = new PageRequest(allCourrier.getPage() - 1, 10);
			Page<CourrierMod> liste = repository.findallcourrier(pageable);
			allCourrier.setL(liste.getContent());
			allCourrier.setNbPage(liste.getTotalPages());
			log.info("listecou: " + om.writeValueAsString(allCourrier));
			return new ResponseEntity<>(allCourrier, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/nbrpagecourrier")
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

	@PostMapping(path = "ajoutcourrier")
	public CourrierMod ajout(@RequestBody CourrierMod param) {
		try {
			CourrierMod newCourrierMod = new CourrierMod();
			newCourrierMod.setDateInsert(DateUtil.sqlDateNow());
			newCourrierMod.setObservation(param.getObservation());
			newCourrierMod.setNumeroCourrier(param.getNumeroCourrier());
			newCourrierMod.setPoidsCourrier(param.getPoidsCourrier());
			newCourrierMod.setDateCourrier(param.getDateCourrier());
			newCourrierMod.setNombreCourrier(param.getNombreCourrier());
			newCourrierMod.setExpediteur(param.getExpediteur());
			newCourrierMod.setDestinataire(param.getDestinataire());
			newCourrierMod.setAdresse(param.getAdresse());
			newCourrierMod.setIdspec(param.getIdspec());
			newCourrierMod.setIdservice(param.getIdservice());
			newCourrierMod.setIdprest(param.getIdprest());
			newCourrierMod.setIdtrajet(param.getIdtrajet());
			newCourrierMod.setUserinsert(param.getUserinsert());
			newCourrierMod.setFlagcou(param.getFlagcou());
			newCourrierMod.setCodedr(param.getCodedr());
			service.save(newCourrierMod);
			// log.info("record : "+record.value().toString());
			CourrierMod acces = service.getByDestinataireDateCouIdserviceAdresse(param.getDestinataire(),
					param.getExpediteur(), param.getDateInsert(), param.getIdservice(), param.getAdresse());
			log.info("val : " + om.writeValueAsString(acces));
			return acces;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping(path = "supprcourrier")
	public void suppr(@RequestBody CourrierMod cm) {
		try {
			service.delete(cm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(path = "/findbyrechcou")
	public ResponseEntity<Object> findbydestinatairedatecou(@RequestBody ParamCourrier param) {
		try {
			// CourrierMod param = new CourrierMod();
			log.info("rech1: " + param.getDestinataire());
			log.info("rech2: " + param.getDateInsert());
			log.info("rech3: " + param.getFlagcou());
			Pageable pageable = new PageRequest(param.getPage() - 1, 10);
			Page<CourrierMod> acces = repository.getByDestinataireDateCou(param.getDestinataire(),
					param.getDateInsert(), param.getFlagcou(), pageable);
			param.setL(acces.getContent());
			param.setNbPage(acces.getTotalPages());
			param.setPage(param.getPage());
			resultat = om.writeValueAsString(param);
			log.info("nbrligne: " + resultat);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/findbyIdserviceDestinataireAdresse")
	public ResponseEntity<Object> findbyIdserviceCodeserviceDestinataireAdresse(@RequestBody CourrierMod param) {
		try {
			log.info("rech1: " + param.getDestinataire());
			log.info("rech2: " + param.getDateCourrier());
			log.info("rech3: " + param.getIdservice());
			log.info("rech4: " + param.getFlagcou());
			if (param.getDateInsert() == null) {
				log.info("rech test: " + param.getAdresse());
				resultat = om.writeValueAsString("Test Value");
				return new ResponseEntity<>(resultat, HttpStatus.OK);
				// producer.send(record.key().toString(),
				// "findbyIdserviceDestinataireAdresseRes", resultat);
			} else {
				CourrierMod acces = service.getByDestinataireDateCouIdserviceAdresse(param.getDestinataire(),
						param.getExpediteur(), param.getDateInsert(), param.getIdservice(), param.getAdresse());
				resultat = om.writeValueAsString(acces);
				log.info("resultat: " + resultat);
				return new ResponseEntity<>(resultat, HttpStatus.OK);
				// producer.send(record.key().toString(),
				// "findbyIdserviceDestinataireAdresseRes", resultat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
