package CICERO.Model;

/**
 * rappresenta un Area geografica: il toponimo che ne deriva &egrave; composto dal luogo,
 */
public class AreaGeograficaClass implements AreaGeografica{

    private final String luogo;
    private final String citta;
    private final String provincia;
    private final String regione;

    // todo da separare quando estraggo dal DB; PROBABILMENTE CONTROLLO NULL INUTILE PERCHE' VIENE APPROVATO DALL'AMMINISTRAZIONE
    public AreaGeograficaClass(String l, String c, String p, String r){
        PiattaformaClass.controlloNull(l, "Il luogo deve essere specificato");
        PiattaformaClass.controlloNull(c, "La citta' deve essere specificata");
        PiattaformaClass.controlloNull(p, "La provincia deve essere specificata");
        PiattaformaClass.controlloNull(r, "La regione deve essere specificata");
        luogo = l;
        citta = c;
        provincia = p;
        regione = r;
    }

    @Override
    public String getToponimo() {
        return luogo+" - "+citta+" - ("+provincia+") - "+regione;
    }
}
