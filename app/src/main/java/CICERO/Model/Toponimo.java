package CICERO.Model;

import java.util.List;

/**
 * Definisce una o pi&ugrave; aree geografiche identificate con un nome
 */
public interface Toponimo {

    /**
     * Restituisce il nome del toponimo
     * @return  nome del toponimo
     */
    String getNome();

    /**
     * Restituisce una lista delle aree geografiche che comprendono questo toponimo
     * @param <T>   Tipo con cui sono state identificate le aree geografiche
     * @return  lista delle aree geografiche
     */
    <T> List<T> getAreeGeografiche();
}
