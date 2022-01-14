package CICERO.Model;

import java.util.ArrayList;

public class CiceroneClass implements Cicerone {

    private String ragioneSociale;
    private String partitaIVA;
//    private ArrayList<String> email;
    private ArrayList<Persona> Cicerone;

    public CiceroneClass(String ragioneSociale, String partitaIVA) {

    }


    @Override
    public <T> boolean inviaProposta(T proposta) {
        return false;
    }

    @Override
    public void richiestaAccesso() {

    }


}
