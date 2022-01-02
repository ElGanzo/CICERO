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
     * @return <code>true</code> se la proposta &egrave; aggiunta alla Piattaforma, <code>false</code> altrimenti
     * @throws NullPointerException se almeno uno tra proposta e cicerone &egrave; <code>null</code>
     */
    <T> boolean aggiungiProposta(T proposta, Cicerone cicerone);

    /**
     * Aggiunge un nuovo profilo aziendale alla Piattaforma
     *
     * @param cicerone nome aziendale di cui fa parte un Cicerone
     * @return <code>true</code> se il nuovo profilo &egrave; stato creato, <code>false</code> altrimenti
     * @throws NullPointerException se Cicerone &egrave; <code>null</code>
     */
    boolean aggiungiProfiloCicerone(Cicerone cicerone);

    /**
     * Aggiunge un nuovo profilo Utente alla Piattaforma
     *
     * @param utente utente che vuole registrarsi alla Piattaforma
     * @return <code>true</code> se il profilo &egrave; stato creato con successo, <code>false</code> altrimenti
     * @throws NullPointerException se Utente &egrave; <code>null</code>
     */
    boolean aggiungiProfiloUtente(Utente utente);

    /**
     * Mostra la disponibilit&agrave; per la prenotazione di un Itinerario
     *
     * @param itinerario Itinerario selezionato
     * @return <code>true</code> se l'itinerario &egrave; prenotabile, <code>false</code> altrimenti
     * @throws NullPointerException se Itinerario &egrave; <code>null</code>
     */
    boolean prenotabilita(Itinerario itinerario);   //TODO egsblain?!?!?

    /**
     * Processa la richiesta di accesso da parte di un Utente
     *
     * @param utente l'Utente che vuole effettuare l'accesso
     * @return <code>true</code> se l'accesso &egrave; approvato, <code>false</code> altrimenti
     * @throws NullPointerException se Utente &egrave; <code>null</code>
     */
    boolean accessoUtente(Utente utente, ServizioEsterno servizio);

    /**
     * Processa la richiesta di accesso da parte di un Cicerone
     *
     * @param cicerone il Cicerone che vuole effettuare l'accesso
     * @return <code>true</code> se l'accesso &egrave; approvato, <code>false</code> altrimenti
     * @throws NullPointerException se Cicerone &egrave; <code>null</code>
     */
    boolean accessoCicerone(Cicerone cicerone);
}
