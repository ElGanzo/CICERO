package CICERO.View;

import CICERO.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    /**
     * Elimina le precedenti scritte sulla console, cos&igrave; da renderla pi&ugrave; leggibile
     */
    private void pulisciConsole() {
        for(int i = 0; i < 20; i++)
            System.out.print("\n");
    }

    /**
     * Stampa la Home iniziale di benvenuto, con la possibilit&agrave; di effettuare
     * [1] -> Log in Utente
     * [2] -> Log in Aziendale
     * [3] -> Crea profilo Utente
     * [0] -> Termina programma Cicero
     * @return scelta effettuata dall'Utente
     */
    public int stampaHome() {
        System.out.println("\nBenvenuto in Cicero!\n");
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
     * @param values valori da controllare all'interno di s
     * @return stringa corretta se inserimento iniziale non andato a buon fine
     */
    private String checkSingleCharacter(String s, String ... values) {
        Scanner scanner = new Scanner(System.in);
        while(isNotValidCharacter(s, values)) {
            s = null;
            System.out.println("Carattere inserito non valido, ritenta oppure premi '0' per uscire ");
            s = scanner.nextLine();
        }
        return s;
    }

    /**
     * Controlla se la stringa inserita dall'Utente non contiene uno dei caratteri ammissibili
     *
     * @param s stringa su cui effettuare il controllo
     * @return <code>false</code> se il carattere inserito &egrave; corretto, <code>true</code> se il carattere &egrave; presente
     */
    private boolean isNotValidCharacter(String s, String ... values) {
        if(s.length()<1)
            return true;
        for (String x: values){
            if(s.contains(x))
                return false;
        }
        return true;
    }

    /**
     * Estrae le credenziali (nome, password) dalle stringhe inserite dall'Utente o dal Cicerone
     * @return Lista di stringhe inserite dall'Utente o dal Cicerone (0 -> email, 1 -> password)
     */
    public List<String> getCredenziali() {
        pulisciConsole();
        List<String> credenziali = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Email Utente: ");
        String username = scanner.nextLine();
        credenziali.add(0, username);
        System.out.print("\nPassword: ");
        String password = scanner.nextLine();
        credenziali.add(1, password);
        return credenziali;
    }

    public UtenteClass creazioneProfiloUtente() {
        ArrayList<String> datiUtente = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creazione nuovo profilo utente");
        System.out.println("Nome: ");
        datiUtente.add(0, scanner.nextLine());
        System.out.println("Cognome: ");
        datiUtente.add(1, scanner.nextLine());
        System.out.println("Data di nascita (AAAA/MM/GG): ");
        datiUtente.add(2, scanner.nextLine());
        System.out.println("Email: ");
        datiUtente.add(3, scanner.nextLine());
        System.out.println("Password: ");
        datiUtente.add(4, scanner.nextLine());


        System.out.println(datiUtente.toString());
        System.out.println("Confermare questi dati? [Y] per confermare, qualsiasi altro tasto per annullare...");
        String s = scanner.nextLine();
        s = checkSingleCharacter(s, "Y", "N", "0", "y", "n");
        if(s.equals("Y") || s.equals("y")){
            return new UtenteClass(datiUtente.get(0),datiUtente.get(1),datiUtente.get(2),datiUtente.get(3),datiUtente.get(4) )
        }
        return null;
    }

    /**
     * Visualizza gli itinerari presenti sulla Piattaforma offrendo la possibilit&agrave; di prenotarne uno
     * @param itinerari itinerari presenti sulla Piattaforma
     * @return idItinerario (numero) dell'Itinerario da prenotare
     */
    public int stampaItinerari(ArrayList<Itinerario> itinerari) {
        pulisciConsole();
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
        s = checkSingleCharacter(s, "Y", "N", "0", "y", "n");
        if(s.equals("Y") || s.equals("y"))
            return j;
        return -1;
    }

    public <T> T richiediProposta(CiceroneClass cicerone) {
        pulisciConsole();
        System.out.println("[1] -> aggiungi una proposta di itinerario");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        s = checkSingleCharacter(s, "1", "0");

        return null;
    }

    /**
     * Chiede tutte le info riguardanti un itinerario al Cicerone
     * @return <code>ItinerarioClass</code> da proporre
     */
    public Itinerario getItinerario(CiceroneClass cicerone, ArrayList<String> tags, ArrayList<Luogo> luoghi) {
        pulisciConsole();
        Scanner scanner = new Scanner(System.in);

        Itinerario itinerario = new ItinerarioClass(cicerone);

        // nome
        System.out.print("\nNome itinerario: ");
        itinerario.setNome(scanner.nextLine());
        // nmax & nmin
        System.out.print("\nNumero massimo di partecipanti: ");
        itinerario.setMaxPartecipanti(Integer.parseInt(scanner.nextLine()));    // prendiamo l'input per buono
        System.out.print("\nNumero minimo di partecipanti: ");
        itinerario.setMinPartecipanti(Integer.parseInt(scanner.nextLine()));    // prendiamo l'input per buono
        // descrizione
        System.out.println("Descrizione dell'itinerario: ");
        itinerario.setInfo(scanner.nextLine());
        // tag
        System.out.println("Aggiungi dei tag disponibili o proponi dei nuovi tag:");
        int i = 1;
        for (String tag: tags) {
            System.out.println();
        }
        // luoghi

        return itinerario;
    }
}
