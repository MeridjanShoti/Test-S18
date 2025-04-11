package it.epicode.gestioneprenotazioni.prenotazione;


import it.epicode.gestioneprenotazioni.utente.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByUtenteAndDataPrenotazione(Utente utente, LocalDate dataPrenotazione);
    boolean existsByPostazioneAndDataPrenotazione(it.epicode.gestioneprenotazioni.postazione.Postazione postazione, LocalDate dataPrenotazione);
}