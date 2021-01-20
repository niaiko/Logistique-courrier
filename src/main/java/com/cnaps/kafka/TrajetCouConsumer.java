package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.TrajetCouMod;
//import mg.cnaps.models.ParamDemandes;
import mg.cnaps.services.TrajetCouService;

@Component
public class TrajetCouConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(TrajetCouConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	TrajetCouService service;
	

	@KafkaListener(topics = "listetrajetcourrierReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pois: "+record.value().toString());
			List<TrajetCouMod> liste = service.findalltrajetcou();
			log.info("nbrligne: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listetrajetcourrierRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listetrajetcourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpagetrajetcourrierReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagetrajetcourrierRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagetrajetcourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajouttrajetcourrierReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			service.save(om.readValue(record.value().toString(), TrajetCouMod.class));
			producer.send(record.key().toString(), "ajouttrajetcourrierRes", "success");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajouttrajetcourrierRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprtrajetcourrierReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), TrajetCouMod.class));
			producer.send(record.key().toString(), "supprtrajetcourrierRes", "succes");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprtrajetcourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "findbyrechtrajetReq")
	public void findbyid(ConsumerRecord<?, ?> record) {
		log.info("rech: "+record.value().toString());
		try {
			List<TrajetCouMod> acces = service.getByLibelleTrajetCou(record.value().toString());
			resultat = om.writeValueAsString(acces);
			log.info("nbrligne: "+resultat);
			producer.send(record.key().toString(), "findbyrechtrajetRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "findbyrechtrajetRes", e.getMessage());
		}
	}
}
