package CICERO.Model;

public class AmministrazioneClass implements Amministrazione {

//    TODO PARAMETRI

    public AmministrazioneClass() {

    }

    @Override
    public <T> boolean approvaProposta(T proposta,Cicerone cicerone) {
        //SE LA PROPOSTA VA BENE O NO ... come lo implemento a livello informatico?

        // dopo aver acceduto alla Piattaforma, l'Amministrazione visualizza le proposte in attesa
        // poi le approva mano mano... todo UC non sviluppato, troppo avanzato?
        return true;
    }
}
