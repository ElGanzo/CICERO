package CICERO.Model;

import java.util.ArrayList;

public class PiattaformaClass implements Piattaforma {

    private final Amministrazione amministrazione;
    public ArrayList<Itinerario> itinerari;
    public ArrayList<TagClass> tag;
    public ArrayList<Luogo> luoghi;
    private ArrayList<Persona> utenti;
    private ArrayList<Cicerone> ciceroni;
    private ArrayList<Prenotazione> prenotazioni;
    public static long IDInvitato = 0;
    public static long IDCiceroni = 0;
    public static int IDLuogo = 0;

    public PiattaformaClass() {
        this.itinerari = new ArrayList<>();
        this.tag = new ArrayList<>();
        this.luoghi = new ArrayList<>();
        this.utenti = new ArrayList<>();
        this.ciceroni = new ArrayList<>();
        this.amministrazione = new AmministrazioneClass();
        this.prenotazioni = new ArrayList<>();
    }

    @Override
    public <T> boolean aggiungiProposta(T proposta, Cicerone cicerone) {
        controlloNull(proposta, "proposta da inserire non valida");
        controlloNull(cicerone, "cicerone che inserisce proposta non valido");
        if(!contiene(proposta))
            throw new IllegalArgumentException("proposta da inserire presente nella Piattaforma");
        if(amministrazione.approvaProposta(proposta, cicerone)){
            if(proposta instanceof TagClass)
                tag.add((TagClass) proposta);
            if(proposta instanceof Luogo)
                luoghi.add((Luogo) proposta);
            if(proposta instanceof Itinerario)
                itinerari.add((Itinerario) proposta);
            return true;
        }
        return false;
    }

    /**
     * Controlla che la proposta non sia gi&agrave; presente nella piattaforma
     * @param proposta proposta che s'intende inserire
     * @param <T> tipo della proposta che si vuole aggiungere
     * @return <code>true</code> se proposta gi&agrave; presente, <code>false</code> altrimenti
     */
    private <T> boolean contiene(T proposta) {
        if(proposta instanceof TagClass)
            return tag.contains(proposta);
        if(proposta instanceof Luogo)
            return luoghi.contains(proposta);
        if(proposta instanceof Itinerario)
            return itinerari.contains(proposta);
        return false;
    }

    @Override
    public boolean aggiungiProfiloCicerone(Cicerone cicerone) {
        controlloNull(cicerone, "cicerone da inserire non valido");
        if(this.ciceroni.contains(cicerone))
           throw new IllegalArgumentException("profilo cicerone attualmente presente nella Piattaforma");
        ciceroni.add(cicerone);
        return true;
    }

    @Override
    public boolean aggiungiProfiloUtente(Persona utente) {
        controlloNull(utente, "utente da inserire non valido");
        if (this.utenti.contains(utente))
            return false;
        this.utenti.add(utente);    // si garantisce che l'utente, nel costruttore, abbia dei campi validi
        return true;
    }

    @Override //troppo low risk -> non implementato
    public boolean prenotabilita(Itinerario itinerario) {
        controlloNull(itinerario, "itinerario nullo non valido per cercare la disponibilita'");
        return itinerari.contains(itinerario);
    }

    public ArrayList<Itinerario> getItinerari() {
        return itinerari;
    }

    public ArrayList<TagClass> getTag() {
        return tag;
    }

    public ArrayList<Luogo> getLuoghi() {
        return luoghi;
    }

    public ArrayList<Persona> getUtenti() {
        return utenti;
    }

    public ArrayList<Cicerone> getCiceroni() {
        return ciceroni;
    }

    public ArrayList<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public Amministrazione getAmministrazione(){return amministrazione;}

    /**
     * Metodo per controllare che un oggetto non sia <code>null</code>
     * Se lo &egrave;, lancia una <code>NullPointerException</code>
     * @param o oggetto da controllare
     * @param errorMessage messaggio di errore contenuto nell'eccezione
     * @throws NullPointerException se l'oggetto passato &egrave; <code>null</code>
     */
    public static void controlloNull(Object o, String errorMessage) {
        if (o == null)
            throw new NullPointerException(errorMessage);
    }

    /**
     * Inserisce una prenotazione per l'Utente all'itinerario specificato
     * @param utente utente che effettua la prenotazione
     * @param itinerario numero itinerario selezionato
     * @throws NullPointerException se itinerario specificato non presente
     */
    public void prenota(UtenteClass utente, int itinerario, int numeroInvitati) {   //todo controllare nMinPartecipanti raggiunto
        controlloNull(utente, "utente mancante");
        Itinerario itinerarioObject = itinerari.get(itinerario);
        if(prenotabilita(itinerarioObject)) //troppo low risk
            prenotazioni.add(new Prenotazione(itinerarioObject,  utente));
    }

    public void inserisciItinerario(Itinerario itinerario) {
        controlloNull(itinerario, "Itinerario da inserire nullo non valido");
        itinerari.add(itinerario);
    }
    public void inserisciTag(TagClass tag) {
        controlloNull(tag, "Itinerario da inserire nullo non valido");
        this.tag.add(tag);
    }
    public void inserisciLuogo(Luogo luogo) {
        controlloNull(luogo, "Itinerario da inserire nullo non valido");
        luoghi.add(luogo);
    }
}
