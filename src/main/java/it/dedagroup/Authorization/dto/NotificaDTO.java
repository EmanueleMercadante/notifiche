package it.dedagroup.Authorization.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import it.dedagroup.Authorization.enums.NotificaType;
import lombok.Data;

@Data
public class NotificaDTO {

	private NotificaType tipoNotifica;
	private String descrizione;
	private String messaggio;

}
