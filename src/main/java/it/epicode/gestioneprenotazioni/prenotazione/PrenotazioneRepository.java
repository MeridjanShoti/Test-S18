package it.epicode.gestioneprenotazioni.prenotazione;


import it.epicode.gestioneprenotazioni.utente.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Optional<List<Prenotazione>> findByDataPrenotazione(LocalDate dataPrenotazione);
    boolean existsByUtenteAndDataPrenotazione(Utente utente, LocalDate dataPrenotazione);
}