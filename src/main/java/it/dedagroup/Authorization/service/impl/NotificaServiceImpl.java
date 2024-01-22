package it.dedagroup.Authorization.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import it.dedagroup.Authorization.dto.NotificaDTO;
import it.dedagroup.Authorization.mapper.NotificaMapper;
import it.dedagroup.Authorization.model.Notifica;
import it.dedagroup.Authorization.repository.NotificaRepository;
import it.dedagroup.Authorization.service.NotificaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificaServiceImpl implements NotificaService{
	
	private final NotificaRepository notificaRepository;
	private final NotificaMapper notificaMapper;
	
	
	@Override
	public NotificaDTO creaNotifica(NotificaDTO notificaDTO) {
		Notifica notificaEntity = notificaMapper.toEntity(notificaDTO);
		
		notificaRepository.findByMessaggio(notificaEntity.getMessaggio())           //ritorna un Optional<Notifica>
	    .ifPresentOrElse(
	        notifica -> { throw new RuntimeException("La notifica già esiste!"); }, //se presente
	        () -> { notificaRepository.save(notificaEntity); } 						//se vuoto
	    );
		
		return notificaDTO;
	}

	@Override
	public List<NotificaDTO> listaNotifiche() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
