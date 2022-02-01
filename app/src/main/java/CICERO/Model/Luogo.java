package CICERO.Model;

public interface Luogo {

    /**
     * Restituisce il toponimo
     *
     * @return il toponimo
     */
    String getToponimo();


    /**
     * Indica se il luogo &egrave; una proposta, oppure &egrave; stato approvato
     * @return <code>true</code> se &egrave; una proposta, <code>false</code> se &egrave; stato approvato
     */
    boolean proposta();
}
