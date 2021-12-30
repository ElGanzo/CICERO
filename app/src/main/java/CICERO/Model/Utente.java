package CICERO.Model;

/**
 * Rappresenta l'entit&agrave; di un Utente ed il suo relativo profilo
 */
public interface Utente {

    /**
     * effettua una prenotazione ad un itinerario
     * @param itinerario evento turistico a cui partecipare
     * @return in/successo prenotazione all'evento
     */
    boolean prenotazione(Itinerario itinerario);
}
