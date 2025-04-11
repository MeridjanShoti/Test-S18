package it.epicode.gestioneprenotazioni.prenotazione;

import it.epicode.gestioneprenotazioni.postazione.Postazione;
import it.epicode.gestioneprenotazioni.utente.Utente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Prenotazioni")

public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate dataPrenotazione;
    @ManyToOne
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private Postazione postazione;
}