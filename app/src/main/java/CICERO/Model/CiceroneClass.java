package CICERO.Model;

import java.util.ArrayList;

/**
 * Rappresenta il <b>PROFILO AZIENDALE</b> di un'associazione/singolo Ciceroni,
 * rappresentati qui con un'<code>ArrayList<code> di PersonaClass  <br>
 * Ogni Cicerone appartenente a questa classe posserr&agrave; le credenziali del profilo
 */
public class CiceroneClass implements Cicerone {

    private String ragioneSociale;
    private String partitaIVA;
    private String emailAzienda;
    private String passwordAzienda;
    private ArrayList<Persona> ciceroni;
    private int idCicerone = 0;

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

    public CiceroneClass(String ragioneSociale, String partitaIVA, String emailAzienda,
                         String passwordAzienda, int idCicerone) {
        PiattaformaClass.controlloNull(ragioneSociale, "Ragione sociale non valida");
        PiattaformaClass.controlloNull(ragioneSociale, "Partita IVA non valida");
        PiattaformaClass.controlloNull(ragioneSociale, "Email aziendale non valida");
        PiattaformaClass.controlloNull(ragioneSociale, "Password aziendale non valida");
        this.partitaIVA = partitaIVA;
        this.ragioneSociale = ragioneSociale;
        this.emailAzienda = emailAzienda;
        this.passwordAzienda = passwordAzienda;
        this.idCicerone = idCicerone;
    }

    /**
     * Restituisce l'id del Cicerone.
     *
     * @return L'id del Cicerone, se id &egrave; presente; <code>-1</code> se id non &egrave; presente.
     */
    public int getIdCicerone() {
        if (idCicerone != 0) {
            return this.idCicerone;
        }
        return -1;
    }

    @Override
    public <T> boolean inviaProposta(T proposta) {
        return false;
    }
}
