package CICERO.Model;

import java.util.Date;

public class UtenteClass extends CICERO.Model.PersonaClass {

    String password;

    public UtenteClass(String nome, String cognome, Date dataNascita, String email, String password) {
        super(nome, cognome, dataNascita, email);
        PiattaformaClass.controlloNull(password, "Password non valida");
        this.password = password;
    }

    /**
     * Invia una richiesta di accesso alla Piattaforma
     */
    public void richiestaAccesso() {
        //TODO implementare/cambiare
//        PiattaformaClass.accessoUtente(this, servizioAutenticazione);
    }

    public String getPassword(){return this.password;}

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
