package CICERO.Controller;

import CICERO.Model.PiattaformaClass;
import CICERO.Model.UtenteClass;
import CICERO.View.ConsoleView;

import java.sql.SQLException;

public class Controller{

    PiattaformaClass piattaforma;
    DBManager dbManager = new DBManager();
    ConsoleView consoleView = new ConsoleView();

    public Controller() throws SQLException {
        this.piattaforma = new PiattaformaClass();
    }

//    amministrazione.inserisciProposta(odsajfnoadsf){
//        piattaforma.aggiungiProposta(odsajfnoadsf, master)
//    }

    public void executeProgram(){
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
                break;
            }

            // login aziendale
            case 2 -> {
//                piattaforma.logInAziendale(); todo implementare

            }

            // termina programma Cicero
            case 0 -> {
                break;
            }

            default -> throw new IllegalStateException("Carattere inserito non valido: " + i +"\n\n");
        }
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

        // poi confrontali con i dati inseriti

        // todo mostra prenotazione (high risk UC)
        return null;
    }


}
