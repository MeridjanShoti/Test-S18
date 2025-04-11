package it.epicode.gestioneprenotazioni.postazione;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    List<Postazione> findByEdificio_CittaIgnoreCaseAndTipo(String citta, TipoPostazione tipo);
}