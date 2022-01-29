package CICERO.Model;

/**
 * Classe che rappresenta l'Amministrazione della Piattaforma
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
    <T> boolean approvaProposta(T proposta, Cicerone cicerone);
}
