package CICERO.Controller;

import CICERO.Model.Cicerone;
import CICERO.Model.UtenteClass;

import java.sql.*;
import java.util.List;

/**
 * Classe per interfacciarsi con il DB
 */
public class DBManager {

    Connection connection;
    Statement connectionStatement;

    public DBManager(String url, String user, String password) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);  // todo qui 1° errore
            connectionStatement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    public List<Cicerone> estraiCiceroni() throws SQLException {
        String query = "SELECT * FROM Ciceroni;";
        ResultSet resultSet = connectionStatement.executeQuery(query);
        //todo separare e analizzare i dati
        // infine restituirli come List<Ciceroni>
        return null;
    }

    /**
     *
     * @param username username dell'Utente
     * @param password password dell'Utente
     * @return utente nel DB, <code>null</code> se Utente non &egrave; presente nel DB o i dati Utente non sono giusti
     */
    public UtenteClass estraiUtente(String username, String password) throws SQLException {
        // esegui query che estrae l utente
        String query = "SELECT * FROM username, password WHERE username = " +username + " and password = " +password;
        ResultSet resultSet = connectionStatement.executeQuery(query);
        // se utente non esiste o password sbagliata oggetti di ritorno e' null
        return resultSet.getObject(0, UtenteClass.class);
    }
}
