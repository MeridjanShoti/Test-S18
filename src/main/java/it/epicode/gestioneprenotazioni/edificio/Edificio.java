package it.epicode.gestioneprenotazioni.edificio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "edifici")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Edificio {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private Long id;
private String nome;
private String indirizzo;
private String citta;
}
