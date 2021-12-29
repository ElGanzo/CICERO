package CICERO.Model;

/**
 * Rappresenta il profilo di un'azienda di Ciceroni o di un singolo Cicerone
 */
public interface Cicerone {

    /**
     * Inoltra un evento all'Amministrazione della Piattaforma per essere poi confermato o meno
     *
     * @param propose    tag, toponimo o itinerario da inserire nella Piattaforma dopo l'approvazione dell'Amministrazione
     * @return l'Itinerario &egrave; stato approvato o meno dall'Amministrazione
     */
    <T> boolean sendPropose(T propose);
}
