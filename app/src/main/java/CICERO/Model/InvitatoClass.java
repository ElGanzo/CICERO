package CICERO.Model;

import java.util.Date;

/**
 * Rappresenta una persona invitata a un certo itinerario. NB: <b>quest'oggetto
 * verr&agrave; distrutto una volta concluso l'itinerario.</b>
 */
public class InvitatoClass extends PersonaClass {
    // TODO: low risk use case, implementare in futuro
    private final long idInvitato;
    private boolean haAccettato;
    private long idItinerario; // todo sostituire con idPrenotazione (regolarsi con il DB)

    /**
     * Rappresenta una persona invitata a un certo itinerario.
     * 
     * @param nome        il nome dell'invitato.
     * @param cognome     il cognome dell'invitato.
     * @param email       l'email dell'invitato.
     * @param dataNascita la data di nascita dell'invitato.
     */
    public InvitatoClass(String nome, String cognome, String email, Date dataNascita) {
        super(nome, cognome, dataNascita, email);
        idInvitato = PiattaformaClass.IDInvitato++;
    }

    /**
     * Restituisce l'ID dell'invitato.
     * 
     * @return l'ID dell'invitato.
     */
    public long getIdInvitato() {
        return idInvitato;
    }

    /**
     * Boolean che indica se l'invitato ha accettato o meno.
     * 
     * @return <code>true</code> se ha accettato l'invito all'itinerario,
     *         <code>false</code> altrimenti.
     */
    public boolean isHaAccettato() {
        haAccettato = false;
        // if (rispostaEmail() == true) {
        // haAccettato = true;
        // }
        return haAccettato;
    }

    /**
     * collega un invitato ad una prenotazione, setta l'itinerario (id) relativo alla prenotazione effettuata
     * @param idItinerario id dell'itinerario relativo alla prenotazione effettuata da un Utente
     */
    public void setIdItinerario(int idItinerario) {
        this.idItinerario = idItinerario;
    }

    /**
     * Restituisce l'idItinerario a cui un Invitato &egrave; stato, per l'appunto, invitato
     * @return idItinerario
     */
    public long getIdItinerario() {
        return idItinerario;
    }

}
