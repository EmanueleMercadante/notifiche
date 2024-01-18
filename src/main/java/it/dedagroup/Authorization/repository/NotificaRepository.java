package it.dedagroup.Authorization.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.Authorization.model.Notifica;

public interface NotificaRepository extends JpaRepository<Notifica, UUID> {
	
	public Optional<Notifica> findById(UUID id);
	public Optional<Notifica> findByMessaggio(String messaggio);

}
