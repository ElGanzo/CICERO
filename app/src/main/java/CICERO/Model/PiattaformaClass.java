package CICERO.Model;

import java.util.ArrayList;

public class PiattaformaClass implements Piattaforma {

    private Amministrazione amministrazione;
    public ArrayList<Itinerario> itinerari;
    public ArrayList<Tag> tags;
    public ArrayList<AreaGeografica> areeGeografiche;
    private ArrayList<Utente> utenti;
    private ArrayList<Cicerone> ciceroni;

    public PiattaformaClass() {
        this.itinerari = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.areeGeografiche = new ArrayList<>();
        this.utenti = new ArrayList<>();
        this.ciceroni = new ArrayList<>();
        this.amministrazione = new AmministrazioneClass();
    }

    @Override
    public <T> boolean aggiungiProposta(T proposta, Cicerone cicerone) {
        controlloNull(proposta, "proposta da inserire non valido");
        controlloNull(cicerone, "cicerone da inserire non valido");
        if(!contiene(proposta))
            throw new IllegalArgumentException("proposta da inserire presente nella Piattaforma");
        if(amministrazione.approvaProposta(cicerone, proposta))
        {
            if(proposta instanceof Tag)
                tags.add((Tag) proposta);
            if(proposta instanceof AreaGeografica)
                areeGeografiche.add((AreaGeografica) proposta);
            if(proposta instanceof Itinerario)
                itinerari.add((Itinerario) proposta);
            return true;
        }
        return false;
    }

    private <T> boolean contiene(T proposta) {
        if(proposta instanceof Tag)
            return tags.contains((TagClass) proposta);
        if(proposta instanceof AreaGeografica)
            return areeGeografiche.contains((AreaGeograficaClass) proposta);
        if(proposta instanceof Itinerario)
            return itinerari.contains((ItinerarioClass) proposta);
        return true;
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
    public boolean aggiungiProfiloUtente(Utente utente) {
        controlloNull(utente, "utente da inserire non valido");
        if (this.utenti.contains(utente))
            return false;
        this.utenti.add(utente);    // si garantisce che l'utente, nel costruttore, abbia dei campi validi
        return true;
    }

    @Override
    public boolean prenotabilita(Itinerario itinerario) {
        controlloNull(itinerario, "itinerario da inserire non valido");
        //TODO: controllare nel catalogo degli itinerari disponibili
        // oppure fornisco l'orario e l'utente sceglierà quello
        return false;
    }

    @Override
    public boolean accessoUtente(Utente utente, ServizioEsterno servizio) {
        return false;
    }

    @Override
    public boolean accessoCicerone(Cicerone cicerone) {
        return false;
    }

    public ArrayList<Itinerario> getItinerari() {
        return itinerari;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public ArrayList<AreaGeografica> getAreeGeografiche() {
        return areeGeografiche;
    }

    public ArrayList<Utente> getUtenti() {
        return utenti;
    }

    public ArrayList<Cicerone> getCiceroni() {
        return ciceroni;
    }

    public static void controlloNull(Object o, String errorMessage) {
        if (o == null)
            throw new NullPointerException(errorMessage);
    }
}
