package CICERO.Model;

import java.util.ArrayList;

public class CiceroneClass implements Cicerone {

    private String ragioneSociale;
    private String partitaIVA;
    private String emailAzienda;
    private String passwordAzienda;
    private ArrayList<Persona> Cicerone;
    private long idCicerone;

    public CiceroneClass(String ragioneSociale, String partitaIVA, String emailAzienda, String passwordAzienda) {
        
        this.idCicerone = PiattaformaClass.IDCiceroni++;
        this.partitaIVA = partitaIVA;
        this.ragioneSociale = ragioneSociale;
        this.emailAzienda = emailAzienda;
        this.passwordAzienda = passwordAzienda;
    }

    public long getIdCicerone() {
        return this.idCicerone;
    }

    @Override
    public <T> boolean inviaProposta(T proposta) {
        return false;
    }
}
