package CICERO.Controller;

import CICERO.Model.Cicerone;
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

    public Controller() throws SQLException {
        this.piattaforma = new PiattaformaClass();
    }

//    amministrazione.inserisciProposta(odsajfnoadsf){
//        piattaforma.aggiungiProposta(odsajfnoadsf, master)
//    }

    public void executeProgram() throws SQLException {
        inizializzaConnessioneDatabase();
        int i = consoleView.stampaHome();
        // if i==-1 -> errore da gestire
        switch (i) {
            // login Utente
            case 1 -> {
                String username = consoleView.getCredenziali().get(0);
                String password = consoleView.getCredenziali().get(1);
                UtenteClass utente = logInUtente(username, password);
                int j = consoleView.stampaItinerari(piattaforma.getItinerari());
                // se -1 -> Utente non prenota ed termina Cicero
                if(j!=-1)
                    piattaforma.prenota(utente, j); // j -> numero itinerario
            }

            // login aziendale
            case 2 -> {
                String username = consoleView.getCredenziali().get(0);
                String password = consoleView.getCredenziali().get(1);
                CiceroneClass cicerone = logInCicerone(username, password);
                piattaforma.logInAziendale();
                effettuaProposta(cicerone);
            }

            // termina programma Cicero
            case 0 -> {
            }

            default -> throw new IllegalStateException("Carattere inserito non valido: " + i +"\n\n");
        }
    }

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
        dbManager = new DBManager(url, username, password);
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

        // todo mostra prenotazione (high risk UC)
        return null;
    }


}
