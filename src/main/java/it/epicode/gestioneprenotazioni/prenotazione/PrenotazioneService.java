package it.epicode.gestioneprenotazioni.prenotazione;

import it.epicode.gestioneprenotazioni.edificio.EdificioService;
import it.epicode.gestioneprenotazioni.postazione.Postazione;
import it.epicode.gestioneprenotazioni.postazione.PostazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

       /*List<Prenotazione> prenotazioniPresentiByData = prenotazioneRepository.findByDataPrenotazione(prenotazione.getDataPrenotazione()).get();
        List <Postazione> listaPostazioni = prenotazioniPresentiByData.stream().map(prenotazione1 -> prenotazione1.getPostazione()).toList();
        List <Postazione> listaPostazioniTotali = postazioneService.findAllPostazioni();*/


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

}
