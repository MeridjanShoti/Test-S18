package it.epicode.gestioneprenotazioni.utente;

import it.epicode.gestioneprenotazioni.prenotazione.Prenotazione;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Utente")

public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String username;
    private String nomeCompleto;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;
    @Override
    public String toString() {
        return "[id= " + id +
                ", username= " + username +
                ", nomeCompleto= " + nomeCompleto +
                ", email= " + email +
                "]";
    }

}