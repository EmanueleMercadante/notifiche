package it.dedagroup.Authorization.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.dedagroup.Authorization.enums.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtenteDTO {
	
    @NotBlank(message = "Il nome è obbligatorio")
	private String nome;
    @NotBlank(message = "Il cognome è obbligatorio")
	private String cognome;
    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "L'email non è in un formato valido")
	private String email;
    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
	private String password;
    @JsonIgnore     //ruolo settato nel facade
	private Ruolo ruolo;

}
