package CICERO.Model;

/**
 * Generica classe per spostare dati da form del sito ad un DB
 * @param <T>
 */
public interface Piattaforma<T> {

    /**
     * Inserimento di una proposta alla Piattaforma
     * @param proposta proposta
     * @param cicerone Azienda che la propone
     * @return in/successo dell'inserimento della proposta alla Piattaforma
     */
    boolean insertPropose(T proposta, Cicerone cicerone);

    /**
     * Aggiunge un nuovo profilo aziendale alla Piattaforma
     * @param cicerone nome aziendale di cui fa parte un Cicerone
     * @return in/successo della creazione di un nuovo profilo
     */
    boolean addNewCiceroneProfile(Cicerone cicerone);

    /**
     * Aggiunge un nuovo profilo Utente alla Piattaforma
     * @param utente utente che vuole registrarsi alla Piattaforma
     * @return in/successo della creazione di un nuovo profilo
     */
    boolean addNewUtenteProfile(Utente utente);

    /**
     * Mostra la disponibilit&agrave; per la prenotazione di un Itinerario
     * @param itinerario Itinerario selezionato
     * @return possibilit&agrave; di prenotare o meno un Itinerario
     */
    boolean isAvailable(Itinerario itinerario);
}
