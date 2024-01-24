package it.dedagroup.Authorization.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.dedagroup.Authorization.dto.NotificaDTO;
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
	
	public List<NotificaDTO> listaNotifiche(){
		List<NotificaDTO> listaNotificheDTO = notificaService.listaNotifiche();
		
		if (listaNotificheDTO.isEmpty()) {
			 log.info("Non sono presenti notifiche nel db");
		}
		
		return listaNotificheDTO;
	}
	
}
