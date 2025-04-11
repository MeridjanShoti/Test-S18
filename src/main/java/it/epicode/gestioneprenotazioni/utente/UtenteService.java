package it.epicode.gestioneprenotazioni.utente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    public Utente createUtente(Utente utente){
        if(utente == null){
            throw new IllegalArgumentException("Utente non valido");
        }
        if(utenteRepository.findByUsername(utente.getUsername()).isPresent()){
            throw new IllegalArgumentException("Username già esistente");
        }
        if(utenteRepository.findByEmail(utente.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email già esistente");
        }
        return utenteRepository.save(utente);
    }
}
