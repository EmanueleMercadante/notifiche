package it.dedagroup.Authorization.dto;


import it.dedagroup.Authorization.enums.NotificaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class NotificaDTO {

	@NotBlank
	@Pattern(regexp = "(?i)INFO|ERROR|WARNING", message = "Il ruolo deve essere INFO o ERROR o WARNING")
	private NotificaType tipoNotifica;
	@NotBlank
	private String descrizione;
	@NotBlank
	private String messaggio;

}
