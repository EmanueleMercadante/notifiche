package it.dedagroup.Authorization.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import it.dedagroup.Authorization.enums.NotificaType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Notifica")
public class Notifica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID")
	private UUID id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NotificaType tipoNotifica;
	
	private String descrizione;
	
	@Column(nullable = false)
	private String messaggio;
	
	@CreatedDate
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataCreazione;
	
	@LastModifiedDate
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataModifica;

}
