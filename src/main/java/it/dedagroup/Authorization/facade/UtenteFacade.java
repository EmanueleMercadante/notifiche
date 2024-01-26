package it.dedagroup.Authorization.facade;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.Authorization.dto.LoginDTO;
import it.dedagroup.Authorization.dto.UtenteDTO;
import it.dedagroup.Authorization.enums.Ruolo;
import it.dedagroup.Authorization.model.Utente;
import it.dedagroup.Authorization.service.UtenteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UtenteFacade {
	
	@Autowired
	UtenteService utenteService;

	public String registraUtente(UtenteDTO utenteDTO) {
		utenteDTO.setRuolo(Ruolo.CLIENTE);
		utenteService.registraUtente(utenteDTO);
		
		log.info("Utente registrato con successo!");
		return "Utente registrato con successo!";
	}
	
	public Utente loginUtente(LoginDTO loginDTO){
        return utenteService.loginUtente(loginDTO);
    }
}
