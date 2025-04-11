package it.epicode.gestioneprenotazioni.edificio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;
}
