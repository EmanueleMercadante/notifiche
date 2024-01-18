package it.dedagroup.Authorization.service;

import java.util.List;

import it.dedagroup.Authorization.dto.NotificaDTO;

public interface NotificaService {

	NotificaDTO creaNotifica(NotificaDTO NDTO);
	
	List<NotificaDTO> listaNotifiche();	
}
