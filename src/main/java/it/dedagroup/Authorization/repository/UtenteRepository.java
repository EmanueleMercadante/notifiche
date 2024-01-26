package it.dedagroup.Authorization.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import it.dedagroup.Authorization.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, UUID>{

	public Optional<Utente> findById(UUID id);
	public Optional<Utente> findByEmail(String email);
	public Optional<Utente> findByEmailAndPassword(String email, String password);
}
