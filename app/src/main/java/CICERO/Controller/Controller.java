package CICERO.Controller;

import CICERO.Model.AmministrazioneClass;
import CICERO.Model.PiattaformaClass;
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
        int i = consoleView.stampaMenuIniziale();
        switch (i){
            case 1:
                piattaforma.logInUtente();
                mostraHome();
                break;

            case 2:
                piattaforma.logInAziendale();
                mostraHome();
                break;

            case 3:
                String s =consoleView.richiediToponimo();
                dbManager.inserisciToponimo(s);
        }
    }

    public void mostraHome(){
        consoleView.stampaHome();
    }
}
