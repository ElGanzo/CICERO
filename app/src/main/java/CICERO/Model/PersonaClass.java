package CICERO.Model;

import java.util.Date;

/**
 * Rappresenta una persona, sia essa un invitato, un utente o un cicerone.
 */
public class PersonaClass implements Persona {
    private final String nome;
    private final String cognome;
    private final String email;
    private final Date dataNascita;

    /**
     * Rappresenta una persona, sia essa un invitato, un utente o un cicerone.
     * 
     * @param nome        il nome della persona.
     * @param cognome     il cognome della persona.
     * @param dataNascita la data di nascita della persona.
     * @param email       l'email della persona.
     */
    public PersonaClass(String nome, String cognome, Date dataNascita, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        PiattaformaClass.controlloNull(nome, "Nome non valido");
        PiattaformaClass.controlloNull(cognome, "Cognome non valido");
        PiattaformaClass.controlloNull(email, "Email non valida");
        PiattaformaClass.controlloNull(dataNascita, "Data di nascita non valida");
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
