package it.dedagroup.Authorization.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.Authorization.dto.UtenteOpzionaleDTO;
import it.dedagroup.Authorization.enums.Ruolo;
import it.dedagroup.Authorization.model.Utente;
import it.dedagroup.Authorization.repository.UtenteRepository;
import it.dedagroup.Authorization.service.AdminService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final UtenteRepository utenteRepository;

	@Override
	public List<Utente> listaUtenti() {
	    List<Utente> listaUtenti = utenteRepository.findAll();
	    
	    if (listaUtenti == null || listaUtenti.isEmpty()) {
	        throw new RuntimeException("Utenti non trovati");
	    }
	    return listaUtenti;
	}

	@Override
	public String rimuoviUtente(UUID id) {
	    utenteRepository.findById(id).ifPresentOrElse(
	            utente -> {
	            	if(utente.isCancellato()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente già rimosso");
	                utente.setCancellato(true);
	                utenteRepository.save(utente);
	            },
	            () -> { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato"); }
	    );
	    return "Utente cancellato con successo";
	}

	@Override
	public Utente aggiornaUtente(UUID id, UtenteOpzionaleDTO utenteOpzionaleDTO) {
		
		if(utenteOpzionaleDTO.isNull()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inserire almeno un campo valido da aggiornare!");
		
		Utente utente = utenteRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato"));
		
		if(utenteOpzionaleDTO.getNome() != null && !utenteOpzionaleDTO.getNome().isBlank()) 		utente.setNome(utenteOpzionaleDTO.getNome());
		if(utenteOpzionaleDTO.getCognome() != null && !utenteOpzionaleDTO.getCognome().isBlank()) 		utente.setCognome(utenteOpzionaleDTO.getCognome());
		if(utenteOpzionaleDTO.getEmail() != null && !utenteOpzionaleDTO.getEmail().isBlank()) 		utente.setEmail(utenteOpzionaleDTO.getEmail());
		if(utenteOpzionaleDTO.getPassword() != null) 		utente.setPassword(utenteOpzionaleDTO.getPassword());
		if(utenteOpzionaleDTO.getRuolo() != null && !utenteOpzionaleDTO.getRuolo().isBlank()) 		utente.setRuolo(Ruolo.valueOf(utenteOpzionaleDTO.getRuolo().toUpperCase()));
		
		utenteRepository.save(utente);
		
		return utente;
	}

	
	


}
