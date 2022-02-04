package CICERO.Model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Rappresenta una prenotazione da parte di un Utente ad un itinerario, con tanto di eventuali Invitati
 */
public class Prenotazione {

    public Itinerario itinerario;
    public Persona utente;
    public ArrayList<InvitatoClass> invitati;
    //i seguenti servono solamente in formato String, non serve veramente l'oggetto Date o Time
    public Date dataInizio;
    public Time orarioInizio;
    public Date dataScadenzaPrenotazione;
    public Date dataScadenzaPagamento;

    public Prenotazione(Itinerario itinerario, Persona utente, ArrayList<InvitatoClass> invitati) {
        PiattaformaClass.controlloNull(itinerario, "Prenotazione senza itinerario non valida");
        PiattaformaClass.controlloNull(utente, "Prenotazione senza utente non valida");
        this.itinerario = itinerario;
        this.utente = utente;
        // non c'e' controlloNull perche' numMinPartecipanti di Itinerario potrebbe essere 1, invitati puo' essere null
        this.invitati = invitati;
    }

    public Time getOrarioInizio() {
        return this.orarioInizio;
    }

    public String getData() {
        return this.dataInizio.toString();
    }

    public Date getDataScadenzaPrenotazione() {
        return this.dataScadenzaPrenotazione;
    }

    public Date getDataScadenzaPagamento() {
        return this.dataScadenzaPagamento;
    }

    public int getNumPartecipanti() {
        return this.invitati.size() + 1;
    }

    public Itinerario getItinerario() {
        return this.itinerario;
    }

    public Cicerone getCicerone() {
        return this.itinerario.getCicerone();
    }

    public Persona getUtente() {
        return this.utente;
    }

//    public ArrayList<String> getEmailInvitati() {
//        ArrayList<String> emails = new ArrayList<>();
//        for (InvitatoClass invitato: invitati){
//            emails.add(invitato.getEmail());
//        }
//        return emails;
//    } todo sviluppare in futuro


    // todo aggiungere eventuali metodi setter

}
