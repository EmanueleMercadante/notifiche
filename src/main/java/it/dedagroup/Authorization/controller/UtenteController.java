package it.dedagroup.Authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.Authorization.dto.LoginDTO;
import it.dedagroup.Authorization.dto.UtenteDTO;
import it.dedagroup.Authorization.facade.UtenteFacade;
import it.dedagroup.Authorization.model.Utente;
import it.dedagroup.Authorization.security.GestoreToken;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/utente")
public class UtenteController {

	@Autowired
	private UtenteFacade utenteFacade;
	
	@Autowired
    private GestoreToken gestoreToken;
	
	@PostMapping("/registrazioneUtente")
	public ResponseEntity<String> registrazioneUtente(@Valid @RequestBody UtenteDTO utenteDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(utenteFacade.registraUtente(utenteDTO));
	}
	
	@PostMapping("/loginUtente")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO){
        Utente utente = utenteFacade.loginUtente(loginDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Authorization",gestoreToken.generaToken(utente)).body("Benvenuto!");
    }
}
