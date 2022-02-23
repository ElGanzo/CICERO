package CICERO.Model;

public class AmministrazioneClass implements Amministrazione {
    public AmministrazioneClass() {
    }

    @Override
    public <T> boolean approvaProposta(T proposta,Cicerone cicerone) {
        //SE LA PROPOSTA E' APPROVABILE.. todo in futuro
        return true;
    }
}
