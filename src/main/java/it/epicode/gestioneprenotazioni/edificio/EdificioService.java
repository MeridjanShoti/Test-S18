package it.epicode.gestioneprenotazioni.edificio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;
    public Edificio createEdificio(Edificio edificio){
        if(edificio == null){
            throw new IllegalArgumentException("Edificio non valido");
        }
        if(edificioRepository.findByNomeAndIndirizzo(edificio.getNome(), edificio.getIndirizzo()).isPresent()){
            throw new IllegalArgumentException("Nome edificio non valido");
        }
        return edificioRepository.save(edificio);
    }
}
