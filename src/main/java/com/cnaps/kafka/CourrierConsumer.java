package com.cnaps.kafka;

//import java.sql.Date;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.CourrierMod;
import mg.cnaps.services.CourrierService;
import mg.cnaps.utils.DateUtil;

@Component
public class CourrierConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(CourrierConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	CourrierService service;
	

	@KafkaListener(topics = "listeallcourrierReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pageliste: "+record.value().toString());
			List<CourrierMod> liste = service.findallcourrier(new PageRequest(Integer.parseInt(record.value().toString()), 10));
			log.info("listecou: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeallcourrierRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listeallcourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpagecourrierReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagecourrierRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagecourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutcourrierReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			CourrierMod param = om.readValue(record.value().toString(), CourrierMod.class);
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
			log.info("record : "+record.value().toString());
			CourrierMod acces = service.getByDestinataireDateCouIdserviceAdresse(param.getDestinataire(),param.getExpediteur(),param.getDateInsert(),param.getIdservice(),param.getAdresse());
			log.info("val : " + om.writeValueAsString(acces));
			producer.send(record.key().toString(), "ajoutcourrierRes", om.writeValueAsString(acces));
			producer.send(record.key().toString(), "saveCourrierBIReq", om.writeValueAsString(acces));
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "ajoutcourrierRes", e.getMessage());
		}
	}


	@KafkaListener(topics = "supprcourrierReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), CourrierMod.class));
			producer.send(record.key().toString(), "supprcourrierRes", "succes");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprcourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "findbyrechcouReq")
	public void findbydestinatairedatecou(ConsumerRecord<?, ?> record) {
		try {
//			log.info("rech1: "+record.value().toString());
			CourrierMod param = om.readValue(record.value().toString(), CourrierMod.class);
			log.info("rech1: "+param.getDestinataire());
			log.info("rech2: "+param.getDateInsert());
			log.info("rech3: "+param.getFlagcou());
			List<CourrierMod> acces = service.getByDestinataireDateCou(param.getDestinataire(),param.getDateInsert(),param.getFlagcou());
			resultat = om.writeValueAsString(acces);
			log.info("nbrligne: "+resultat);
			producer.send(record.key().toString(), "findbyrechcouRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "findbyrechcouRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "findbyIdserviceDestinataireAdresseReq")
	public void findbyIdserviceCodeserviceDestinataireAdresse(ConsumerRecord<?, ?> record) {
		try {
			log.info("rech: "+record.value().toString());
			CourrierMod param = om.readValue(record.value().toString(), CourrierMod.class);
			log.info("rech1: "+param.getDestinataire());
			log.info("rech2: "+param.getDateCourrier());
			log.info("rech3: "+param.getIdservice());
			log.info("rech4: "+param.getFlagcou());
			if(param.getDateInsert() == null) 
			{
				log.info("rech test: "+param.getAdresse());
				resultat = om.writeValueAsString("Test Value");
				producer.send(record.key().toString(), "findbyIdserviceDestinataireAdresseRes", resultat);
			}
			else
			{
				CourrierMod acces = service.getByDestinataireDateCouIdserviceAdresse(param.getDestinataire(),param.getExpediteur(),param.getDateInsert(),param.getIdservice(),param.getAdresse());
				resultat = om.writeValueAsString(acces);
				log.info("resultat: "+resultat);
				producer.send(record.key().toString(), "findbyIdserviceDestinataireAdresseRes", resultat);
			}
		} catch (Exception e) {
			producer.send(record.key().toString(), "findbyIdserviceDestinataireAdresseRes", e.getMessage());
		}
	}
}
