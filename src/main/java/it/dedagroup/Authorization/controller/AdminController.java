package it.dedagroup.Authorization.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.Authorization.dto.UtenteOpzionaleDTO;
import it.dedagroup.Authorization.facade.AdminFacade;
import it.dedagroup.Authorization.model.Utente;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminFacade adminFacade;

	
	@GetMapping("/listaUtenti")
	ResponseEntity<List<Utente>> listaUtenti(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(adminFacade.listaUtenti());
	}
	
	@GetMapping("/rimuoviUtente/{id}")
	ResponseEntity<String> rimuoviUtente(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(adminFacade.rimuoviUtente(id));
	}
	
	@PutMapping("/aggiornaUtente/{id}")
	ResponseEntity<Utente> aggiornaUtente(@PathVariable UUID id, @Valid @RequestBody UtenteOpzionaleDTO utenteOpzionaleDTO) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(adminFacade.aggiornaUtente(id, utenteOpzionaleDTO));
	}
}
