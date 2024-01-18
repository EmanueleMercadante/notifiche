package it.dedagroup.Authorization.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import it.dedagroup.Authorization.dto.NotificaDTO;
import it.dedagroup.Authorization.service.NotificaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class NotificaFacade {
	
	@Autowired
	NotificaService NService;
	
	
	public NotificaDTO aggiungiNotifica(NotificaDTO NDTO) {
		return NService.creaNotifica(NDTO);
	}
	
}
