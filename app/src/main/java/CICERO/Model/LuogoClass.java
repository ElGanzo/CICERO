package CICERO.Model;

/**
 * rappresenta un Area geografica: il toponimo che ne deriva &egrave; composto dal luogo,
 */
public class LuogoClass implements Luogo {

    private final String luogo;
    private final String citta;
    private final String provincia;
    private final String regione;
    private boolean proposta;

    public LuogoClass(String luogo, String citta, String provincia, String regione){
        PiattaformaClass.controlloNull(regione, "Almeno la regione deve essere specificata");
        this.luogo = luogo;
        this.citta = citta;
        this.provincia = provincia;
        this.regione = regione;
        this.proposta = true;
    }

    @Override
    public String getToponimo() {
        return luogo+" - "+citta+" - ("+provincia+") - "+regione;
    }

    @Override
    public boolean proposta() {
        return this.proposta;
    }

    /**
     * Un luogo passa da essere proposta ad essere disponibile, quindi proposta==false, ovvero il luogo &egrave; stato accettato
     */
    public void luogoAccettato(){
        this.proposta = false;
    }
}