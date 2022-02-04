package CICERO.Model;

// import java.util.Date;

import java.util.Date;

public interface Persona {

    /**
     * Restituisce il nome della persona
     *
     * @return il nome della persona
     */
    String getNome();

    /**
     * Restituisce il cognome della persona
     *
     * @return il cognome della persona
     */
    String getCognome();

    /**
     * Restituisce l'email della persona
     *
     * @return l'email della persona
     */
    String getEmail();

    /**
     * Restitusce la data di nascita della persona
     *
     * @return la data di nascita della persona
     */
    Date getDataNascita();
}