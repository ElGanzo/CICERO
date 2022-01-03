package CICERO.Model;

import java.util.ArrayList;

public class PiattaformaClass implements Piattaforma {

    public ArrayList<Itinerario> itinerari;
    public ArrayList<Tag> tags;
    public ArrayList<AreaGeografica> areeGeografiche;
    private ArrayList<Utente> utenti;
    private ArrayList<Cicerone> ciceroni;
    private Amministrazione amministrazione;

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
        return false;
    }

    @Override
    public boolean aggiungiProfiloCicerone(Cicerone cicerone) {
        return false;
    }

    @Override
    public boolean aggiungiProfiloUtente(Utente utente) {
        objectIsNull(utente);
        if (this.utenti.contains(utente))
            return false;
        this.utenti.add(utente);
        return true;
    }

    @Override
    public boolean prenotabilita(Itinerario itinerario) {
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


    public void objectIsNull(Object o) {
        if (o == null)
            throw new NullPointerException();
    }
}
