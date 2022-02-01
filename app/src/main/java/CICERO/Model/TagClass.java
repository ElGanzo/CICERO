package CICERO.Model;

/**
 * Tag da associare ad un Itinerario, essendo una proposta &egrave; inizialmente in attesa di approvazione
 */
public class TagClass {
    String nome;
    boolean proposta;

    public TagClass(String nome){
        this.nome = nome;
        proposta = true;
    }

    @Override // equivale a getNome();
    public String toString() {
        return nome;
    }

    public void setProposta(boolean proposta) {
        this.proposta = proposta;
    }

    public boolean isProposta() {
        return proposta;
    }
}
