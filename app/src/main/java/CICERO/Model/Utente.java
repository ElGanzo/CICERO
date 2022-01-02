package CICERO.Model;

/**
 * Rappresenta l'entit&agrave; di un Utente ed il suo relativo profilo
 */
public interface Utente {

    /**
     * effettua una prenotazione ad un itinerario
     *
     * @param itinerario evento turistico a cui partecipare
     * @return in/successo prenotazione all'evento
     * @throws NullPointerException se Itinerario &egrave; <code>null</code>
     */
    boolean prenotazione(Itinerario itinerario);

    /**
     * Invia una richiesta di accesso alla Piattaforma
     * TODO: se i metodi dupicati sono parecchi meglio unire cicerone e utente
     */
    void richiestaAccesso();
}
