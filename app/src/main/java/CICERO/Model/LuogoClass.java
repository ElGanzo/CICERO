package CICERO.Model;

/**
 * rappresenta un Area geografica: il toponimo che ne deriva &egrave; composto dal luogo,
 */
public class LuogoClass implements Luogo {

    private final String luogo;
    private final String citta;
    private final String provincia;
    private final String regione;
    private final long IDLuogo;
    private boolean proposta;
    
    public LuogoClass(String l, String c, String p, String r){
        //  PROBABILMENTE CONTROLLO NULL INUTILE PERCHE' VIENE APPROVATO DALL'AMMINISTRAZIONE
        PiattaformaClass.controlloNull(l, "Il luogo deve essere specificato");
        PiattaformaClass.controlloNull(c, "La citta' deve essere specificata");
        PiattaformaClass.controlloNull(p, "La provincia deve essere specificata");
        PiattaformaClass.controlloNull(r, "La regione deve essere specificata");
        luogo = l;
        citta = c;
        provincia = p;
        regione = r;
        IDLuogo = PiattaformaClass.IDLuogo++;
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

    public long getIDLuogo() {
        return IDLuogo;
    }
}
