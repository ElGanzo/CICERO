package CICERO.Model;

/**
 * Rappresenta una persona invitata ad un certo itinerario:<br> <b>QUEST'OGGETTO VERR&Agrave; DISTRUTTO UNA VOLTA
 * CONCLUSO L'ITINERARIO</b>
 */
public class InvitatoClass extends PersonaClass {
    //todo classe da rimuovere? "UC8 - Interazione con invito" forse troppo low risk use case?
    private final long idInvitato;
    private boolean haAccettato;
    private long idItinerario;

    public InvitatoClass(String nome, String cognome, String email, String dataNascita) {
        super(nome, cognome, dataNascita, email);
        idInvitato = PiattaformaClass.IDInvitato++;
    }

    public long getIdInvitato() {
        return idInvitato;
    }

    public boolean isHaAccettato() {
        haAccettato = false;
//        if (rispostaEmail() == true) {
//            haAccettato = true;
//        }
        return haAccettato;
    }

    public void setIdItinerario(int idItinerario){ this.idItinerario=idItinerario;}

    public long getIdItinerario() {
        return idItinerario;
    }

}
