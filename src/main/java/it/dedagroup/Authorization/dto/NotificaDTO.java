package it.dedagroup.Authorization.dto;

import it.dedagroup.Authorization.enums.NotificaType;
import lombok.Data;

@Data
public class NotificaDTO {

	private NotificaType tipoNotifica;
	private String descrizione;
	private String messaggio;

}
