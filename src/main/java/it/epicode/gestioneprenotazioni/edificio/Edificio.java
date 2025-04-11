package it.epicode.gestioneprenotazioni.edificio;

import it.epicode.gestioneprenotazioni.postazione.Postazione;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "edifici")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Edificio {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private Long id;
@Column(unique = true)
private String nome;
@Column(unique = true)
private String indirizzo;
private String citta;
@OneToMany (mappedBy = "edificio")
private List<Postazione> postazione;
@Override
public String toString() {
    return "[id=" + id +
            ", nome= " + nome +
            ", indirizzo= " + indirizzo +
            ", città= " + citta +
            "}";
}
}
