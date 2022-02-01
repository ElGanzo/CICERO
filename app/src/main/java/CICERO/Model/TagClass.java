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
}
