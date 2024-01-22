package it.dedagroup.Authorization.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import it.dedagroup.Authorization.dto.NotificaDTO;
import it.dedagroup.Authorization.producer.NotificheProducer;
import it.dedagroup.Authorization.service.NotificaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

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
	
}
