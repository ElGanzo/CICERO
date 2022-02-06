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
    private boolean proposta;

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

    // qui era stato fatto overloading: una versione di ItinerarioClass senza durata
    // e un'altra senza info

    public int getCiceroneId() {
        return cicerone.getIdCicerone();
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
        return this.tag;
    }

    @Override
    public List<Luogo> getToponimi() {
        return toponimi;
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
        toponimi.add(toponimo);
    }

    @Override
    public void inserisciTag(TagClass tag) {
        PiattaformaClass.controlloNull(tag, "tag da inserire non valido");
        this.tag.add(tag);
    }

    public Cicerone getCicerone() {
        return this.cicerone;
    }

    @Override
    public boolean proposta() {
        return this.proposta;
    }
}
