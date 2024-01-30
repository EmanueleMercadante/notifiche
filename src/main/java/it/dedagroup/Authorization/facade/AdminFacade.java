package it.dedagroup.Authorization.facade;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.Authorization.dto.UtenteOpzionaleDTO;
import it.dedagroup.Authorization.model.Utente;
import it.dedagroup.Authorization.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AdminFacade {

	@Autowired
	AdminService adminService;
	
	public List<Utente> listaUtenti() {
		return adminService.listaUtenti();
	}
	
	public String rimuoviUtente(UUID id) {
		if(id != null) return adminService.rimuoviUtente(id);
		else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "valori null non accettati");
		
	}

	public Utente aggiornaUtente(UUID id, UtenteOpzionaleDTO utenteOpzionaleDTO) {
		if(id != null) return adminService.aggiornaUtente(id, utenteOpzionaleDTO);
		else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id ha valore null");

	}
}
