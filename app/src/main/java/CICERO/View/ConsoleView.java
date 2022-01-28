package CICERO.View;

import CICERO.Model.CiceroneClass;
import CICERO.Model.Itinerario;
import CICERO.Model.PiattaformaClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

//    public String richiediToponimo(){
//        System.out.println("dammi il toponimo");
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextLine();
//    }

    /**
     * Stampa la Home iniziale di benvenuto, con la possibilit&agrave; di effettuare
     * [1] -> Log in Utente
     *
     * [2] -> Log in Aziendale
     *
     * [3] -> Crea profilo Utente
     *
     * [0] -> Termina programma Cicero
     *
     * @return scelta effettuata dall'Utente
     */
    public int stampaHome() {
        System.out.flush();
        System.out.println("Benvenuto in Cicero!\n");
        System.out.println("Per effettuare le operazioni:");
        System.out.println("[1] -> Log in Utente");
        System.out.println("[2] -> Log in Aziendale");
        System.out.println("[3] -> Crea profilo Utente");
        System.out.println("[0] -> Termina programma Cicero");

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        s = checkSingleCharacter(s, "0", "1", "2", "3");
            
        return Integer.parseInt(s);
    }

    /**
     * Controlla che il carattere inserito dall'Utente sia uno tra i caratteri specificati dalla precedente stampa
     * @param s stringa contenente il carattere
     * @return stringa corretta se inserimento iniziale non andato a buon fine
     */
    private String checkSingleCharacter(String s, String ... values) {
        Scanner scanner = new Scanner(System.in);
        while(s.length()>1 || isValidCharacter(s, values)) {
            s = null;
            System.out.println("Carattere inserito non valido, ritenta");
            s = scanner.nextLine();
        }
        return s;
    }

    /**
     * Controlla che la stringa inserita dall'Utente contenga uno dei caratteri richiesti per effettuare le operazioni
     *
     * @param s stringa su cui effettuare il controllo
     * @return false se la stringa non contiene il carattere, true se il carattere &egrave; presente
     */
    private boolean isValidCharacter(String s, String ... values) {
        for (String x: values){
            if(s.contains(x))
                return true;
        }
        return false;
    }

    /**
     * Estrae le credenziali (nome, password) dalle stringhe inserite dall'Utente
     * @return Lista di stringhe inserite dall'Utente (0 -> username, 1 -> password)
     */
    public List<String> getCredenziali() {
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        List<String> credenziali = new ArrayList<>();
        credenziali.add(0, username);
        System.out.print("\nPassword: ");
        String password = scanner.nextLine();
        credenziali.add(1, password);
        return credenziali;
    }

    /**
     * Visualizza gli itinerari presenti sulla Piattaforma offrendo la possibilit&agrave; di prenotarne uno
     * @param itinerari itinerari presenti sulla Piattaforma
     * @return idItinerario (numero) dell'Itinerario da prenotare
     */
    public int stampaItinerari(ArrayList<Itinerario> itinerari) {
        System.out.println("Itinerari: \n");
        int i = 1;
        for (Itinerario itinerario: itinerari){
            System.out.println("["+i +"] -> " +itinerario.toString());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleziona un itinerario: ");

        String s = scanner.nextLine();
        int j = Integer.parseInt(s);
        if(j <= itinerari.size() && j > 0 )
            // visualizza itinerario
            System.out.println(itinerari.get(j-1).toString());

        System.out.println("Prenotare l'itinerario? [Y]es / [N]o");
        s= scanner.nextLine();
        s = checkSingleCharacter(s, "Y", "N");
        if(s.equals("Y"))
            return j;
        return -1;
    }

    /**
     * Richiede le info della connessione al DB all'Utente, in ordine:
     * -url connessione DB
     * -username Utente
     * -password Utente
     * @return Lista di info per effettuare la connessione al DB
     */
    public List<String> getInfoConnessione() {
        Scanner scanner = new Scanner(System.in);
        List<String> app = new ArrayList<>();
        System.out.println("Indirizzo IP della connessione al DB: ");
        app.add(0, scanner.nextLine());
        PiattaformaClass.controlloNull(app.get(0), "indirizzo ip nullo non valido");
        System.out.println("username: ");
        app.add(1, scanner.nextLine());
        PiattaformaClass.controlloNull(app.get(1), "username nullo non valido");
        System.out.println("password: ");
        app.add(2, scanner.nextLine());
        PiattaformaClass.controlloNull(app.get(2), "password nulla non valido");
        return app;
    }

    public <T> T richiediProposta(CiceroneClass cicerone) {
        System.out.flush();
        System.out.println("[1] -> aggiungi una proposta di itinerario");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        s = checkSingleCharacter(s, "1");

        return null;
    }
}
