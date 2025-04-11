package it.epicode.gestioneprenotazioni.commons;

import it.epicode.gestioneprenotazioni.edificio.Edificio;
import it.epicode.gestioneprenotazioni.edificio.EdificioService;
import it.epicode.gestioneprenotazioni.postazione.Postazione;
import it.epicode.gestioneprenotazioni.postazione.PostazioneService;
import it.epicode.gestioneprenotazioni.postazione.TipoPostazione;
import it.epicode.gestioneprenotazioni.prenotazione.PrenotazioneService;
import it.epicode.gestioneprenotazioni.utente.Utente;
import it.epicode.gestioneprenotazioni.utente.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
//commentato dopo la creazione del database
public class CreazioneRunner implements CommandLineRunner {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private EdificioService edificioService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CreazioneRunner partito");
        Edificio edificio1 = new Edificio();
        edificio1.setNome("Edificio 1");
        edificio1.setIndirizzo("Via Roma 1");
        edificio1.setCitta("Roma");
        edificioService.createEdificio(edificio1);
        Edificio edificio2 = new Edificio();
        edificio2.setNome("Edificio 2");
        edificio2.setIndirizzo("Via Milano 2");
        edificio2.setCitta("Milano");
        edificioService.createEdificio(edificio2);
        Edificio edificio3 = new Edificio();
        edificio3.setNome("Edificio 3");
        edificio3.setIndirizzo("Via Napoli 3");
        edificio3.setCitta("Napoli");
        edificioService.createEdificio(edificio3);
        Edificio edificio4 = new Edificio();
        edificio4.setNome("Edificio 4");
        edificio4.setIndirizzo("Via Torino 4");
        edificio4.setCitta("Torino");
        edificioService.createEdificio(edificio4);

        Postazione postazione1 = new Postazione();
        postazione1.setDescrizione("Postazione 1");
        postazione1.setTipo(TipoPostazione.PRIVATO);
        postazione1.setNumeroMassimoOccupanti(4);
        postazione1.setEdificio(edificio1);
        postazioneService.createPostazione(postazione1);
        Postazione postazione2 = new Postazione();
        postazione2.setDescrizione("Postazione 2");
        postazione2.setTipo(TipoPostazione.OPENSPACE);
        postazione2.setNumeroMassimoOccupanti(7);
        postazione2.setEdificio(edificio2);
        postazioneService.createPostazione(postazione2);
        Postazione postazione3 = new Postazione();
        postazione3.setDescrizione("Postazione 3");
        postazione3.setTipo(TipoPostazione.SALA_RIUNIONI);
        postazione3.setNumeroMassimoOccupanti(15);
        postazione3.setEdificio(edificio3);
        postazioneService.createPostazione(postazione3);
        Utente utente1 = new Utente();
        utente1.setUsername("mario89");
        utente1.setNomeCompleto("Mario Rossi");
        utente1.setEmail("mariorossi@gmail.com");
        utenteService.createUtente(utente1);
        Utente utente2 = new Utente();
        utente2.setUsername("lucaRDF");
        utente2.setNomeCompleto("Luca Agostini");
        utente2.setEmail("lucaAgostini@gmail.com");
        utenteService.createUtente(utente2);
        Utente utente3 = new Utente();
        utente3.setUsername("giuseppeVerdi");
        utente3.setNomeCompleto("Giuseppe Verdi");
        utente3.setEmail("giuseppeverdi@gmail.com");
        utenteService.createUtente(utente3);

    }
}
