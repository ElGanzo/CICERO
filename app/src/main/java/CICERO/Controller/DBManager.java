package CICERO.Controller;

import CICERO.Model.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe per interfacciarsi con il DB
 */
public class DBManager {

    Connection connection;
    Statement connectionStatement;

//    private String pattern = "yyyy-mm-dd";
//    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public DBManager(String url, String user, String password) throws Exception {

        connection = DriverManager.getConnection(url, user, password);
        connectionStatement = connection.createStatement();

    }

    /**
     * @param username username dell'Utente
     * @param password password dell'Utente
     * @return utente nel DB, <code>null</code> se Utente non &egrave; presente nel DB o i dati Utente non sono giusti
     */
    public UtenteClass estraiUtente(String username, String password) throws SQLException {
        String query = "SELECT email, password FROM Utenti WHERE email = '" + username + "' and password = '" + password + "');";
        ResultSet resultSet = connectionStatement.executeQuery(query);

        if (resultSet.next())
            return resultSet.getObject(0, UtenteClass.class);
        else return null;
    }

    public CiceroneClass estraiCicerone(String username, String password) throws SQLException {
        String query = "SELECT email, password FROM Aziende a WHERE email = '" + username + "' and password = '" + password + "');";
        ResultSet resultSet = connectionStatement.executeQuery(query);

        if (resultSet.next())
            return resultSet.getObject(0, CiceroneClass.class);
        else return null;
    }

    public ArrayList<ItinerarioClass> estraiItinerari() throws SQLException {
        String query = "SELECT i.nome, i.num_min_utenti, i.num_max_utenti, a.nome, a.p_iva, a.email, a.password, " +
                "t.nome, l.nome, l.citta, l.provincia, l.regione " +
                "FROM Itinerari i, Ciceroni c, Aziende a, Luoghi l, Tag t, Itinerari_Tag it, Itinerari_Luoghi il " +
                "WHERE i.id_cicerone = c.id AND c.id_azienda = a.id AND it.id_itinerario = i.id AND it.nome_tag = t.nome " +
                "AND il.id_itinerario = i.id AND il.id_luogo = l.id AND i.accettato = 1 " +
                "ORDER BY i.nome;";
        ResultSet resultSet = connectionStatement.executeQuery(query);
        ArrayList<ItinerarioClass> result = new ArrayList<>();
        ItinerarioClass itinerario;
        CiceroneClass cicerone;
        TagClass tag;
        ArrayList<TagClass> tagArray = new ArrayList<>();
        LuogoClass luogo;
        ArrayList<Luogo> luogoArray = new ArrayList<>();
        int numIterazioni = 0;

        resultSet.next();
        String nomeItinerarioRigaPrecedente = resultSet.getObject(1, String.class);
        String nomeItinerarioRigaCorrente;
        while (resultSet.next()) {
            nomeItinerarioRigaCorrente = resultSet.getObject(1, String.class);
            tag = new TagClass(resultSet.getObject(8, String.class));   //nome tag
            if ((numIterazioni != 0) &&
                    nomeItinerarioRigaPrecedente.contentEquals(nomeItinerarioRigaCorrente) &&
                    !tagArray.contains(tag)) {
                tagArray.add(tag);
                continue;
            }
            luogo = new LuogoClass(
                    resultSet.getObject(9, String.class),   //toponimo
                    resultSet.getObject(10, String.class),  //citta
                    resultSet.getObject(11, String.class),  //provincia
                    resultSet.getObject(12, String.class)   //regione
            );
            if ((numIterazioni != 0) &&
                    nomeItinerarioRigaPrecedente.contentEquals(nomeItinerarioRigaCorrente) &&
                    !luogoArray.contains(luogo)) {
                luogoArray.add(luogo);
                continue;
            }
            cicerone = new CiceroneClass(
                    resultSet.getObject(4, String.class),   //nome azienda
                    resultSet.getObject(5, String.class),   //partita IVA
                    resultSet.getObject(6, String.class),   //email
                    resultSet.getObject(7, String.class)    //password
            );
            itinerario = new ItinerarioClass(cicerone,
                    resultSet.getObject(1, String.class),   //titolo itinerario
                    resultSet.getObject(2, int.class),      //min partecipanti
                    resultSet.getObject(3, int.class),      //max partecipanti
                    tagArray, luogoArray);
            result.add(itinerario);
            tagArray = new ArrayList<>();
            luogoArray = new ArrayList<>();
            nomeItinerarioRigaPrecedente = resultSet.getObject(1, String.class);
            numIterazioni++;
        }
        return result;
    }

    public ArrayList<TagClass> estraiTag() throws SQLException {
        String query = "SELECT * FROM Tag t WHERE accettato = 1;";
        ResultSet resultSet = connectionStatement.executeQuery(query);
        ArrayList<TagClass> result = new ArrayList<>();
        while (resultSet.next()) {
            TagClass tag = new TagClass(resultSet.getObject(1, String.class));
            result.add(tag);
        }
        return result;
    }

    public ArrayList<LuogoClass> estraiLuoghi() throws SQLException {
        String query = "SELECT * FROM Luoghi l WHERE accettato = 1;";
        ResultSet resultSet = connectionStatement.executeQuery(query);
        ArrayList<LuogoClass> result = new ArrayList<>();
        LuogoClass luogo;

        while (resultSet.next()) {
            luogo = new LuogoClass(resultSet.getObject(2, String.class),        //luogo
                    resultSet.getObject(3, String.class),                       //citta'
                    resultSet.getObject(4, String.class),                       //provincia
                    resultSet.getObject(5, String.class));                      //regione
            result.add(luogo);
        }
        return result;
    }

    //TODO eliminare
    public boolean utenteEsiste(UtenteClass utente) throws SQLException {
        String query = "SELECT COUNT(u_id) FROM Utenti WHERE nome = '" + utente.getEmail() + "' GROUP BY u_id;";
        ResultSet resultSet = connectionStatement.executeQuery(query);
        return resultSet.getObject(0, int.class) > 0;
    }

    public void inserisciNuovoUtente(UtenteClass utente) throws SQLException {
        //TODO inserire flag verificato/accettato (email verificata confermata)
        String query = "INSERT INTO Utenti u (nome, cognome, d_nascita, email, password) " +
                "VALUES ('" + utente.getNome() + "', '" +
                utente.getCognome() + "', '" +
                utente.getDataNascita() + "', '" +
                utente.getEmail() + "', '" +
                utente.getPassword() + "');";
        connectionStatement.executeQuery(query);
    }

    public void inserisciItinerario(Itinerario itinerario) throws SQLException {
        String query = "INSERT INTO Itinerari_proposti ip (nome, id_cicerone, num_min_utenti, num_max_utenti, info) " +
                "VALUES ('" + itinerario.getNome() + "', " +    //titolo itinerario
                itinerario.getCicerone() + ", " +               //id cicerone (autore itinerario)
                itinerario.getMinPartecipanti() + ", " +
                itinerario.getMaxPartecipanti() + ", '" +
                itinerario.getInfo() + "');";                   //descrizione itinerario
        connectionStatement.executeQuery(query);
    }

    public void inserisciTag(TagClass tag) throws SQLException {
        String query = "INSERT INTO Tag t (nome) VALUES ('" + tag.toString() + "');";
        connectionStatement.executeQuery(query);
    }

    public void inserisciPrenotazione(Prenotazione prenotazione) throws SQLException {

        String query = "INSERT INTO Prenotazioni VALUES (id_itinerario, id_utente, n_partecipanti," +
                "data_inizio, orario_inizio, data_scadenza_prenotazione, data_scadenza_pagamento";

//        connectionStatement.executeQuery(query);
    }

}
