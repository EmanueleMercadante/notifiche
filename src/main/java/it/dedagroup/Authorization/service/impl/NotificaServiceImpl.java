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
	
	private final NotificaRepository NRepository;
	private final NotificaMapper NMapper;
	
	
	@Override
	public NotificaDTO creaNotifica(NotificaDTO n) {
		Notifica nEntity = NMapper.toEntity(n);
		Notifica notifica = NRepository.findByMessaggio(nEntity.getMessaggio()).orElse(null);
		if (notifica == null) {
			NRepository.save(nEntity);
			return n;
		} else {
			throw new RuntimeException("La notifica gia esiste!");
		}
	}

	@Override
	public List<NotificaDTO> listaNotifiche() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
