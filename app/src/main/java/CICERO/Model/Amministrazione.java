package CICERO.Model;

/**
 * Classe che rappresenta l'Amministrazione della Piattaforma
 */
public interface Amministrazione {

    /**
     * Stabilisce se un itinerario proposto da un profilo Cicerone &egrave; ammissibile nella Piattaforma
     * @param cicerone  Azienda che propone l'itinerario
     * @param itinerario da inserire
     * @return
     */
    <T> boolean approvePropose(Cicerone cicerone, T propose);

}
