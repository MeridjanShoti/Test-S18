package it.epicode.gestioneprenotazioni.prenotazione;

import it.epicode.gestioneprenotazioni.edificio.EdificioService;
import it.epicode.gestioneprenotazioni.postazione.Postazione;
import it.epicode.gestioneprenotazioni.postazione.PostazioneService;
import it.epicode.gestioneprenotazioni.utente.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private EdificioService edificioService;
    public Prenotazione createPrenotazione(Prenotazione prenotazione){
        if(prenotazione == null){
            throw new IllegalArgumentException("Prenotazione non valida");
        }
        return prenotazioneRepository.save(prenotazione);
    }
    public Prenotazione findById(Long id){
        return prenotazioneRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Prenotazione non trovata"));
    }
    public void deleteById(Long id){
        if(findById(id) == null) {
            throw new IllegalArgumentException("Prenotazione non trovata");
        } else {
            prenotazioneRepository.deleteById(id);
        }
    }
    public boolean giornoGiaPrenotatoDaUtente(Utente utente, LocalDate data){
        return prenotazioneRepository.existsByUtenteAndDataPrenotazione(utente, data);
    }
    public boolean giornoGiaPrenotatoDaPostazione(Postazione postazione, LocalDate data){
        return prenotazioneRepository.existsByPostazioneAndDataPrenotazione(postazione, data);
    }
    public List<Prenotazione> findAll(){
        return prenotazioneRepository.findAll();
    }
    public void printPrenotazioni(List<Prenotazione> prenotazioni){
        for(Prenotazione prenotazione : prenotazioni){
            System.out.println(prenotazione);
        }
    }
}
