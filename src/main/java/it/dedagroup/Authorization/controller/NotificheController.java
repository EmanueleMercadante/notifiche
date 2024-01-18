package it.dedagroup.Authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.Authorization.dto.NotificaDTO;
import it.dedagroup.Authorization.facade.NotificaFacade;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifica")
public class NotificheController {
	
	@Autowired
	private NotificaFacade NFacade;
	
	
	@PostMapping("/addNotifica")
	public ResponseEntity<Void> addNotifica(@RequestBody NotificaDTO NDTO) {
		NFacade.aggiungiNotifica(NDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@GetMapping
	public ResponseEntity<String> getAllNotifiche(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	
	

}
