package it.dedagroup.Authorization.mapper;

import org.mapstruct.Mapper;

import it.dedagroup.Authorization.dto.UtenteDTO;
import it.dedagroup.Authorization.model.Utente;



@Mapper(componentModel = "spring")
public interface UtenteMapper {

	Utente toEntity(UtenteDTO utenteDTO);
	
	UtenteDTO toDto(Utente Utente);
}
