package it.epicode.gestioneprenotazioni.postazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostazioneService {
    @Autowired
    private PostazioneRepository postazioneRepository;
    public Postazione createPostazione(Postazione postazione){
        if(postazione == null){
            throw new IllegalArgumentException("Postazione non valida");
        }
        return postazioneRepository.save(postazione);
    }
}
