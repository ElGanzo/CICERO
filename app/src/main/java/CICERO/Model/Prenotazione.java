package CICERO.Model;

import java.util.ArrayList;

/**
 * Rappresenta una prenotazione da parte di un Utente ad un itinerario, guidato da un cicerone
 */
public class Prenotazione {
    //TODO rivedere

    public Itinerario itinerario;
    public Persona utente;
    public ArrayList<InvitatoClass> invitati;     //todo sviluppare in futuro
    public String data;

    public Prenotazione(Itinerario itinerario, Persona utente) {
        PiattaformaClass.controlloNull(itinerario, "Prenotazione senza itinerario non valida");
        PiattaformaClass.controlloNull(utente, "Prenotazione senza utente non valida");
        this.itinerario = itinerario;
        this.utente = utente;
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
