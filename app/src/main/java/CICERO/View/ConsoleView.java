package CICERO.View;
import CICERO.Model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Elimina le precedenti scritte sulla console, cos&igrave; da renderla pi&ugrave; leggibile
     */
    private void pulisciConsole() {
        for(int i = 0; i < 20; i++)
            System.out.print("\n");
    }

    /**
     * Stampa la <i>Home</i>, con la possibilit&agrave; di effettuare<br>
     *
     * [1] -> Log in Utente<br>
     * [2] -> Log in Aziendale<br>
     * [3] -> Crea profilo Utente<br>
     * [0] -> Termina programma Cicero<br>
     *<br>
     * Implementazione dei casi d'uso pi&ugrave; rischiosi:<br>
     * - Creazione profilo Utente<br>
     * - Aggiunta di proposta di itinerario<br>
     * - Prenotazione<br>
     * Inoltre, vengonono implementati alcuni UC low risk (UC16 & UC17) per far funzionare il sistema
     * @return scelta effettuata dall'Utente
     */
    public int stampaHome() {
        System.out.println("\nBenvenuto in Cicero!\n");
        System.out.println("Per effettuare le operazioni:");
        System.out.println("[1] -> Log in Utente");
        System.out.println("[2] -> Log in Aziendale");
        System.out.println("[3] -> Crea profilo Utente");
        System.out.println("[0] -> Termina programma Cicero");

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
     * @return Lista di <code>String</code> inserite dall'Utente o dal Cicerone (email, password)
     */
    public List<String> getCredenziali() {
        pulisciConsole();
        List<String> credenziali = new ArrayList<>();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        PiattaformaClass.controlloNull(email, "Email non valida");
        credenziali.add(0, email);
        System.out.print("\nPassword: ");
        String password = scanner.nextLine();
        PiattaformaClass.controlloNull(password, "Password");
        credenziali.add(1, password);
        return credenziali;
    }

    /**
     * Richiede all'utente tutti i dati necessari per la creazione di un Profilo Utente
     * @return <code>Utente</code> se i dati vengono confermati, <code>null</code> altrimenti
     */
    public UtenteClass creazioneProfiloUtente() {
        ArrayList<String> datiUtente = new ArrayList<>();
        String s = null;
        System.out.println("--- Creazione nuovo profilo utente --- ");
        System.out.println("Nome: ");
        s = scanner.nextLine();
        if(s == null)
            throw new NullPointerException("Nome null non valido");
        datiUtente.add(0, s);
        System.out.println("Cognome: ");
        s = scanner.nextLine();
        if(s==null)
            throw new NullPointerException("Cognome null non valido");
        datiUtente.add(1, s);
        System.out.println("Data di nascita (AAAA-MM-GG): ");
        datiUtente.add(2, scanner.nextLine());
        System.out.println("Email: ");
        datiUtente.add(3, scanner.nextLine());
        System.out.println("Password: ");
        datiUtente.add(4, scanner.nextLine());


        System.out.println(datiUtente.toString());
        System.out.println("Confermare questi dati? [Y] per confermare, qualsiasi altro tasto per annullare...");
        s = scanner.nextLine();
        s = checkSingleCharacter(s, "Y", "N", "0", "y", "n");
        if(s.equals("Y") || s.equals("y")){
            return new UtenteClass(datiUtente.get(0),datiUtente.get(1), datiUtente.get(2), datiUtente.get(3), datiUtente.get(4));
        }
        System.out.println("Creazione profilo Utente annullata.");
        return null;
    }

    /**
     * Visualizza gli itinerari presenti sulla Piattaforma
     * @param itinerari itinerari presenti sulla Piattaforma
     */
    public void stampaItinerari(ArrayList<Itinerario> itinerari) {
        pulisciConsole();
        System.out.println("Itinerari: \n");
        int i = 1;
        for (Itinerario itinerario: itinerari){
            System.out.println("["+i +"] -> " +itinerario.toString());
        }
    }

    /**
     * offre all'Utente la possibilit&agrave; di prenotare un itinerari tra quelli presenti nella Piattaforma
     * @param itinerari presenti nella Piattaforma
     * @return numero dell'itinerario selezionato
     */
    public int prenotaItinerario(ArrayList<Itinerario> itinerari){

        // proponi prenotazione
        System.out.println("Seleziona un itinerario se vuoi prenotarlo, altrimenti premi [0] per tornare alla Home");

        String s = scanner.nextLine();
        int j = Integer.parseInt(s);

        // visualizza itinerario
        if( j<=itinerari.size() && j==0 )
            System.out.println(itinerari.get(j-1).toString());
        else
            return -1;

        // prenotazione itinerario
        System.out.println("Prenotare l'itinerario? [Y]es / [N]o");
        s = scanner.nextLine();
        s = checkSingleCharacter(s, "Y", "N", "0", "y", "n");
        if(s.equals("Y") || s.equals("y"))
            return j;
        return -1;
    }

    /**
     * Chiede tutte le info riguardanti un itinerario al Cicerone
     * @return <code>ItinerarioClass</code> da proporre
     */
    public Itinerario getItinerario(CiceroneClass cicerone, ArrayList<TagClass> tags, ArrayList<Luogo> luoghi) {
        pulisciConsole();
        System.out.println("    ---     Aggiunta di proposta di un nuovo itinerario     ---");
        ArrayList<String> datiItinerario = new ArrayList<>();

        // prendiamo gli input per buoni
        // nome
        System.out.print("\nNome itinerario: ");
        datiItinerario.add(0, scanner.nextLine());

        // nmin & nmax
        System.out.print("\nNumero minimo di partecipanti: ");
        datiItinerario.add(1, scanner.nextLine());
        System.out.print("\nNumero massimo di partecipanti: ");
        datiItinerario.add(2, scanner.nextLine());

        // descrizione --> info
        System.out.print("\nDescrizione dell'itinerario: ");
        datiItinerario.add(3, scanner.nextLine());

        // tag
        System.out.println("\nAggiungi dei tag disponibili o proposta di nuovi tag");
        System.out.println("Tag disponibili:");
        int i = 1;
        for (TagClass tag: tags) {
            System.out.println("["+i+"]"+" -> "+ tag);
            i++;
        }
        System.out.println(" [ * ] -> Nessun tag da inserire");
        String s = scanner.nextLine();
        ArrayList<TagClass> tagSelezionato = new ArrayList<>();
        if(!s.equals("*"))
            if(s.equals("0"))   // in futuro potra' aggiungere altri tags dopo che l'itinerario e' stato approvato
                tagSelezionato.add(tags.get(Integer.parseInt(s)));

        // luoghi
        System.out.println("Aggiungi dei luoghi contrassegnati con dei toponimi: ");
        int j = 1;
        for (Luogo luogo: luoghi){
            System.out.println("["+j+"] -> "+luogo.getToponimo());
            j++;
        }
        // consiglia a Cicerone la proposta di Luogo
        System.out.println("[ * ] -> toponimo non disponibile tra quelli mostrati --- ATTENZIONE: annulla l'aggiunta" +
                " di proposta di nuovo itinerario: l'itinerario necessita di un luogo di svolgimento (altrimenti prova " +
                "'Aggiungi proposta di area geografica' da menu' iniziale)");
            // se non trova il luogo interessato
        String luogoSelezionato = scanner.nextLine();
        if(luogoSelezionato.equals("*"))
            return null;
        ArrayList<Luogo> luogo = new ArrayList<>();
        luogo.add(luoghi.get(Integer.parseInt(luogoSelezionato)));

        // durata
        System.out.println("Durata in ore dell'itinerario: ");
        double durata = scanner.nextDouble();

        System.out.println("Itinerario aggiunto alle proposte di itinerario, riepilogo: ");
        System.out.println(datiItinerario + tagSelezionato.get(0).toString() + luogo.get(0).getToponimo() +
                " durata in ore: "+durata);

        return new ItinerarioClass(cicerone, datiItinerario.get(0),
                Integer.parseInt(datiItinerario.get(1)), Integer.parseInt(datiItinerario.get(2)), datiItinerario.get(3),
                tagSelezionato, luogo, durata);
    }

    /**
     * scanner, essendo privato, deve essere chiuso da <code>ConsoleView</code>
     */
    public void chiudiScanner() {scanner.close();
    }
}
