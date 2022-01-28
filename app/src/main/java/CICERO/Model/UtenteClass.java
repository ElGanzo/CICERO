package CICERO.Model;

import java.util.Date;

public class UtenteClass extends CICERO.Model.PersonaClass {

    private long id;
//    private int servizioAutenticazione;
    private String numTel;
    private String via;
    private String citta;
    private String provincia;
    private int cap;
    private String stato;

    public UtenteClass(String nome, String cognome, String email, int servizioAutenticazione, Date dataNascita) {
        super(nome, cognome, email, dataNascita);
//        if (servizioAutenticazione == 0)
//            throw new NullPointerException();
////        this.servizioAutenticazione = servizioAutenticazione;
    }

    /**
     * Invia una richiesta di accesso alla Piattaforma
     */
    public void richiestaAccesso() {
        //TODO implementare/cambiare

//        PiattaformaClass.accessoUtente(this, servizioAutenticazione);
    }

    /**
     * effettua una prenotazione ad un itinerario
     *
     * @param itinerario evento turistico a cui partecipare
     * @return in/successo prenotazione all'evento
     * @throws NullPointerException se Itinerario &egrave; <code>null</code>
     */
    public boolean prenotazione(Itinerario itinerario) {
        //TODO intuile perche' lo fa il controller (Piattaforma...)
        return false;
    }

    public String getNome() {
        return super.getNome();
    }

    public String getCognome() {
        return super.getCognome();
    }

    public String getEmail() {
        return super.getEmail();
    }

     public Date getDataNascita(){
        return super.getDataNascita();
     }
}
