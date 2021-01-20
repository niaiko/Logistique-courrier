package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.PrestAdresseCouMod;
//import mg.cnaps.models.ParamDemandes;
import mg.cnaps.services.PrestAdresseCouService;

@Component
public class PrestAdresseCouConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(PrestAdresseCouConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	PrestAdresseCouService service;
	

	@KafkaListener(topics = "listeprestadcourrierReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pois: "+record.value().toString());
			List<PrestAdresseCouMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("nbrligne: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeprestadcourrierRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listeprestadcourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpageprestadcourrierReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpageprestadcourrierRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpageprestadcourrierRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutprestadcourrierReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			service.save(om.readValue(record.value().toString(), PrestAdresseCouMod.class));
			producer.send(record.key().toString(), "ajoutprestadcourrierRes", "success");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajoutprestadcourrierRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprprestadcourrierReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), PrestAdresseCouMod.class));
			producer.send(record.key().toString(), "supprprestadcourrierRes", "succes");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprprestadcourrierRes", e.getMessage());
		}
	}
}
