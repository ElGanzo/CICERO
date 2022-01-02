package CICERO.Model;

import java.util.Date;

public class UtenteClass implements Utente {

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

    @Override
    public void richiestaAccesso() {
        //TODO implementare/cambiare

//        PiattaformaClass.accessoUtente(this, servizioAutenticazione);
    }

    @Override
    public boolean prenotazione(Itinerario itinerario) {
        //TODO implementare
        return false;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }
}
