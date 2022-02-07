package CICERO.Model;

/**
 * Tag da associare ad un Itinerario, essendo una proposta &egrave; inizialmente
 * in attesa di approvazione.
 */
public class TagClass {
    String nome;
    boolean approvato;

    /**
     * Tag da associare ad un Itinerario, essendo una proposta &egrave; inizialmente
     * in attesa di approvazione.
     * 
     * @param nome il tag.
     */
    public TagClass(String nome) {
        this.nome = nome;
        approvato = true;
    }

    /**
     * Imposta il tag come approvato.
     */
    public void setApprovatoTrue() {
        this.approvato = true;
    }

    /**
     * Restituisce <code>true</code> se il tag è stato approvato, <code>false</code>
     * altrimenti.
     * 
     * @return <code>true</code> se il tag è stato approvato, <code>false</code>
     *         altrimenti.
     */
    public boolean isProposta() {
        return approvato;
    }

    @Override // equivale a getNome();
    public String toString() {
        return nome;
    }
}
