package CICERO.Model;

/**
 * Classe che rappresenta l'Amministrazione della Piattaforma
 * TODO: la concretizzazione può essere totalmente statica?
 *  così per esempio anche Cicerone può chiamare Amministrazione.approvaProposta nel suo metodo inviaProposta();
 */
public interface Amministrazione {

    /**
     * Stabilisce se un itinerario proposto da un profilo Cicerone &egrave; ammissibile nella Piattaforma
     *
     * @param cicerone Azienda che propone l'itinerario
     * @param proposta itinerario, tag o toponimo da inserire
     * @return <code>true</code> se la proposta &egrave; approvata, <code>false</code> altrimenti
     * @throws NullPointerException se almeno uno tra cicerone e proposta &egrave; <code>null</code>
     */
    <T> boolean approvaProposta(Cicerone cicerone, T proposta); // invia email, aspetta riscontro amministrazione, restituisce true se va bene
    // e se va bene verrà chiamato inserisci proposta TODO: da eliminare il commentis

    /**
     * Aggiunge un tag, toponimo o un itinerario alle strutture dati corrispondenti.
     * Una proposta da aggiungere &egrave; approvata e pronta per essere inserita: se &egrave; un tag verr&agrave; aggiunto alla lista dei tag, se &egrave; un toponimo verr&agrave; aggiunto alla lista dei toponimi e cos&igrave; via...
     *
     * @param proposta itinerario, tag o toponimo da aggiungere
     * @return <code>true</code> se la proposta &egrave; stata inserita, <code>false</code> altrimenti
     * @throws NullPointerException se proposta &egrave; <code>null</code>
     */
    <T> boolean inserisciProposta(T proposta); // if(toAdd.instanceof(tag) ... if(instanceof(toponimo) ecc...

}
