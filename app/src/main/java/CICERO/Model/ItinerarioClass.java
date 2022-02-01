package CICERO.Model;

import java.util.ArrayList;
import java.util.List;

public class ItinerarioClass implements Itinerario{

    private final Cicerone cicerone;
    private String info;
    private String nome;
    private List<TagClass> tags;
    private List<Luogo> toponimi;
    private int numMaxPartecipanti;
    private int numMinPartecipanti;
    private final long idItinerario;          // todo da rivedere, potrebbe essere estratto da DB
    private double durata;
    private boolean proposta;

    public ItinerarioClass(Cicerone cicerone){
        PiattaformaClass.controlloNull(cicerone, "Cicerone inserito per l'itinerario non valido");
        idItinerario = PiattaformaClass.IDItinerario++;
        this.cicerone = cicerone;
        this.tags = new ArrayList<>();
        this.toponimi = new ArrayList<>();
        this.proposta=true;
        this.durata = 0;
    }

    @Override
    public Cicerone getCicerone() {
        return cicerone;
    }

    @Override
    public double getDurata(){ return durata;}

    @Override
    public long getIdItinerario() {
        return idItinerario;
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
        return this.tags;
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
    public void setDurata(double durata){ this.durata = durata;}

    @Override
    public void setInfo(String info){
        PiattaformaClass.controlloNull(info, "le info da inserire non valide");
        this.info=info;
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
        tags.add(tag);
    }

    @Override
    public boolean proposta(){ return this.proposta;}
}
