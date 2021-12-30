package CICERO.Model;

import java.util.List;

/**
 * Rappresenta un itinerario presente nella Piattaforma
 */
public interface Itinerario {

    /**
     * Restituisce la descrizione dell'Itinerario
     * @return Stringa con tutte le info riguardanti l'Itinerario
     */
    String getInfo();

    /**
     * Restituisce i tag assegnati a questo itinerario
     * @return lista dei tag associato a questo itinerario
     * */
    List<Tag> getTags();

    /**
     * Restituisce il toponimo assegnato a questo itinerario
     * @return Toponimo associato a questo itinerario
     * */
    Toponimo getToponimo();

    /**
     * Resituisce il numero massimo di utenti che possono partecipare a questo itinerario
     * @return numero massimo di utenti che possono partecipare a questo itinerario
     */
    int getMaxPartecipanti();

    /**
     * Resituisce il numero minimo di utenti che possono partecipare a questo itinerario
     * @return numero minimo di utenti che possono partecipare a questo itinerario
     */
    int getMinPartecipanti();

}
