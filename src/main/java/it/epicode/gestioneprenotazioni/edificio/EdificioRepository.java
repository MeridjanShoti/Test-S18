package it.epicode.gestioneprenotazioni.edificio;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    Optional<Edificio> findByNomeAndIndirizzo(String nome, String indirizzo);
}