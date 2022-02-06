package CICERO.Model;

import java.util.ArrayList;
import java.util.List;

public class ItinerarioClass implements Itinerario {

    private final Cicerone cicerone;
    private String nome;
    private int numMinPartecipanti;
    private int numMaxPartecipanti;
    private String info;
    private List<TagClass> listaTag;
    private TagClass singoloTag;
    private List<Luogo> luoghi;
    private Luogo luogo;
    private double durata;
    private final boolean proposta;

    /**
     * Rappresenta un itinerario.
     * 
     * @param cicerone           il Cicerone che fa da guida per l'itinerario.
     * @param nome               il nome dell'itinerario.
     * @param numMinPartecipanti il numero minimo di partecipanti.
     * @param numMaxPartecipanti il numero massimo di partecipanti.
     * @param info               la descrizione dell'itinerario.
     * @param tag                il tag assegnato all'itinerario.
     * @param luogo              il luogo dove si svolge l'itinerario.
     * @param durata             la durata dell'itinerario (se null viene assegnata
     *                           come di 1h di default).
     */
    public ItinerarioClass(Cicerone cicerone, String nome, int numMinPartecipanti, int numMaxPartecipanti, String info,
            TagClass tag, Luogo luogo, double durata) {
        this.cicerone = cicerone;
        this.nome = nome;
        this.numMinPartecipanti = numMinPartecipanti;
        this.numMaxPartecipanti = numMaxPartecipanti;
        this.info = info;
        this.singoloTag = tag;
        this.luogo = luogo;
        this.durata = durata;
        this.proposta = true;
    }

    /**
     * Rappresenta un itinerario.
     * 
     * @param cicerone           il Cicerone che fa da guida per l'itinerario.
     * @param nome               il nome dell'itinerario.
     * @param numMinPartecipanti il numero minimo di partecipanti.
     * @param numMaxPartecipanti il numero massimo di partecipanti.
     * @param info               la descrizione dell'itinerario.
     * @param tag                i tag assegnati all'itinerario.
     * @param luoghi             i luoghi dove si svolge l'itinerario.
     * @param durata             la durata dell'itinerario (se null viene assegnata
     *                           come di 1h di default).
     */
    public ItinerarioClass(Cicerone cicerone, String nome, int numMinPartecipanti, int numMaxPartecipanti, String info,
            ArrayList<TagClass> tag, ArrayList<Luogo> luoghi, double durata) {
        PiattaformaClass.controlloNull(cicerone, "Cicerone inserito per l'itinerario non valido");
        this.cicerone = cicerone;
        this.nome = nome;
        this.numMinPartecipanti = numMinPartecipanti;
        this.numMaxPartecipanti = numMaxPartecipanti;
        this.info = info;
        this.listaTag = tag;
        this.luoghi = luoghi;
        this.durata = durata;
        this.proposta = true;
    }

    @Override
    public double getDurata() {
        return durata;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public List<TagClass> getTags() {
        return this.listaTag;
    }

    @Override
    public List<Luogo> getToponimi() {
        return this.luoghi;
    }

    @Override
    public int getMaxPartecipanti() {
        return this.numMaxPartecipanti;
    }

    @Override
    public int getMinPartecipanti() {
        return this.numMinPartecipanti;
    }

    @Override
    public void setDurata(double durata) {
        this.durata = durata;
    }

    @Override
    public void setInfo(String info) {
        PiattaformaClass.controlloNull(info, "le info da inserire non valide");
        this.info = info;
    }

    @Override
    public void setNome(String nome) {
        PiattaformaClass.controlloNull(nome, "Nome non valido");
        this.nome = nome;
    }

    @Override
    public void setMaxPartecipanti(int numMaxPartecipanti) {
        this.numMaxPartecipanti = numMaxPartecipanti;
    }

    @Override
    public void setMinPartecipanti(int numMinPartecipanti) {
        this.numMinPartecipanti = numMinPartecipanti;
    }

    @Override
    public void inserisciToponimo(Luogo toponimo) {
        PiattaformaClass.controlloNull(toponimo, "Toponimo inserito non valido");
        this.luoghi.add(toponimo);
    }

    @Override
    public void inserisciTag(TagClass tag) {
        PiattaformaClass.controlloNull(tag, "tag da inserire non valido");
        this.listaTag.add(tag);
    }

    public Cicerone getCicerone() {
        return this.cicerone;
    }

    @Override
    public boolean proposta() {
        return this.proposta;
    }
}
