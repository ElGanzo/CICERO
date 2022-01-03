package CICERO.Model;

import org.checkerframework.checker.units.qual.C;

import java.util.Date;

public class Prenotazione {
    //TODO rivedere, decidere funzionamento

    public Itinerario itinerario;
    public Cicerone cicerone;
    public Utente utente;
//    public Invitato invitato;
    public String emailInvitato; //(in alternativa a fare InvitatoClass)
    public Date data;

    public void Prenotazione(Itinerario itinerario, Cicerone cicerone, Utente utente) {
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

    public Utente getUtente() {
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
