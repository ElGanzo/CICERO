package CICERO.Controller;

import CICERO.Model.Itinerario;
import CICERO.Model.Prenotazione;
import CICERO.Model.UtenteClass;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Classe per interfacciarsi con il DB
 */
public class DBManager {

    Connection connection;
    Statement connectionStatement;

    private String pattern = "yyyy-mm-dd";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public DBManager(String url, String user, String password) throws Exception {

        connection = DriverManager.getConnection(url, user, password);
        connectionStatement = connection.createStatement();

    }


    // todo ragionare
//    public <T> T estraiDa(T tabella, String query){return null;}
//    public <T> void inserisciIn(T tabella, String query, T... valori) throws SQLException {
//        long id = 0;
//        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//
//            pstmt.setString(1, actor.getFirstName());
//            pstmt.setString(2, actor.getLastName());
//
//            int affectedRows = pstmt.executeUpdate();
//            // check the affected rows
//            if (affectedRows > 0) {
//                // get the ID back
//                try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                    if (rs.next()) {
//                        id = rs.getLong(1);
//                    }
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

//    public List<Cicerone> estraiCiceroni() throws SQLException {
//        String query = "SELECT * FROM Ciceroni;";
//        ResultSet resultSet = connectionStatement.executeQuery(query);
//        //todo separare e analizzare i dati
//        // infine restituirli come List<Ciceroni>
//        return null;
//    }

    /**
     *
     * @param username username dell'Utente
     * @param password password dell'Utente
     * @return utente nel DB, <code>null</code> se Utente non &egrave; presente nel DB o i dati Utente non sono giusti
     */
    public UtenteClass estraiUtente(String username, String password) throws SQLException {
        // esegui query che estrae l utente
        String query = "SELECT email, password FROM Utenti WHERE email = '" + username + "' and password = '" + password + "');";
        ResultSet resultSet = connectionStatement.executeQuery(query);
        // se utente non esiste o password sbagliata oggetti di ritorno e' null
        return resultSet.getObject(0, UtenteClass.class);
    }

    public boolean utenteEsiste(UtenteClass utente) throws SQLException {
        String query = "SELECT COUNT(u_id) FROM Utenti WHERE nome = '" + utente.getEmail() + "' GROUP BY u_id;";
        ResultSet resultSet = connectionStatement.executeQuery(query);
        return resultSet.getObject(0, int.class) > 0;
    }

    public void inserisciNuovoUtente(UtenteClass utente) throws SQLException {
        //TODO inserire flag verificato/accettato (email verificata confermata)

        String date = simpleDateFormat.format(utente.getDataNascita());

        String query = "INSERT INTO Utenti u (nome, cognome, d_nascita, email, password) " +
                "VALUES ('" + utente.getNome() + "', '" +
                utente.getCognome() + "', '" +
                date + "', '" +
                utente.getEmail() + "', '" +
                utente.getPassword() + "');";
        connectionStatement.executeQuery(query);
    }

    public void inserisciPropostaItinerario(Itinerario itinerario) throws SQLException {
        //TODO inserire flag verificato/accettato

        String query = "INSERT INTO Itinerari_proposti ip (nome, id_cicerone, num_min_utenti, num_max_utenti, info) " +
                "VALUES ('" + itinerario.getNome() + "', " +    //titolo itinerario
                itinerario.getCicerone() + ", " +               //id cicerone (autore itinerario)
                itinerario.getMinPartecipanti() + ", " +
                itinerario.getMaxPartecipanti() + ", '" +
                itinerario.getInfo() + "');";                   //descrizione itinerario
        connectionStatement.executeQuery(query);
    }

    public void inserisciPrenotazione(Prenotazione prenotazione) throws SQLException {
        //TODO implementare

        String query = "";

        connectionStatement.executeQuery(query);
    }

}
