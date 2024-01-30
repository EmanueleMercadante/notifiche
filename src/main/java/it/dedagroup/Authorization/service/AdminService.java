package it.dedagroup.Authorization.service;

import java.util.List;
import java.util.UUID;

import it.dedagroup.Authorization.dto.UtenteOpzionaleDTO;
import it.dedagroup.Authorization.model.Utente;

public interface AdminService {

	List<Utente> listaUtenti();
	String rimuoviUtente(UUID id);
	Utente aggiornaUtente(UUID id, UtenteOpzionaleDTO utenteOpzionaleDTO);
}
