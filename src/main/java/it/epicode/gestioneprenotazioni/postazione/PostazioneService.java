package it.epicode.gestioneprenotazioni.postazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Postazione> findAllPostazioni(){
        return postazioneRepository.findAll();
    }
    public List<Postazione> findByCittaAndTipo(String citta, TipoPostazione tipo){
        if(citta == null || tipo == null){
            throw new IllegalArgumentException("Città o tipo non validi");
        }
        return postazioneRepository.findByEdificio_CittaIgnoreCaseAndTipo(citta, tipo);
    }
    public Postazione findById(Long id){
        return postazioneRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Postazione non trovata"));
    }
    public void printPostazioni(List<Postazione> postazioni){
        for(Postazione postazione : postazioni){
            System.out.println(postazione);
        }
    }
}
