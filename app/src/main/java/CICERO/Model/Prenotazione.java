package CICERO.Model;

import java.util.ArrayList;

/**
 * Rappresenta una prenotazione da parte di un Utente ad un itinerario, con tanto di eventuali Invitati
 */
public class Prenotazione {

    public Itinerario itinerario;
    public Persona utente;
    public ArrayList<InvitatoClass> invitati;
    public String data;//todo sviluppare in futuro (?) anche scadenza ecc...

    public Prenotazione(Itinerario itinerario, Persona utente, ArrayList<InvitatoClass> invitati) {
        PiattaformaClass.controlloNull(itinerario, "Prenotazione senza itinerario non valida");
        PiattaformaClass.controlloNull(utente, "Prenotazione senza utente non valida");
        this.itinerario = itinerario;
        this.utente = utente;
        // non c'e' controlloNull perche' numMinPartecipanti di Itinerario potrebbe essere 1, invitati puo' essere null
        this.invitati = invitati;
    }

    public Itinerario getItinerario() {
        return this.itinerario;
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

    public String getData() {
        return this.data;
    }

    // todo aggiungere eventuali metodi setter

}
