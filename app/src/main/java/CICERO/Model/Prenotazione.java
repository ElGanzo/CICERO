package CICERO.Model;

import org.checkerframework.checker.units.qual.C;

import java.util.Date;

public class Prenotazione {
    //TODO rivedere, decidere funzionamento perche' potremmo comunque

    public Itinerario itinerario;
    public Cicerone cicerone;
    public Persona utente;
    public InvitatoClass[] invitato;
    public String emailInvitato; //(in alternativa a fare InvitatoClass)
    public Date data;

    public Prenotazione(Itinerario itinerario, Cicerone cicerone, Persona utente) {
        if (itinerario == null || utente == null || cicerone == null)
            throw new NullPointerException();
        this.itinerario = itinerario;
        this.cicerone = cicerone;
        this.utente = utente;
    }

    public Itinerario getItinerario() {
        return this.itinerario;
    }

    public Cicerone getCicerone() {
        return this.cicerone;
    }

    public Persona getUtente() {
        return this.utente;
    }

    public String getEmailInvitato() {
        return this.emailInvitato;
    }

    public Date getData() {
        return this.data;
    }

    //aggiungere eventuali metodi setter

}
