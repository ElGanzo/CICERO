package CICERO.Model;

import CICERO.Model.Persona;

import java.util.Date;

public class PersonaClass implements Persona {
    private String nome;
    private String cognome;
    private String email;
    private Date dataNascita;

    public PersonaClass(String nome, String cognome, String email, Date dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        if (nome == null || cognome == null || email == null || dataNascita == null)
            throw new NullPointerException();
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
