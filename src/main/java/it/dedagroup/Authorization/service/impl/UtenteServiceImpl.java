package it.dedagroup.Authorization.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.Authorization.dto.LoginDTO;
import it.dedagroup.Authorization.dto.UtenteDTO;
import it.dedagroup.Authorization.mapper.UtenteMapper;
import it.dedagroup.Authorization.model.Utente;
import it.dedagroup.Authorization.repository.UtenteRepository;
import it.dedagroup.Authorization.service.UtenteService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService {
	
	private final UtenteRepository utenteRepository;
	private final UtenteMapper utenteMapper;

	@Override
	public void registraUtente(UtenteDTO utenteDTO) {
		
		Utente utenteEntity = utenteMapper.toEntity(utenteDTO);
		
		utenteRepository.findByEmail(utenteEntity.getEmail())           
	    .ifPresentOrElse(
	        utente -> { throw new RuntimeException("L'utente già esiste!"); }, 
	        () -> { utenteRepository.save(utenteEntity); } 						
	    );
	}
	
	@Override
	public Utente loginUtente(LoginDTO loginDTO) {
		return utenteRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword())
				.orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nessun utente trovato con queste credenziali"));
	}

}
