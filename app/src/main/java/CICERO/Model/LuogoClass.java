package CICERO.Model;

/**
 * Rappresenta un Area geografica dove si possono svolgere uno o più itinerari.
 */
public class LuogoClass implements Luogo {

    private final String luogo;
    private final String citta;
    private final String provincia;
    private final String regione;
    private boolean approvato;

    /**
     * Rappresenta un luogo che pu&ograve; essere assegnato a uno o pi&ugrave;
     * itinerari. Un luogo pu&ograve; essere una citt&agrave; intera, un luogo
     * dentro una citt&agrave;, o una frazione di una citt&agrave;.
     * 
     * @param luogo     il nome del luogo (pu&ograve; contenere
     *                  la stessa info del campo citt&agrave;).
     * @param citta     la citt&agrave; dove &egrave; situato il luogo.
     * @param provincia la provincia a cui appartiene la citt&agrave;.
     * @param regione   la regione a cui appartiene la provincia.
     */
    public LuogoClass(String luogo, String citta, String provincia, String regione) {
        PiattaformaClass.controlloNull(regione, "Almeno la regione deve essere specificata");
        this.luogo = luogo;
        this.citta = citta;
        this.provincia = provincia;
        this.regione = regione;
        this.approvato = true;
    }

    /**
     * Pone un luogo proposto da non approvato ad approvato dall'amministrazione.
     */
    public void setLuogoApprovato() {
        this.approvato = true;
    }

    @Override
    public String getToponimo() {
        return luogo + " - " + citta + " - (" + provincia + ") - " + regione;
    }

    @Override
    public boolean approvato() {
        return this.approvato;
    }
}