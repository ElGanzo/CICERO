package CICERO.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un itinerario presente nella Piattaforma
 */
public interface Itinerario {

    /**
     * Restituisce la descrizione dell'Itinerario
     *
     * @return Stringa con tutte le info riguardanti l'Itinerario
     */
    String getInfo();

    /**
     * Restituisce i tag assegnati a questo itinerario
     *
     * @return lista dei tag associato a questo itinerario
     */
    List<String> getTags();

    /**
     * Restituisce i toponimi delle aree geografiche assegnate a questo itinerario
     *
     * @return Toponimi associati a questo itinerario
     */
    List<AreaGeografica> getToponimi();

    /**
     * Resituisce il numero massimo di utenti che possono partecipare a questo itinerario
     *
     * @return numero massimo di utenti che possono partecipare a questo itinerario
     */
    int getMaxPartecipanti();

    /**
     * Resituisce il numero minimo di utenti che possono partecipare a questo itinerario
     *
     * @return numero minimo di utenti che possono partecipare a questo itinerario
     */
    int getMinPartecipanti();

    /**
     * Associa numero massimo di partecipanti all'Itinerario
     *
     * @param numMaxPartecipanti numero massimo di partecipanti da associare
     */
    void setMaxPartecipanti(int numMaxPartecipanti);

    /**
     * Associa numero minimo di partecipanti all'Itinerario
     *
     * @param numMinPartecipanti numero minimo di partecipanti da associare
     */
    void setMinPartecipanti(int numMinPartecipanti);


    /**
     * Inserisce le informazioni dell'itinerario
     * @param info stringa contenente info dell'itinerario
     * @throws NullPointerException se info passate sono <code>null</code>
     */
    void setInfo(String info);

    /**
     * Associa un nuovo toponimo all'Itinerario
     *
     * @param toponimo il toponimo da associare all'itinerario
     * @throws NullPointerException se toponimo &egrave; <code>null</code>
     */
    void inserisciToponimo(AreaGeografica toponimo);

    /**
     * Associa tag all'itinerario
     *
     * @param tag il tag da associare
     * @throws NullPointerException     se tag &egrave; <code>null</code>
     * @throws IllegalArgumentException se tag &egrave; gi&agrave; associato all'itinerario
     */
    void inserisciTag(String tag);

    /**
     * Restituisce il cicerone che fara&agrave; da guida per l'itinerario
     * @return {@code Cicerone} associato all'itinerario
     */
    Cicerone getCicerone();

    /**
     * Restituisce l'id assegnato all'itinerario
     * @return idItinerario
     */
    long getIdItinerario();
}
