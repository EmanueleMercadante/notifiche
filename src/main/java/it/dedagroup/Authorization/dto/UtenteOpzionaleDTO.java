package it.dedagroup.Authorization.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtenteOpzionaleDTO {
		
	private String nome;
	private String cognome;
	@Email(message = "L'email non è in un formato valido")
	private String email;
	@Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
	private String password;
    @Pattern(regexp = "(?i)CLIENTE|ADMIN", message = "Il ruolo deve essere CLIENTE o ADMIN")
	private String ruolo;
	
	
	public boolean isNull () {
		if ((this.nome == null || this.nome.isBlank()) &&
			(this.cognome == null || this.cognome.isBlank()) &&
			(this.email == null || this.email.isBlank()) &&
			(this.password == null || this.password.isBlank()) &&
			(this.ruolo == null || this.ruolo.isBlank())) {
			
			return true;
		} else {
			return false;
		}
	}
}
