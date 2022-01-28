package CICERO.Model;

import java.util.Date;

public class PersonaClass implements Persona {
    private final String nome;
    private final String cognome;
    private String email;
    private final Date dataNascita;

    public PersonaClass(String nome, String cognome, String email, Date dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        PiattaformaClass.controlloNull(nome, "Nome utente non valido");
        PiattaformaClass.controlloNull(cognome, "Cognome utente non valido");
        PiattaformaClass.controlloNull(email, "Email utente non valida");
        PiattaformaClass.controlloNull(dataNascita, "Data di nascita dell'Utente non valida");
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getCognome() {
        return cognome;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Date getDataNascita() {
        return dataNascita;
    }
}
