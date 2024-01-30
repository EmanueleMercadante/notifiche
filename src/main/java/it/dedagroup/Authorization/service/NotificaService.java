package it.dedagroup.Authorization.service;

import java.util.List;
import java.util.UUID;

import it.dedagroup.Authorization.dto.NotificaDTO;
import it.dedagroup.Authorization.model.Notifica;

public interface NotificaService {

	NotificaDTO creaNotifica(NotificaDTO NDTO);
	
	List<Notifica> listaNotifiche();

	Notifica rimuoviNotifica(UUID id);	
}
