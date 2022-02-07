package CICERO.Controller;

import CICERO.Model.*;
import CICERO.View.ConsoleView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per collegare Model e View. Gestisce la connessione al database
 * attraverso il DBManager.
 */
public class Controller {

    PiattaformaClass piattaforma;
    DBManager dbManager;
    ConsoleView consoleView = new ConsoleView();

    /**
     * Classe per collegare Model e View. Gestisce la connessione al database
     * attraverso il DBManager.
     */
    public Controller() {
        this.piattaforma = new PiattaformaClass();
    }

    // if amministrazione.approvaProposta(odsajfnoadsf, cicerone){
    // piattaforma.aggiungiProposta(odsajfnoadsf, cicerone)
    // }

    /**
     * Esegue il programma Cicero.
     * 
     * @throws Exception se viene scelta un'opzione non contemplata.
     */
    public void executeProgram() throws Exception {
        // effettua la connessione al DB
        dbManager = new DBManager("jdbc:mysql://104.248.18.55:3306/TogepiDB", "Mikez", "TogepiMikez");
        inizializzaPiattaforma();
        int i;

        for (Itinerario itinerario: piattaforma.getItinerari()){
            System.out.println("\n");
            itinerario.toString();
        }
        do {

            i = consoleView.stampaHome();

            switch (i) {

                // login Utente ( UC17 )
                case 1: {

                    // acquisisco le credenziali
                    List<String> credenziali = consoleView.getCredenziali();
                    // autentico l'Utente
                    UtenteClass utente = dbManager.estraiUtente(credenziali.get(0), credenziali.get(1));
                    if (utente == null) {
                        consoleView.stampaErroriCredenziali();
                        break;
                    }
                    consoleView.stampaItinerari(piattaforma.getItinerari());

                    // UC3 - Prenotazione todo da controllare nMin/nMax partecipanti
                    int itinerarioSelezionato = consoleView.prenotaItinerario(piattaforma.getItinerari());
                    // j == -1 --> Utente non vuole prenotare ma semplicemente visualizza
                    // l'itinerario
                    if (itinerarioSelezionato != -1)
                        prenotazione(utente, itinerarioSelezionato);
                    break;
                }

                // login aziendale ( UC16 )
                case 2: {

                    // acquisisco le credenziali
                    List<String> credenziali = consoleView.getCredenziali();

                    // autentico il Cicerone (profilo aziendale)
                    CiceroneClass cicerone = dbManager.estraiCicerone(credenziali.get(0), credenziali.get(1));
                    if (cicerone == null) {
                        consoleView.stampaErroriCredenziali();
                        break;
                    }

                    // UC2 - Aggiungi proposta di itinerario todo da testare sembra OK
                    Itinerario itinerario = consoleView.getItinerario(cicerone, piattaforma.getTag(),
                            piattaforma.getLuoghi());
                    if (itinerario == null)
                        break;
                    piattaforma.inserisciItinerario(itinerario);
                    dbManager.inserisciItinerario(itinerario, cicerone);
                }

                // UC1 - Creazione profilo utente todo da testare, sembra OK
                case 3: {
                    UtenteClass utente;
                    utente = consoleView.creazioneProfiloUtente();
                    if (utente != null) {
                        piattaforma.aggiungiProfiloUtente(utente);
                        dbManager.inserisciNuovoUtente(utente); // utente appena creato potra' fare il login
                    }
                    break;
                }

                // termina programma Cicero
                case 0:
                    consoleView.stampaArrivederci();
                    consoleView.chiudiScanner();
                    System.exit(0);

                default:
                    throw new IllegalStateException("Carattere inserito non valido: " + i + "\n\n");
            }
        } while (i != 0);
        consoleView.chiudiScanner();
    }

    /**
     * Utente prenota un itinerario dopo aver raggiunto il numMinPartecipanti
     * 
     * @param utente                Utente che vuole prenotare un itinerario
     * @param itinerarioSelezionato numero stampato a video e selezionato
     *                              dall'Utente che indica un Itinerario
     * @throws NullPointerException se utente &egrave; <code>null</code>
     */
    private void prenotazione(UtenteClass utente, int itinerarioSelezionato) throws Exception {
        PiattaformaClass.controlloNull(utente, "Un utente inesistente non puo' prenotare...");
        Itinerario itinerario = piattaforma.getItinerari().get(itinerarioSelezionato);
        ArrayList<InvitatoClass> invitati;

        // richiesta invitati all'Itinerario ( obbligo di inserire nvitati se
        // numMinPartecipanti > 1 )
        invitati = consoleView.richiediInvitati(itinerario.getMinPartecipanti(), itinerario.getMaxPartecipanti());
        Prenotazione prenotazione = new Prenotazione(itinerario, utente, invitati);
        piattaforma.aggiungiPrenotazione(prenotazione);
        dbManager.inserisciPrenotazione(prenotazione);
    }

    /**
     * Estrae dal DB tutti gli itinerari, tutti i tags e tutti i luoghi per
     * inserirli in Piattaforma.
     * 
     * @throws SQLException se si verifica un errore di accesso al database.
     */
    private void inizializzaPiattaforma() throws SQLException {
        for (TagClass tag : dbManager.estraiTag()) {
            piattaforma.inserisciTag(tag);
        }
        for (Luogo luogo : dbManager.estraiLuoghi()) {
            piattaforma.inserisciLuogo(luogo);
        }
        for (ItinerarioClass itinerario : dbManager.estraiItinerari()) {
            piattaforma.inserisciItinerario(itinerario);
        }
    }
}
