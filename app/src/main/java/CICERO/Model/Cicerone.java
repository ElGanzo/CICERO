package CICERO.Model;

/**
 * Rappresenta il profilo di un'azienda di Ciceroni o di un singolo Cicerone
 */
public interface Cicerone {

    /**
     * Inoltra un evento all'Amministrazione della Piattaforma per essere poi confermato o meno
     *
     * @param proposta Tag, Toponimo o Itinerario da inserire nella Piattaforma dopo l'approvazione dell'Amministrazione
     * @return <code>true</code> se Tag, Toponimo o Itinerario &egrave; stato approvato dall'Amministrazione, <code>false</code> altrimenti
     * @throws NullPointerException se proposta &egrave; <code>null</code>
     */
    <T> boolean inviaProposta(T proposta);

    /**
     * Invia una richiesta di accesso alla Piattaforma
     * TODO: se i metodi dupicati sono parecchi meglio unire cicerone e utente
     */
    void richiestaAccesso();
}
