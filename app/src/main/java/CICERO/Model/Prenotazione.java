package CICERO.Model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Rappresenta una prenotazione da parte di un Utente ad un itinerario.
 */
public class Prenotazione {

    public Itinerario itinerario;
    public Persona utente;
    public ArrayList<InvitatoClass> invitati;
    // i seguenti servono solamente in formato String, non serve veramente l'oggetto
    // Date o Time
    public Date dataInizio;
    public Time orarioInizio;
    public Date dataScadenzaPrenotazione;
    public Date dataScadenzaPagamento;

    /**
     * Rappresenta una prenotazione da parte di un Utente ad un itinerario.
     * 
     * @param itinerario l'itinerario che viene prenotato dall'utente.
     * @param utente     l'utente che effettua la prenotazione.
     * @param invitati   l'ArrayList di invitati dall'utente.
     */
    public Prenotazione(Itinerario itinerario, Persona utente, ArrayList<InvitatoClass> invitati) {
        PiattaformaClass.controlloNull(itinerario, "Prenotazione senza itinerario non valida");
        PiattaformaClass.controlloNull(utente, "Prenotazione senza utente non valida");
        this.itinerario = itinerario;
        this.utente = utente;
        // non c'e' controlloNull perche' numMinPartecipanti di Itinerario potrebbe
        // essere 1, invitati puo' essere null
        this.invitati = invitati;
    }

    /**
     * Restituisce l'orario di inizio dell'itinerario prenotato.
     * 
     * @return l'orario di inizio dell'itinerario prenotato.
     */
    public Time getOrarioInizio() {
        return this.orarioInizio;
    }

    /**
     * Restituisce la data in cui si tiene l'itinerario prenotato.
     * 
     * @return la data in cui si tiene l'itinerario prenotato.
     */
    public String getData() {
        return this.dataInizio.toString();
    }

    /**
     * Restituisce la data di scadenza della prenotazione entro la quale un numero
     * di partecipanti non inferiore al minimo deve aver accettato l'invito. Una
     * volta scaduta la prenotazione non &egrave; pi&ugrave; possibile accettare
     * l'invito e, se il numero minimo di partecipanti non &egrave; stata raggiunto
     * decade la prenotazione.
     * 
     * @return la data di scadenza della prenotazione.
     */
    public Date getDataScadenzaPrenotazione() {
        return this.dataScadenzaPrenotazione;
    }

    /**
     * Restituisce la data di scadenza entro la quale bisogna effettuare il
     * pagamento per la prenotazione. Una volta scaduta decade la prenotazione.
     * 
     * @return la data di scadenza entro la quale bisogna effettuare il pagamento
     *         per la prenotazione.
     */
    public Date getDataScadenzaPagamento() {
        return this.dataScadenzaPagamento;
    }

    /**
     * Restituisce il numero di partecipanti (che essi abbiano accettato o meno).
     * 
     * @return il numero di partecipanti (che essi abbiano accettato o meno).
     */
    public int getNumPartecipanti() {
        return this.invitati.size() + 1;
    }

    /**
     * Restituisce l'itinerario prenotato.
     * 
     * @return l'itinerario prenotato.
     */
    public Itinerario getItinerario() {
        return this.itinerario;
    }

    /**
     * Restituisce il cicerone che si occupa dell'itinerario prenotato.
     * 
     * @return il cicerone che si occupa dell'itinerario prenotato.
     */
    public Cicerone getCicerone() {
        return this.itinerario.getCicerone();
    }

    /**
     * Restituisce l'utente che ha effettuato la prenotazione.
     * 
     * @return l'utente che ha effettuato la prenotazione.
     */
    public Persona getUtente() {
        return this.utente;
    }

    // public ArrayList<String> getEmailInvitati() {
    // ArrayList<String> emails = new ArrayList<>();
    // for (InvitatoClass invitato: invitati){
    // emails.add(invitato.getEmail());
    // }
    // return emails;
    // } todo sviluppare in futuro

    // todo aggiungere eventuali metodi setter

    // TODO aggiungi un setDecaduta() che pone la prenotazione come decaduta quando:
    // (1) la data di scadenza del pagamento è superata 
    // (2) il numero min di partecipanti non è stato raggiunto 
    // (3) l'itinerario è già stato fatto

}
