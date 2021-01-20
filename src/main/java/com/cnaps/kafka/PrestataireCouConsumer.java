package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.PrestataireCouMod;
//import mg.cnaps.models.ParamDemandes;
import mg.cnaps.services.PrestataireCouService;

@Component
public class PrestataireCouConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(PrestataireCouConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	PrestataireCouService service;
	

	@KafkaListener(topics = "listeprestatairecourrierReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pois: "+record.value().toString());
			List<PrestataireCouMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("nbrligne: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeprestatairecourrierRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listeprestatairecourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpageprestatairecourrierReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpageprestatairecourrierRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpageprestatairecourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutprestatairecourrierReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			service.save(om.readValue(record.value().toString(), PrestataireCouMod.class));
			producer.send(record.key().toString(), "ajoutprestatairecourrierRes", "success");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajoutprestatairecourrierRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprprestatairecourrierReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), PrestataireCouMod.class));
			producer.send(record.key().toString(), "supprprestatairecourrierRes", "succes");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprprestatairecourrierRes", e.getMessage());
		}
	}
}
