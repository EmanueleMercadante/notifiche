package it.dedagroup.Authorization.service;

import it.dedagroup.Authorization.dto.LoginDTO;
import it.dedagroup.Authorization.dto.UtenteDTO;
import it.dedagroup.Authorization.model.Utente;

public interface UtenteService {

	void registraUtente(UtenteDTO utenteDTO);
	Utente loginUtente(LoginDTO loginDTO);
}
