package CICERO.Model;

public interface Luogo {

    /**
     * Restituisce il toponimo
     *
     * @return il toponimo
     */
    String getToponimo();

    /**
     * Indica se il luogo &egrave; una proposta, oppure se &egrave; stato approvato dall'amministrazione.
     *
     * @return <code>true</code> se &egrave; una proposta, <code>false</code> altrimenti.
     */
    boolean approvato();
}
