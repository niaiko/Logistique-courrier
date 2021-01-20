package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

//import mg.cnaps.models.AccueilMod;
import mg.cnaps.models.ServiceCouMod;
//import mg.cnaps.models.ParamDemandes;
import mg.cnaps.services.ServiceCouService;

@Component
public class ServiceCouConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(ServiceCouConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	ServiceCouService servicec;
	

	@KafkaListener(topics = "listeservicecourrierReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pois: "+record.value().toString());
			List<ServiceCouMod> liste = servicec.getAll(Integer.parseInt(record.value().toString()));
			log.info("nbrligne: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeservicecourrierRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listeservicecourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpageservicecourrierReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = servicec.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpageservicecourrierRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpageservicecourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutservicecourrierReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			servicec.save(om.readValue(record.value().toString(), ServiceCouMod.class));
			producer.send(record.key().toString(), "ajoutservicecourrierRes", "success");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajoutservicecourrierres", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprservicecourrierReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			servicec.delete(om.readValue(record.value().toString(), ServiceCouMod.class));
			producer.send(record.key().toString(), "supprservicecourrierRes", "succes");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprservicecourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "findbyrechReq")
	public void findbyid(ConsumerRecord<?, ?> record) {
		log.info("rech: "+record.value().toString());
		try {
			List<ServiceCouMod> acces = servicec.getByLibelleServCou(record.value().toString());
			resultat = om.writeValueAsString(acces);
			log.info("nbrligne: "+resultat);
			producer.send(record.key().toString(), "findbyrechRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "findbyrechRes", e.getMessage());
		}
	}
}
