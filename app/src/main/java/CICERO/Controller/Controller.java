package CICERO.Controller;

import CICERO.Model.CiceroneClass;
import CICERO.Model.Itinerario;
import CICERO.Model.PiattaformaClass;
import CICERO.Model.UtenteClass;
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

        int i;
        i = consoleView.stampaHome();
        // if i==-1 -> errore da gestire
        switch (i) {

            // login Utente
            case 1 : {
                // acquisisco le credenziali
                List<String> credenziali = consoleView.getCredenziali();
                // autentico l'Utente
                UtenteClass utente = logInUtente(credenziali.get(0), credenziali.get(1));

                // UC3 - Prenotazione
                int j = consoleView.stampaItinerari(piattaforma.getItinerari());
                if (j != -1)   // j == -1 --> Utente non vuole prenotare ma semplicemente visualizza l'itinerario
                    piattaforma.prenota(utente, j); // j -> numero itinerario
            }

            // login aziendale
            case 2 : {
                // acquisisco le credenziali
                String username = consoleView.getCredenziali().get(0);
                String password = consoleView.getCredenziali().get(1);
                // autentico il Cicerone (profilo aziendale)
                CiceroneClass cicerone = logInCicerone(username, password);
                // UC2 - Aggiungi proposta di itinerario
                Itinerario itinerario = consoleView.getItinerario(cicerone);
                piattaforma.aggiungiProposta(itinerario, cicerone);
            }

            // UC1 - Creazione profilo utente
            case 3 : {
                ArrayList<String> datiUtente;
                do {
                    datiUtente = consoleView.creazioneProfiloUtente();
                }
                while (datiUtente == null || dbManager.utenteEsiste(datiUtente.get(0))) ;
                dbManager.inserisciNuovoUtente(datiUtente);
                System.exit(0);
                // utente appena creato dovra' fare il login
            }

            // termina programma Cicero
            case 0 :
                System.exit(0);


            default : throw new IllegalStateException("Carattere inserito non valido: " + i + "\n\n");
        }

    }

    private CiceroneClass logInCicerone(String username, String password) {
        //todo implementare
        return null;
    }


    /**
     * Se l'Utente esiste, ovvero &egrave; presente nel DB, esegui l'accesso
     * e restituisce l'oggetto UtenteClass
     *
     * @param email nome utente di Utente
     * @param password password di Utente
     * @return profilo Utente
     * @throws IllegalArgumentException se email e password non esistono nel DB
     */
    private UtenteClass logInUtente(String email, String password) throws SQLException {

        // Chiedi al db se email e password utente esistono
        UtenteClass utente = dbManager.estraiUtente(email, password);

        // se presenti nel DB allora l'autentico todo da migliorare se Utente sbaglia
        while (utente == null) {
            System.out.println("Username o password sbagliati");    // todo soprattutto questo :(
            List<String> app = consoleView.getCredenziali();
            utente = logInUtente(app.get(0), app.get(1));
        }

        // return UtenteClass se esiste, errore se non esiste  todo orientarsi con vpp
        return utente;
    }


}
