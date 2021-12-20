package CICERO.Model;

/**
 * Classe che rappresenta l'entit&agrave; di un Utente ed il suo relativo profilo
 */
public interface Utente {

    /**
     * effettua una prenotazione ad un dato evento
     * @param itinerario
     * @return in/successo prenotazione all'evento
     */
    boolean booking(Itinerario itinerario);
}
