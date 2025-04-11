package it.epicode.gestioneprenotazioni.commons;

import it.epicode.gestioneprenotazioni.edificio.EdificioService;
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

import java.time.LocalDate;
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
    @Autowired
    private EdificioService edificioService;
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String username;
        String date;
        String city;
        TipoPostazione type = null;
        Long idPrenotazione = null;
        Long idPostazione = null;
        boolean continua = true;
        System.out.println("GestionePrenotazioniRunner partito");
        Utente utente = null;
        do {
            System.out.println("inserisci il tuo username");
            try {
                username = scanner.nextLine();
                utente = utenteService.findByUsername(username);
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
            }
        } while (utente == null);
        //gestire creazione nuovo utente
        while (continua){
            System.out.println("Seleziona l'operazione da effettuare");
            System.out.println("1. Ricerca postazione per tipo e città");
            System.out.println("2. Prenota postazione");
            System.out.println("3. Annulla prenotazione");
            System.out.println("4. Cambia Utente");
            System.out.println("5. Lista prenotazioni");
            System.out.println("6. Lista edifici");
            System.out.println("7. Lista Utenti");
            System.out.println("8. Lista postazioni");
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
                    if (!prenotazioneService.giornoGiaPrenotatoDaUtente(utente, LocalDate.parse(date))) {
                        System.out.println("Inserisci la postazione da prenotare");
                        try {
                            idPostazione = scanner.nextLong();
                            try {
                                Postazione postazione = postazioneService.findById(idPostazione);
                                if (prenotazioneService.giornoGiaPrenotatoDaPostazione(postazione, LocalDate.parse(date))) {
                                    System.out.println("Postazione già prenotata");
                                } else {
                                    Prenotazione prenotazione = new Prenotazione();
                                    prenotazione.setDataPrenotazione(LocalDate.parse(date));
                                    prenotazione.setPostazione(postazione);
                                    prenotazione.setUtente(utente);
                                    prenotazioneService.createPrenotazione(prenotazione);
                                    System.out.println("Prenotazione effettuata");
                                }
                            } catch (IllegalArgumentException e) {
                                log.error(e.getMessage());
                            }
                        }
                        catch (InputMismatchException e){
                            log.error("Devi inserire un numero!");
                        }
                        finally {
                            scanner.nextLine();
                        }
                    } else {
                        System.out.println("Hai già prenotato una postazione in questa data");
                    }
                    break;
                case 3:
                    System.out.println("Inserisci l'id della prenotazione da cancellare");
                    try {
                        idPrenotazione = scanner.nextLong();
                        try {
                            prenotazioneService.deleteById(idPrenotazione);
                            System.out.println("Prenotazione cancellata");
                        } catch (IllegalArgumentException e) {
                            log.error(e.getMessage());
                        }
                    }
                    catch (InputMismatchException e){
                        log.error("Devi inserire un numero!");
                    }
                    finally {
                        scanner.nextLine();
                    }

                    break;
                case 4:
                    System.out.println("inserisci il tuo username");
                    try {
                        username = scanner.nextLine();
                        utente = utenteService.findByUsername(username);
                    } catch (IllegalArgumentException e) {
                        log.error(e.getMessage());
                        System.out.println("proseguirai con lo stesso utente inserito in precedenza");
                    }
                    break;
                    case 5:
                        System.out.println("Lista prenotazioni: ");
                        prenotazioneService.printPrenotazioni(prenotazioneService.findAll());
                        break;
                        case 6:
                            System.out.println("Lista edifici: ");
                            edificioService.printEdifici(edificioService.findAllEdifici());
                            break;
                case 7:
                    System.out.println("Lista utenti: ");
                    utenteService.printUtenti(utenteService.findAllUtenti());
                    break;
                case 8:
                    System.out.println("Lista postazioni: ");
                    postazioneService.printPostazioni(postazioneService.findAllPostazioni());
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
