package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.SpecCouMod;
//import mg.cnaps.models.ParamDemandes;
import mg.cnaps.services.SpecCouService;

@Component
public class SpecCouConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(SpecCouConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	SpecCouService service;
	

	@KafkaListener(topics = "listespeccourrierReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("Spec: "+record.value().toString());
			List<SpecCouMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("renvoie: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listespeccourrierRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listespeccourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpagespeccourrierReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagespeccourrierRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagespeccourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutspeccourrierReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			service.save(om.readValue(record.value().toString(), SpecCouMod.class));
			producer.send(record.key().toString(), "ajoutspeccourrierRes", "success");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajouttrspeccourrierRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprspeccourrierReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), SpecCouMod.class));
			producer.send(record.key().toString(), "supprspeccourrierRes", "succes");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprspeccourrierRes", e.getMessage());
		}
	}
}
