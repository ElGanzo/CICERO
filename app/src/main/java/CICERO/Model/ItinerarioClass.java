package CICERO.Model;

import java.util.ArrayList;
import java.util.List;

public class ItinerarioClass implements Itinerario{

    private final Cicerone cicerone;
    private String nome;
    private int numMinPartecipanti;
    private int numMaxPartecipanti;
    private String info;
    private List<TagClass> tag;
    private List<Luogo> toponimi;
    private double durata;
    private boolean proposta;

    public ItinerarioClass(Cicerone cicerone, String nome, int numMinPartecipanti, int numMaxPartecipanti, String info,
                           ArrayList<TagClass> tag, ArrayList<Luogo> toponimi, double durata){
        PiattaformaClass.controlloNull(cicerone, "Cicerone inserito per l'itinerario non valido");
        this.cicerone = cicerone;
        this.nome = nome;
        this.numMinPartecipanti = numMinPartecipanti;
        this.numMaxPartecipanti = numMaxPartecipanti;
        this.info = info;
        this.tag = tag;
        this.toponimi = toponimi;
        this.durata = durata;
        this.proposta=true;
    }

    public ItinerarioClass(Cicerone cicerone, String nome, int numMinPartecipanti, int numMaxPartecipanti, String info,
                           ArrayList<TagClass> tag, ArrayList<Luogo> toponimi){
        PiattaformaClass.controlloNull(cicerone, "Cicerone inserito per l'itinerario non valido");
        this.cicerone = cicerone;
        this.nome = nome;
        this.numMinPartecipanti = numMinPartecipanti;
        this.numMaxPartecipanti = numMaxPartecipanti;
        this.info = info;
        this.tag = tag;
        this.toponimi = toponimi;
        this.durata = 1;
        this.proposta=true;
    }

    public ItinerarioClass(Cicerone cicerone, String nome, int numMinPartecipanti, int numMaxPartecipanti,
                           ArrayList<TagClass> tag, ArrayList<Luogo> toponimi){
        PiattaformaClass.controlloNull(cicerone, "Cicerone inserito per l'itinerario non valido");
        this.cicerone = cicerone;
        this.nome = nome;
        this.numMinPartecipanti = numMinPartecipanti;
        this.numMaxPartecipanti = numMaxPartecipanti;
        this.info = "";
        this.tag = tag;
        this.toponimi = toponimi;
        this.durata = 1;
        this.proposta=true;
    }

    @Override
    public Cicerone getCicerone() {
        return cicerone;
    }

    @Override
    public double getDurata(){ return durata;}

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
        this.tag.add(tag);
    }

    @Override
    public boolean proposta(){ return this.proposta;}
}
