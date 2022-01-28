package CICERO.Model;

import java.util.ArrayList;
import java.util.List;

public class ItinerarioClass implements Itinerario{

    private int numMaxPartecipanti;
    private int numMinPartecipanti;
    private String info;
    private List<Tag> tags;
    private ArrayList<String> toponimi;
    private long idItinerario;

    // attributoTemporalePerPrenotabilità; ATTRIBUTO DI QUANDO E' PRENOTABILE QUESTO ITINERARIO
    // attributoTemporalePerScadenzaPagamento

    public ItinerarioClass(){
        this.tags = new ArrayList<>();
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info){
        PiattaformaClass.controlloNull(info, "le info da inserire non valide");
        this.info=info;
    }

    @Override
    public List<Tag> getTags() {
        return this.tags;
    }

    @Override
    public ArrayList<String> getToponimi() {
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
    public void setMaxPartecipanti(int numMaxPartecipanti) {
        this.numMaxPartecipanti = numMaxPartecipanti;
    }

    @Override
    public void setMinPartecipanti(int numMinPartecipanti) {
        this.numMinPartecipanti = numMinPartecipanti;
    }

    public void inserisciToponimo(String s) {

        toponimi.add(s);
    }

    @Override
    public void inserisciTag(Tag tag) {
        PiattaformaClass.controlloNull(tag, "tag da inserire non valido");
        this.tags.add(tag);
    }
}
