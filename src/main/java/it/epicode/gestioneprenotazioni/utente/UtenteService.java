package it.epicode.gestioneprenotazioni.utente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Utente findByUsername(String username){
        if(username == null){
            throw new IllegalArgumentException("Username non valido");
        }
        if(utenteRepository.findByUsername(username).isEmpty()){
            throw new IllegalArgumentException("Utente non trovato");
        }
        return utenteRepository.findByUsername(username).get();
    }
    public List<Utente> findAllUtenti(){
        return utenteRepository.findAll();
    }
    public void printUtenti(List<Utente> utenti){
        for(Utente utente : utenti){
            System.out.println(utente);
        }
    }
}
