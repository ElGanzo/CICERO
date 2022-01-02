package CICERO.Model;

/**
 * Generica classe per spostare dati da form del sito ad un DB
 */
public interface Piattaforma {

    /**
     * Inserimento di una proposta alla Piattaforma
     *
     * @param proposta proposta
     * @param cicerone Azienda che la propone
     * @return true se la proposta &egrave; aggiunta alla Piattaforma, false altrimenti
     * @throws NullPointerException se proposta e/o Cicerone sono null
     */
    <T> boolean aggiungiProposta(T proposta, Cicerone cicerone);

    /**
     * Aggiunge un nuovo profilo aziendale alla Piattaforma
     *
     * @param cicerone nome aziendale di cui fa parte un Cicerone
     * @return true se il nuovo profilo &egrave; stato creato, false altrimenti
     * @throws NullPointerException se Cicerone &egrave; null
     */
    boolean aggiungiProfiloCicerone(Cicerone cicerone);

    /**
     * Aggiunge un nuovo profilo Utente alla Piattaforma
     *
     * @param utente utente che vuole registrarsi alla Piattaforma
     * @return in/successo della creazione di un nuovo profilo
     * @throws NullPointerException se Utente &egrave; null
     */
    boolean aggiungiProfiloUtente(Utente utente);

    /**
     * Mostra la disponibilit&agrave; per la prenotazione di un Itinerario
     *
     * @param itinerario Itinerario selezionato
     * @return possibilit&agrave; di prenotare o meno un Itinerario
     * @throws NullPointerException se Itinerario &egrave; null
     */
    boolean prenotablita(Itinerario itinerario);

    /**
     * Processa la richiesta di accesso da parte di un Utente
     *
     * @param utente l'Utente che vuole effettuare l'accesso
     * @return true se l'accesso &egrave; approvato, false altrimenti
     * @throws NullPointerException se Utente &egrave; null
     */
    boolean accessoUtente(Utente utente, ServizioEsterno servizio);

    /**
     * Processa la richiesta di accesso da parte di un Cicerone
     *
     * @param cicerone il Cicerone che vuole effettuare l'accesso
     * @return true se l'accesso &egrave; approvato, false altrimenti
     * @throws NullPointerException se Cicerone &egrave; null
     */
    boolean accessoCicerone(Cicerone cicerone);
}
