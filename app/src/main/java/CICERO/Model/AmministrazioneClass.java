package CICERO.Model;

public class AmministrazioneClass implements Amministrazione {

//    TODO PARAMETRI

    public AmministrazioneClass() {

    }

    @Override
    public <T> boolean approvaProposta(Cicerone cicerone, T proposta) {
        return true;
    }

    @Override
    public <T> boolean inserisciProposta(T proposta) {
        return false;
    }
}
