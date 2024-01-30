package it.dedagroup.Authorization.facade;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.Authorization.dto.NotificaDTO;
import it.dedagroup.Authorization.model.Notifica;
import it.dedagroup.Authorization.producer.NotificheProducer;
import it.dedagroup.Authorization.service.NotificaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class NotificaFacade {
	
	@Autowired
	NotificaService notificaService;
	
	@Autowired
	NotificheProducer notificheProducer;
	
	
	public NotificaDTO aggiungiNotifica (NotificaDTO notificaDTO) {
		return notificaService.creaNotifica(notificaDTO);
	}
	
	public String inviaNotifica (String queueName, NotificaDTO notificaDTO) {
		return notificheProducer.sendMessage(queueName, notificaDTO);
	}
	
	public List<Notifica> listaNotifiche(){
		List<Notifica> listaNotifiche = notificaService.listaNotifiche();
		
		if (listaNotifiche.isEmpty()) {
			 log.info("Non sono presenti notifiche nel db");
		}
		
		return listaNotifiche;
	}

	public Notifica rimuoviNotifica(UUID id) {
		if(id != null) return notificaService.rimuoviNotifica(id);
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID non valido");
	}
	
}
