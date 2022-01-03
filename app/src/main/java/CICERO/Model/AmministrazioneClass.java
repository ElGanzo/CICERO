package CICERO.Model;

public class AmministrazioneClass implements Amministrazione {

//    TODO PARAMETRI

    public AmministrazioneClass() {

    }

    @Override
    public <T> boolean approvaProposta(Cicerone cicerone, T proposta) {
        //SE LA PROPOSTA VA BENE O NO ... come lo implemento a livello informatico brò?

        return true;
    }

    @Override
    public <T> boolean inserisciProposta(T proposta) {
       //TODO: come comunica con la piattaforma?
        return false;
    }
}
