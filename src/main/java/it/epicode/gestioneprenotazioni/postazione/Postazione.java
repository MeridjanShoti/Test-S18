package it.epicode.gestioneprenotazioni.postazione;

import it.epicode.gestioneprenotazioni.edificio.Edificio;
import it.epicode.gestioneprenotazioni.prenotazione.Prenotazione;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "postazioni")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descrizione;
    private TipoPostazione tipo;
    private int numeroMassimoOccupanti;
    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
    @OneToMany (mappedBy = "postazione")
    private List<Prenotazione> prenotazione;
}
