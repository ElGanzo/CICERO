package CICERO.Model;

import java.util.ArrayList;

public class PiattaformaClass implements Piattaforma {

    private final Amministrazione amministrazione;
    public ArrayList<Itinerario> itinerari;
    public ArrayList<String> tags;
    public ArrayList<AreaGeografica> areeGeografiche;
    private ArrayList<Persona> utenti;
    private ArrayList<Cicerone> ciceroni;
    private ArrayList<Prenotazione> prenotazioni;
    public static long IDInvitato = 0;
    public static long IDCiceroni = 0;
    public static int IDItinerario = 0;
    public static int IDAreaGeografica = 0;

    public PiattaformaClass() {
        this.itinerari = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.areeGeografiche = new ArrayList<>();
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
            if(proposta instanceof String)
                tags.add((String) proposta);
            if(proposta instanceof AreaGeografica)
                areeGeografiche.add((AreaGeografica) proposta);
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
        if(proposta instanceof String)
            return tags.contains(proposta);
        if(proposta instanceof AreaGeografica)
            return areeGeografiche.contains(proposta);
        if(proposta instanceof Itinerario)
            return itinerari.contains(proposta);
        return false;
    }

    @Override
    public boolean aggiungiProfiloCicerone(Cicerone cicerone) {
        controlloNull(cicerone, "cicerone da inserire non valido");
        if(this.ciceroni.contains(cicerone))
           throw new IllegalArgumentException("profilo cicerone attualmente presente nella Piattaforma");
        // se cicerone OK allora inserisci --> Come lo valutiamo????
        return false;
    }

    @Override
    public boolean aggiungiProfiloUtente(Persona utente) {
        controlloNull(utente, "utente da inserire non valido");
        if (this.utenti.contains(utente))
            return false;
        this.utenti.add(utente);    // si garantisce che l'utente, nel costruttore, abbia dei campi validi
        return true;
    }

    @Override //todo forse troppo low risk, facciamo che lo prenota e basta?
    public boolean prenotabilita(Itinerario itinerario) {
        controlloNull(itinerario, "itinerario nullo non valido per cercare la disponibilita'");
        if(!itinerari.contains(itinerario))
            return false;
        //TODO: fornisco l'orario e l'utente sceglierà quello --> forse troppo low risk UC
        return true;
    }

    @Override
    public boolean accessoUtente(Persona utente, ServizioEsterno servizio) {
        return false;
    }

    @Override
    public boolean accessoCicerone(Cicerone cicerone) {
        return false;
    }

    public ArrayList<Itinerario> getItinerari() {
        return itinerari;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public ArrayList<AreaGeografica> getAreeGeografiche() {
        return areeGeografiche;
    }

    public ArrayList<Persona> getUtenti() {
        return utenti;
    }

    public ArrayList<Cicerone> getCiceroni() {
        return ciceroni;
    }

    public Amministrazione getAmministrazione(){return amministrazione;}

    public static void controlloNull(Object o, String errorMessage) {
        if (o == null)
            throw new NullPointerException(errorMessage);
    }

    public void prenota(UtenteClass utente, int j) {
        controlloNull(utente, "utente mancante");
        // prenotabilita(itinerari.get(j)); todo forse troppo low risk
        prenotazioni.add(new Prenotazione(itinerari.get(j), itinerari.get(j).getCicerone(),  utente));

    }
}
