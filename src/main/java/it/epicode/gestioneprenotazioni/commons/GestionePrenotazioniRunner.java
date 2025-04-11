package it.epicode.gestioneprenotazioni.commons;

import it.epicode.gestioneprenotazioni.postazione.Postazione;
import it.epicode.gestioneprenotazioni.postazione.PostazioneService;
import it.epicode.gestioneprenotazioni.postazione.TipoPostazione;
import it.epicode.gestioneprenotazioni.prenotazione.Prenotazione;
import it.epicode.gestioneprenotazioni.prenotazione.PrenotazioneService;
import it.epicode.gestioneprenotazioni.utente.Utente;
import it.epicode.gestioneprenotazioni.utente.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
@Slf4j
@Component
public class GestionePrenotazioniRunner implements CommandLineRunner {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String username;
        String date;
        String city;
        TipoPostazione type = null;
        Long idPrenotazione = null;
        boolean continua = true;
        System.out.println("GestionePrenotazioniRunner partito");
        System.out.println("inserisci il tuo username");
        username = scanner.nextLine();
        Utente utente = utenteService.findByUsername(username);
        //gestire creazione nuovo utente
        while (continua){
            System.out.println("Seleziona l'operazione da effettuare");
            System.out.println("1. Ricerca postazione per tipo e città");
            System.out.println("2. Prenota postazione");
            System.out.println("3. Annulla prenotazione");
            System.out.println("4. Cambia Utente");
            System.out.println("0. Esci");
            int choice = 50;
            try {
                choice = scanner.nextInt();

            } catch (InputMismatchException e) {
                log.error("devi inserire un numero!");
            }
            finally {
                scanner.nextLine();
            }
            switch (choice) {
                case 1:
                    System.out.println("Inserisci la città");
                    city = scanner.nextLine();
                    System.out.println("Inserisci il tipo di postazione");
                    System.out.println("1. Privato");
                    System.out.println("2. Open Space");
                    System.out.println("3. Sala Riunioni");
                    int choice2 = 50;
                    try {
                        choice2 = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        log.error("Devi inserire un numero!");
                    }
                    finally {
                        scanner.nextLine();
                    }
                    switch (choice2) {
                        case 1:
                            type = TipoPostazione.PRIVATO;
                            break;
                        case 2:
                            type = TipoPostazione.OPENSPACE;
                            break;
                        case 3:
                            type = TipoPostazione.SALA_RIUNIONI;
                            break;
                        default:
                            System.out.println("Scelta non valida");
                            break;
                    }
                    if (type != null && city != null) {
                        List<Postazione> postazioni = postazioneService.findByCittaAndTipo(city, type);
                        for (Postazione postazione : postazioni) {
                            System.out.println(postazione.toString());
                        }
                        if  (postazioni.isEmpty()) {
                            System.out.println("Nessuna postazione trovata");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Inserisci la data della prenotazione");
                    date = scanner.nextLine();
                    System.out.println("Inserisci la postazione da prenotare");
                    try {
                        idPrenotazione = scanner.nextLong();
                    }
                    catch (InputMismatchException e){
                        log.error("Devi inserire un numero!");
                    }
                    finally {
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    System.out.println("Inserisci l'id della prenotazione da cancellare");
                    try {
                        idPrenotazione = scanner.nextLong();
                    }
                    catch (InputMismatchException e){
                        log.error("Devi inserire un numero!");
                    }
                    finally {
                        scanner.nextLine();
                    }

                    try {
                        prenotazioneService.deleteById(idPrenotazione);
                        System.out.println("Prenotazione cancellata");
                    } catch (IllegalArgumentException e) {
                        log.error(e.getMessage());
                    }

                    break;
                case 4:
                    System.out.println("inserisci il tuo username");
                    username = scanner.nextLine();
                    utente = utenteService.findByUsername(username);
                    break;
                case 0:
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }



    }
}
