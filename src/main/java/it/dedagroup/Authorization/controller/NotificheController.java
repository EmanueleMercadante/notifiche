package it.dedagroup.Authorization.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.Authorization.dto.NotificaDTO;
import it.dedagroup.Authorization.facade.NotificaFacade;
import it.dedagroup.Authorization.model.Notifica;

@RestController
@RequestMapping("/notifica")
public class NotificheController {
	
	@Autowired
	private NotificaFacade notificaFacade;
	
	@Value("${consumer.queue.notifiche}")
	private String notificheQueue;
	
	
	
	@PostMapping("/addNotifica")
	public ResponseEntity<Void> addNotifica(@RequestBody NotificaDTO notificaDTO) {
		notificaFacade.aggiungiNotifica(notificaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/sendNotifica")
	public ResponseEntity<String> sendNotifica(@RequestBody NotificaDTO notificaDTO) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(notificaFacade.inviaNotifica(notificheQueue, notificaDTO));
	}
	
	@GetMapping("/getNotifiche")
	public ResponseEntity<List<Notifica>> getNotifiche(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(notificaFacade.listaNotifiche());
	}
	
	@GetMapping("/rimuoviNotifica/{id}")
	ResponseEntity<Notifica> rimuoviNotifica(@PathVariable UUID id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(notificaFacade.rimuoviNotifica(id));
	}

}
