package CICERO.Model;

import java.util.Date;

public class UtenteClass {

    private long id;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String email;
    private int servizioAutenticazione;
    private String numTel;

    private String via;
    private String citta;
    private String provincia;
    private int cap;
    private String stato;

    public void Utente(String nome, String cognome, String email, int servizioAutenticazione) {
        if (nome == null || email == null || servizioAutenticazione == 0)
            throw new NullPointerException();
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.servizioAutenticazione = servizioAutenticazione;
    }

    public void richiestaAccesso() {
        //TODO implementare/cambiare
    }

    public boolean prenotazione() {
        //TODO implementare
        return false;
    }

}
