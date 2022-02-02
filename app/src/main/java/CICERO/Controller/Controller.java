package CICERO.Controller;

import CICERO.Model.*;
import CICERO.View.ConsoleView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    PiattaformaClass piattaforma;
    DBManager dbManager;
    ConsoleView consoleView = new ConsoleView();

    public Controller() {
        this.piattaforma = new PiattaformaClass();
    }

//    if amministrazione.approvaProposta(odsajfnoadsf, cicerone){
//        piattaforma.aggiungiProposta(odsajfnoadsf, cicerone)
//    }

    public void executeProgram() throws Exception {
        // effettua la connessione al DB
        dbManager = new DBManager("jdbc:mysql://104.248.18.55:3306/TogepiDB", "Mikez", "TogepiMikez");
        inizializzaPiattaforma();
        int i = 1;

        while(i!=0) {

            i = consoleView.stampaHome();

            switch (i) {

                // login Utente ( UC17 )
                case 1: {

                    // acquisisco le credenziali
                    List<String> credenziali = consoleView.getCredenziali();
                    // autentico l'Utente
                    UtenteClass utente = logInUtente(credenziali.get(0), credenziali.get(1));
                    consoleView.stampaItinerari(piattaforma.getItinerari());

                    // UC3 - Prenotazione todo da controllare nMin/nMax partecipanti
                    int itinerarioSelezionato = consoleView.prenotaItinerario(piattaforma.getItinerari());
                    // j == -1 --> Utente non vuole prenotare ma semplicemente visualizza l'itinerario
                    if (itinerarioSelezionato != -1)
                        prenotazione(utente, itinerarioSelezionato);
                    break;
                }

                // login aziendale ( UC16 )
                case 2: {

                    // acquisisco le credenziali
                    List<String> credenziali = consoleView.getCredenziali();

                    // autentico il Cicerone (profilo aziendale)
                    CiceroneClass cicerone = logInCicerone(credenziali.get(0), credenziali.get(1));

                    // UC2 - Aggiungi proposta di itinerario todo da testare sembra OK
                    Itinerario itinerario = consoleView.getItinerario(cicerone, piattaforma.getTag(), piattaforma.getLuoghi());
                    if(itinerario == null)
                        break;
                    piattaforma.inserisciItinerario(itinerario);
                    dbManager.inserisciItinerario(itinerario);
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
                    System.exit(0);

                default:
                    throw new IllegalStateException("Carattere inserito non valido: " + i + "\n\n");
            }
        }
        consoleView.chiudiScanner();
    }

    private void prenotazione(UtenteClass utente, int itinerarioSelezionato) {
        Itinerario itinerario = piattaforma.getItinerari().get(itinerarioSelezionato);
        // todo in futuro UC8 - Interazione con invito --> low risk UC
        ArrayList<InvitatoClass> invitati = new ArrayList<>();
        invitati = consoleView.richiediInvitati(itinerario.getMinPartecipanti(), itinerario.getMaxPartecipanti());
        piattaforma.prenota(utente, itinerario, invitati); // j -> numero itinerario
        dbManager.inserisciPrenotazione();  // todo rivedere
    }

    /**
     * Estrae dal DB tutti gli itinerari, tutti i tags e tutti i luoghi per inserirli in Piattaforma
     */
    private void inizializzaPiattaforma() throws SQLException {
        for (TagClass tag: dbManager.estraiTag()){
            piattaforma.inserisciTag(tag);
        }
        for (Luogo luogo: dbManager.estraiLuoghi()){
            piattaforma.inserisciLuogo(luogo);
        }
        for (ItinerarioClass itinerario: dbManager.estraiItinerari()){
            piattaforma.inserisciItinerario(itinerario);
        }
    }

    /**
     * Esegue l'accesso di <code>CiceroneClass</code> nella <code>PiattaformaClass</code>
     * @param email email del Cicerone
     * @param password password del Cicerone
     * @return <code>CiceroneClass</code> se presente nel DB, altrimenti richiede di nuovo le credenziali
     */
    private CiceroneClass logInCicerone(String email, String password) throws SQLException {

        // Chiedi al db se email e password utente esistono
        CiceroneClass cicerone = dbManager.estraiCicerone(email, password);

        // se presenti nel DB allora l'autentico
        while (cicerone == null) {
            System.out.println("Username o password sbagliati, profilo aziendale non presente nel DB");
            List<String> app = consoleView.getCredenziali();
            cicerone = logInCicerone(app.get(0), app.get(1));
        }

        // ... e restituisco il cicerone, come conferma che
        return cicerone;
    }

    /**
     * Se l'Utente esiste, ovvero &egrave; presente nel DB, esegui l'accesso
     * e restituisce l'oggetto UtenteClass
     *
     * @param email nome utente di Utente
     * @param password password di Utente
     * @return profilo Utente
     */
    private UtenteClass logInUtente(String email, String password) throws SQLException {

        // Chiedi al db se email e password utente esistono
        UtenteClass utente = dbManager.estraiUtente(email, password);

        // se presenti nel DB allora l'autentico todo da migliorare se Utente sbaglia
        while (utente == null) {
            System.out.println("Username o password sbagliati");
            List<String> app = consoleView.getCredenziali();
            utente = logInUtente(app.get(0), app.get(1));
        }

        // return UtenteClass se esiste, errore se non esiste
        return utente;
    }
}
