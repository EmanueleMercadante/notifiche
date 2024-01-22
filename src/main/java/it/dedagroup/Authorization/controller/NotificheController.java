package it.dedagroup.Authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.Authorization.dto.NotificaDTO;
import it.dedagroup.Authorization.facade.NotificaFacade;
import it.dedagroup.Authorization.producer.NotificheProducer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notifica")
public class NotificheController {
	
	@Autowired
	private NotificaFacade notificaFacade;
	
	@Autowired
	private NotificheProducer notificheProducer;
	
	@Value("${consumer.queue.notifiche}")
	private String notificheQueue;
	
	
	@PostMapping("/addNotifica")
	public ResponseEntity<Void> addNotifica(@RequestBody NotificaDTO notificaDTO) {
		notificaFacade.aggiungiNotifica(notificaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/sendNotifica")
	public ResponseEntity<String> sendNotifica(@RequestBody NotificaDTO notificaDTO) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(notificheProducer.sendMessage(notificheQueue, notificaDTO));
	}
	
//	@GetMapping
//	public ResponseEntity<String> getAllNotifiche(){
//		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//	}
	
	
	

}
