package CICERO.View;
import CICERO.Model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private static Scanner scanner;

    public ConsoleView(){
        scanner = new Scanner(System.in);
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
        int i = Integer.parseInt(s);
        return i;
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
        List<String> credenziali = new ArrayList<>();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        PiattaformaClass.controlloNull(email, "Email non valida");
        credenziali.add(0, email);
        System.out.print("\nPassword: ");
        String password = scanner.nextLine();
        PiattaformaClass.controlloNull(password, "Password non valida");
        credenziali.add(1, password);
        return credenziali;
    }

    /**
     * Richiede all'utente tutti i dati necessari per la creazione di un Profilo Utente
     * @return <code>Utente</code> se i dati vengono confermati, <code>null</code> altrimenti
     */
    public UtenteClass creazioneProfiloUtente() throws ParseException {
        ArrayList<String> datiUtente = new ArrayList<>();
        String s;
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
        System.out.println("Data di nascita (AAAA/MM/GG): ");
        Date dataNascita= new SimpleDateFormat("yyyy/MM/dd").parse(scanner.nextLine());
        System.out.println("Email: ");
        datiUtente.add(2, scanner.nextLine());
        System.out.println("Password: ");
        datiUtente.add(3, scanner.nextLine());


        System.out.println(datiUtente +", " +dataNascita);
        System.out.println("Confermare questi dati? [Y] per confermare, qualsiasi altro tasto per annullare...");
        s = scanner.nextLine();
        s = checkSingleCharacter(s, "Y", "N", "0", "y", "n");
        if(s.equals("Y") || s.equals("y")){
            return new UtenteClass(datiUtente.get(0),datiUtente.get(1), dataNascita, datiUtente.get(2), datiUtente.get(3));
        }
        System.out.println("Creazione profilo Utente annullata.");
        return null;
    }

    /**
     * Visualizza gli itinerari presenti sulla Piattaforma
     * @param itinerari itinerari presenti sulla Piattaforma
     */
    public void stampaItinerari(ArrayList<Itinerario> itinerari) {
        System.out.println("Itinerari: \n");
        int i = 1;
        for (Itinerario itinerario: itinerari){
            System.out.println("["+i +"] -> " +itinerario.toString());
        }
    }

    /**
     * offre all'Utente la possibilit&agrave; di prenotare un itinerari tra quelli presenti nella Piattaforma
     * @param itinerari presenti nella Piattaforma
     * @return numero dell'itinerario selezionato, 0 se non si vuole effettuare una prenotazione
     */
    public int prenotaItinerario(ArrayList<Itinerario> itinerari){

        // proponi prenotazione
        System.out.println("Seleziona un itinerario se vuoi prenotarlo, altrimenti premi [0] per tornare alla Home");

        String s = scanner.nextLine();
        int j = Integer.parseInt(s);

        // visualizza itinerario
        if( j<=itinerari.size() && j!=0 )
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
     * Chiede tutte le info riguardanti un itinerario al Cicerone.<br>
     *
     * Inizialmente si pu&ograve; assegnare all'itinerario proposto solo un luogo e un tag,
     * in una seconda fase verranno aggiunti ulteriori luoghi e/o tag se necessario
     *
     * @return <code>ItinerarioClass</code> da proporre
     */
    public Itinerario getItinerario(CiceroneClass cicerone, ArrayList<TagClass> listaTag, ArrayList<Luogo> luoghi) {
        System.out.println("    ---     Aggiunta di proposta di un nuovo itinerario     ---");
        ArrayList<String> datiItinerario = new ArrayList<>();

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
        System.out.println("\nAggiungi un tag all'itinerario, inserisci il nome del tag che vuoi aggiungere oppure" +
                "[ * ] per nessun tag");
        String s = scanner.nextLine();
        TagClass tagSelezionato = null;
        if(!s.equals("*")) {    // se Cicerone vuole aggiungere un tag
            TagClass app = new TagClass(s);
            app.setProposta(false); // se sta nella lista dei tag non e' una proposta ma e' valido
            // cerca il tag inserito dall'Utente all'interno dei tag disponibili
            tagSelezionato = (listaTag.get(listaTag.indexOf(app)));
            if(tagSelezionato == null)
                System.out.println("Tag inserito non presente, non verra' aggiunto nessun tag all'itinerario proposto");
        }

        // luoghi
        LuogoClass luogo = richiediLuogo();
        do{
            if(!luoghi.contains(luogo))
                System.out.println("Luogo inserito non trovato... Riprovare oppure premere 0");
            s = scanner.nextLine();
        }while(!luoghi.contains(luogo) || s.equals("0") || luogo == null);

        // durata
        System.out.println("Durata in ore dell'itinerario: ");
        double durata = scanner.nextDouble();

        System.out.println("Itinerario aggiunto alle proposte di itinerario, riepilogo: ");
        System.out.println(datiItinerario + tagSelezionato.toString() + luogo.getToponimo() +
                " durata in ore: "+durata);

        return new ItinerarioClass(cicerone, datiItinerario.get(0),
                Integer.parseInt(datiItinerario.get(1)), Integer.parseInt(datiItinerario.get(2)), datiItinerario.get(3),
                tagSelezionato, luogo, durata);
    }

    private LuogoClass richiediLuogo() {
        LuogoClass luogo;

            String regione;
            System.out.println("Regione in cui si svolgera' l'itinerario (ATTENZIONE: regione obbligatoria): ");
            regione = scanner.nextLine();
            if(regione == null) {
                System.out.println("Itinerario invalido senza luogo di svolgimento... Proposta di itinerario annullata ");
                return null;    // todo testare
            }
            System.out.println("\n[invio] -> ignora le provincia, citta' e luogo \n)");
            String provincia;
            System.out.println("Sigla della provincia in cui si svolgera' l'itinerario: ");
            provincia = scanner.nextLine();
            if(provincia != null){
                String citta;
                System.out.println("Citta in cui si svolgera' l'itinerario (invio per terminare) : ");
                citta = scanner.nextLine();
                if( citta != null){
                    String luogoString;
                    System.out.println("Luogo in cui si svolgera' l'itinerario (invio per terminare) : ");
                    luogoString = scanner.nextLine();
                    luogo = new LuogoClass(luogoString, citta, provincia, regione);  // luogo ok anche se null
                }
                luogo = new LuogoClass(null, null, provincia, regione);
            }
            luogo = new LuogoClass(null, null, null, regione);
            // ricercare il luogo nella lista dei luoghi
        luogo.luogoAccettato();
        return luogo;
    }

    /**
     * scanner deve essere chiuso da <code>ConsoleView</code> perch&egrave; questa &egrave; la classe che si occupa delle stampe
     */
    public void chiudiScanner() {scanner.close();
    }

    /**
     * Chiede a Utente, che prenota un itinerario, la lista degli invitati
     * @param minPartecipanti minimo numero di persone che devono partecipare all'Itinerario
     * @param maxPartecipanti massimo numero di PersonaClass che possono partecipare all'Itinerario
     * @return lista degli invitati
     */
    public ArrayList<InvitatoClass> richiediInvitati(int minPartecipanti, int maxPartecipanti) throws ParseException {
        // info itinerario
        System.out.println("Numero minimo di partecipanti previsto per l'itinerario: " +minPartecipanti);
        System.out.println("Numero massimo di partecipanti previsto per l'itinerario: " +maxPartecipanti);
        System.out.println("Puoi invitare massimo altre " +(maxPartecipanti-1) + " persone e minimo altre "+(minPartecipanti-1));

        // se non si vuole invitare nessuno
        if(minPartecipanti==1){
            System.out.println("Premi [0] per non invitare nessuno, qualsiasi altro tasto per invitare altre persone");
            if(scanner.nextLine().contains("0"))
                return null;
        }

        // richiedi invitati
        ArrayList<InvitatoClass> invitati = new ArrayList<>();
        int i = 0;
        while(i<minPartecipanti || i<maxPartecipanti) {
            System.out.println("invitato numero: " + i);
            // proponi stop inviti
            if (i >= minPartecipanti){
                System.out.println("Premi [0] se non vuoi invitare nessun altro, qualsiasi altro tasto per continuare");
                if (scanner.nextLine().contains("0"))
                    break;
            }
            // acquisisci dati invitato
            ArrayList<String> datiInvitato = new ArrayList<>();
            System.out.print("Nome: ");
            datiInvitato.add(0,scanner.nextLine());
            System.out.print("Cognome: ");
            datiInvitato.add(1,scanner.nextLine());
            System.out.print("Email: ");
            datiInvitato.add(2,scanner.nextLine());
            System.out.print("Data di nascita (AAAA/MM/GG): ");
            Date dataNascita= new SimpleDateFormat("yyyy/MM/dd").parse(scanner.nextLine());
            InvitatoClass invitato = new InvitatoClass(datiInvitato.get(0),
                    datiInvitato.get(1), datiInvitato.get(2), dataNascita);
            invitati.add(invitato);
            i++;
        }
        return invitati;
    }

    /**
     * Messaggio di errore quando <code>Utente</code> o <code>Cicerone</code> fornisce credenziali non valide
     */
    public void stampaErroriCredenziali() {
        System.out.println("Email o password sbagliati...");
    }

    public void stampaArrivederci() {
        System.out.println("\nAlla prossima!");
    }
}
