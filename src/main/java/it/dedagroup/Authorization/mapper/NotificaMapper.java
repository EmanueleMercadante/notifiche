package it.dedagroup.Authorization.mapper;

import org.mapstruct.Mapper;

import it.dedagroup.Authorization.dto.NotificaDTO;
import it.dedagroup.Authorization.model.Notifica;

@Mapper(componentModel = "spring")
public interface NotificaMapper {

	Notifica toEntity(NotificaDTO nDTO);
	
	NotificaDTO toDto(Notifica notifica);
}
