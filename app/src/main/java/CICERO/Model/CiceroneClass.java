package CICERO.Model;

import java.util.ArrayList;

/**
 * Rappresenta il <b>PROFILO AZIENDALE</b> di un'associazione composta da uno o
 * pi&ugrave; Ciceroni. Ogni Cicerone &egrave; rappresentato con
 * un'<code>ArrayList</code> di PersonaClass e avr&agrave; le credenziali del
 * profilo.
 */
public class CiceroneClass implements Cicerone {

    private String ragioneSociale;
    private String partitaIVA;
    private String emailAzienda;
    private String passwordAzienda;
    private ArrayList<Persona> ciceroni;
    private int idCicerone = 0;

    /**
     * Corrisponde al Cicerone che si occupa di un itinerario
     * 
     * @param ragioneSociale  la ragione sociale dell'azienda di cui fa parte
     * @param partitaIVA      la partita IVA dell'azienda di cui fa parte
     * @param emailAzienda    l'email dell'azienda di cui fa parte
     * @param passwordAzienda la password per fare il log in aziendale
     */
    public CiceroneClass(String ragioneSociale, String partitaIVA, String emailAzienda, String passwordAzienda) {
        PiattaformaClass.controlloNull(ragioneSociale, "Ragione sociale non valida");
        PiattaformaClass.controlloNull(ragioneSociale, "Partita IVA non valida");
        PiattaformaClass.controlloNull(ragioneSociale, "Email aziendale non valida");
        PiattaformaClass.controlloNull(ragioneSociale, "Password aziendale non valida");
        this.partitaIVA = partitaIVA;
        this.ragioneSociale = ragioneSociale;
        this.emailAzienda = emailAzienda;
        this.passwordAzienda = passwordAzienda;
    }

    /**
     * Restituisce l'id del Cicerone.
     *
     * @return L'id del Cicerone se id &egrave; presente; <code>-1</code> se id non
     *         &egrave; presente.
     */
    public int getIdCicerone() {
        if (idCicerone != 0) {
            return this.idCicerone;
        }
        return -1;
    }

    @Override
    public String getRagioneSociale(){return this.ragioneSociale;}

    /**
     * Restituisce l'email dell'azienda di cui fa parte il Cicerone.
     * 
     * @return l'email dell'azienda di cui fa parte il Cicerone.
     */
    public String getEmail() {
        return this.emailAzienda;
    }

    @Override
    public <T> boolean inviaProposta(T proposta) {
        return false;
    }
}
