package CICERO.Model;

// import java.util.Date;

public class PersonaClass implements Persona {
    private final String nome;
    private final String cognome;
    private final String email;
    private final String dataNascita;

    public PersonaClass(String nome, String cognome, String dataNascita, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        PiattaformaClass.controlloNull(nome, "Nome non valido");
        PiattaformaClass.controlloNull(cognome, "Cognome non valido");
        PiattaformaClass.controlloNull(email, "Email non valida");
        PiattaformaClass.controlloNull(dataNascita, "Data di nascita non valida");}

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
    public String getDataNascita() {
        return dataNascita;
    }
}
