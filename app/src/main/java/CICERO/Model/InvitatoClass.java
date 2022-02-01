package CICERO.Model;

// import java.util.Date;

public class InvitatoClass extends PersonaClass {
    private long idInvitato;
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
