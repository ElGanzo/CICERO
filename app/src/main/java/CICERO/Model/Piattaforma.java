package CICERO.Model;

/**
 * Entit&agrave; per gestire i dati estratti da un DB
 */
public interface Piattaforma {

    /**
     * Inserimento di una proposta alla Piattaforma
     *
     * @param proposta proposta
     * @param cicerone Azienda che la propone
     * @return <code>true</code> se la proposta &egrave; aggiunta alla Piattaforma, <code>false</code> altrimenti
     * @throws NullPointerException se almeno uno tra proposta e cicerone &egrave; <code>null</code>
     * @throws IllegalArgumentException se la proposta da inserire &egrave; presente nella Piattaforma
     */
    <T> boolean aggiungiProposta(T proposta, Cicerone cicerone);

    /**
     * Aggiunge un nuovo profilo aziendale alla Piattaforma
     *
     * @param cicerone nome aziendale di cui fa parte un Cicerone
     * @return <code>true</code> se il nuovo profilo &egrave; stato creato, <code>false</code> altrimenti
     * @throws NullPointerException se Cicerone &egrave; <code>null</code>
     * @throws IllegalArgumentException se profilo Cicerone &egrave; gi&agrave; presente nella Piattaforma
     */
    boolean aggiungiProfiloCicerone(Cicerone cicerone);

    /**
     * Aggiunge un nuovo profilo Utente alla Piattaforma
     *
     * @param utente utente che vuole registrarsi alla Piattaforma
     * @return <code>true</code> se il profilo &egrave; stato creato con successo, <code>false</code> altrimenti
     * @throws NullPointerException se Utente &egrave; <code>null</code>
     */
    boolean aggiungiProfiloUtente(Persona utente);

    /**
     * Mostra la disponibilit&agrave; per la prenotazione di un Itinerario
     *
     * @param itinerario Itinerario selezionato
     * @return <code>true</code> se l'itinerario &egrave; prenotabile, <code>false</code> altrimenti
     * @throws NullPointerException se Itinerario &egrave; <code>null</code>
     */
    boolean prenotabilita(Itinerario itinerario);
}
