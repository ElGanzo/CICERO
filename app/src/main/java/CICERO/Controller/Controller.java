package CICERO.Controller;

import CICERO.Model.CiceroneClass;
import CICERO.Model.PiattaformaClass;
import CICERO.Model.UtenteClass;
import CICERO.View.ConsoleView;

import java.sql.SQLException;
import java.util.List;

public class Controller{

    PiattaformaClass piattaforma;
    DBManager dbManager;
    ConsoleView consoleView = new ConsoleView();

    public Controller() {
        this.piattaforma = new PiattaformaClass();
    }

//    if amministrazione.approvaProposta(odsajfnoadsf, cicerone){
//        piattaforma.aggiungiProposta(odsajfnoadsf, cicerone)
//    }

    public void executeProgram() throws SQLException {
        inizializzaConnessioneDatabase();   // todo qui 3° errore
        int i = consoleView.stampaHome();
        // if i==-1 -> errore da gestire
        switch (i) {

            // login Utente
            case 1 -> {
                // acquisisco le credenziali
                List<String> credenziali = consoleView.getCredenziali();
                // autentico l'Utente
                UtenteClass utente = logInUtente(credenziali.get(0), credenziali.get(1));
                // ... dovrei stampare la SUA home (storico itinerari ecc...) todo <-- forse troppo avanzato, discuterne cancellare
                int j = consoleView.stampaItinerari(piattaforma.getItinerari());
                if(j!=-1)   // j == -1 --> Utente non vuole prenotare ma semplicemente visualizza l'itinerario
                    piattaforma.prenota(utente, j); // j -> numero itinerario
            }

            // login aziendale
            case 2 -> {
                // acquisisco le credenziali
                String username = consoleView.getCredenziali().get(0);
                String password = consoleView.getCredenziali().get(1);
                // autentico il Cicerone (profilo aziendale)
                CiceroneClass cicerone = logInCicerone(username, password);
                //piattaforma.logInAziendale();   // idem, dovrei stampare la SUA home ma ...
                effettuaProposta(cicerone);
            }

            // termina programma Cicero
            case 0 -> {
            }

            default -> throw new IllegalStateException("Carattere inserito non valido: " + i +"\n\n");
        }
    }

    /**
     * Richiesta di approvazione proposta ad <code>AmministrazioneClass</code>,
     * se viene approvata, verr&agrave; inserita nella lista delle proposte su <code>PiattaformaClass</code>
     *
     * @param cicerone cicerone che effettua la proposta
     * @param <T> tipo della proposta da proporre e, in caso, inserire
     */
    private <T> void effettuaProposta(CiceroneClass cicerone) {
        T proposta = consoleView.richiediProposta(cicerone);
        if(piattaforma.getAmministrazione().approvaProposta(proposta, cicerone))
            piattaforma.aggiungiProposta(proposta, cicerone);
    }

    private CiceroneClass logInCicerone(String username, String password) {
        //todo implementare
        return null;
    }

    private void inizializzaConnessioneDatabase() throws SQLException {
        List<String> infoConnessione;
        infoConnessione = consoleView.getInfoConnessione();

        String url = infoConnessione.get(0);
        String username = infoConnessione.get(1);
        String password = infoConnessione.get(2);
        System.out.println(infoConnessione.toString());
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
        dbManager = new DBManager(url, username, password); // todo qui 2° errore, come controllare/gestire eccezioni?
    }


    /**
     * Se l'Utente esiste, ovvero &egrave; presente nel DB, esegui l'accesso
     * e restituisce l'oggetto (?)
     * @param username nome utente di Utente
     * @param password password di Utente
     * @return
     */
    private UtenteClass logInUtente(String username, String password) {
        // Estrai dal db username e password utente
//        dbManager.
        // poi confrontali con i dati inseriti

        // return errore se non esiste  todo orientarsi con vpp
        return null;
    }


}
