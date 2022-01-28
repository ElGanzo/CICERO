package CICERO.View;

import CICERO.Model.Itinerario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

//    public String richiediToponimo(){
//        System.out.println("dammi il toponimo");
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextLine();
//    }

    /**
     * Stampa la Home iniziale di benvenuto, con la possibilit&agrave; di effettuare Log in Utente e Aziendale
     *
     * @return scelta effettuata dall'Utente
     */
    public int stampaHome() {
        System.out.flush();
        System.out.println("Benvenuto in Cicero!\n");
        System.out.println("Per effettuare le operazioni:");
        System.out.println("[1] -> Log in Utente");
        System.out.println("[2] -> Log in Aziendale");
        System.out.println("[3] -> Crea profilo Utente");
        System.out.println("[0] -> Termina programma Cicero");

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        s = checkCharacter(s);
            
        return Integer.parseInt(s);
    }



    private String checkCharacter(String s) {
        Scanner scanner = new Scanner(System.in);
        while(s.length()>1 || isValidCharacter(s, "1", "2", "0")) {
            s = null;
            System.out.println("Carattere inserito non valido, ritenta");
            s = scanner.nextLine();
        }
        return s;
    }

    /**
     * Controlla che la stringa inserita dall'Utente contenga uno dei caratteri richiesti per effettuare le operazioni
     *
     * @param s stringa su cui effettuare il controllo
     * @return false se la stringa non contiene il carattere, true se il carattere &egrave; presente
     */
    private boolean isValidCharacter(String s, String ... values) {
        for (String x: values){
            if(s.contains(x))
                return true;
        }
        return false;
    }

    public List<String> getCredenziali() {
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        List<String> credenziali = new ArrayList<>();
        credenziali.add(0, username);
        System.out.print("\nPassword: ");
        String password = scanner.nextLine();
        credenziali.add(1, password);
        return credenziali;
    }

    public int stampaItinerari(ArrayList<Itinerario> itinerari) {
        System.out.println("Itinerari: \n");
        int i = 1;
        for (Itinerario itinerario: itinerari){
            System.out.println("["+i +"] -> " +itinerario.toString());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleziona un itinerario: ");

        String s = scanner.nextLine();
        int j = Integer.parseInt(s);
        if(j <= itinerari.size() && j > 0 )
            // visualizza itinerario
            System.out.println(itinerari.get(j-1).toString());

        System.out.println("Prenotare l'itinerario? [Y]es / [N]o");
        s= scanner.nextLine();
        if(s.contains("Y"))
            return j;
        else
            return -1;
    }

}
