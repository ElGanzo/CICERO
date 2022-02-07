package CICERO.Model;

import java.util.Date;

/**
 * Rappresenta un utente della piattaforma.
 */
public class UtenteClass extends CICERO.Model.PersonaClass {

    String password;

    /**
     * Rappresenta un utente della piattaforma.
     * 
     * @param nome        il nome dell'utente.
     * @param cognome     il cognome dell'utente.
     * @param dataNascita la data di nascita dell'utente.
     * @param email       l'email dell'utente.
     * @param password    la password dell'utente.
     */
    public UtenteClass(String nome, String cognome, Date dataNascita, String email, String password) {
        super(nome, cognome, dataNascita, email);
        PiattaformaClass.controlloNull(password, "Password non valida");
        this.password = password;
    }

    /**
     * Invia una richiesta di accesso alla Piattaforma
     */
    public void richiestaAccesso() {
        // TODO implementare/cambiare
        // PiattaformaClass.accessoUtente(this, servizioAutenticazione);
    }

    /**
     * Restituisce la password dell'utente.
     * 
     * @return la password dell'utente.
     */
    public String getPassword() {
        return this.password;
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

    public Date getDataNascita() {
        return super.getDataNascita();
    }
}
