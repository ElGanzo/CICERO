package CICERO.Controller;

import CICERO.Model.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe per interfacciarsi con il DB
 */
public class DBManager {

    Connection connection;
    Statement togepiDB;
    Statement togepiDBPrivate;  //da testare

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");

    /**
     * Stabilisce una connessione con il database e crea oggetto DBManager per fornire
     * interfaccia con il database
     *
     * @param url      indirizzo del server del database
     * @param user     nome utente per accesso al database
     * @param password password dell'utente per accesso al database
     * @throws Exception se si verifica un errore di accesso al database
     */
    public DBManager(String url, String user, String password) throws Exception {
        connection = DriverManager.getConnection(url, user, password);
        togepiDB = connection.createStatement();
        togepiDBPrivate = connection.createStatement(); //da testare
    }

    /**
     * Recupera l'utente con le credenziali corrispondenti
     *
     * @param email    nome utente dell'Utente da recuperare
     * @param password password dell'Utente da recuperare
     * @return oggetto utente se l'utente viene trovato, <code>null</code> altrimenti
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public UtenteClass estraiUtente(String email, String password) throws SQLException, ParseException {

        String query = "SELECT * FROM Utenti WHERE email = '" + email + "' AND password = '" + password +
                "' AND verificato = 1;";
        ResultSet resultSet = togepiDB.executeQuery(query);
        UtenteClass utente = null;
        if (resultSet.next()) {
            String nome = resultSet.getObject(2, String.class);
            String cognome = resultSet.getObject(3, String.class);
            Date dataNascita = dateFormatter.parse(resultSet.getObject(4, String.class));
            String mail = resultSet.getObject(5, String.class);
            String pw = resultSet.getObject(6, String.class);
            utente = new UtenteClass(nome, cognome, dataNascita, mail, pw);
        }
        return utente;
    }

    /**
     * Recupera il Cicerone con le credenziali corrispondenti
     *
     * @param email    nome utente del Cicerone da recuperare
     * @param password password del Cicerone da recuperare
     * @return oggetto cicerone se il Cicerone viene trovato, <code>null</code> altrimenti
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public CiceroneClass estraiCicerone(String email, String password) throws SQLException {
        String query = "SELECT * FROM Aziende WHERE email = '" + email + "' and password = '" + password +
                "' AND verificato = 1;";
        ResultSet resultSet = togepiDB.executeQuery(query);
        CiceroneClass cicerone = null;
        if (resultSet.next()) {
            String ragioneSociale = resultSet.getObject(3, String.class);
            String partitaIva = resultSet.getObject(2, String.class);
            String mail = resultSet.getObject(4, String.class);
            String pw = resultSet.getObject(5, String.class);
//            int id = resultSet.getObject(1, int.class);
            cicerone = new CiceroneClass(ragioneSociale, partitaIva, mail, pw);
        }
        //TODO rivedi intero metodo
        return cicerone;
    }

    /**
     * Recupera tutti gli itinerari verificati presenti nel database.
     *
     * @return un ArrayList contenente tutti gli itinerari verificati presenti nel database
     * (Array vuoto se non vengono trovati itinerari)
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public ArrayList<ItinerarioClass> estraiItinerari() throws SQLException {
        ArrayList<ItinerarioClass> itinerariArray = new ArrayList<>();
        int iterazioni = 1;
        ItinerarioClass itinerario;
        String itinerariQuery = "SELECT * FROM Itinerari;";
        ResultSet itinerariResultSet = togepiDB.executeQuery(itinerariQuery);
        while (!itinerariResultSet.isClosed()) {
            if (!itinerariResultSet.next())
                break;
            String nomeItinerarioCorrente = itinerariResultSet.getObject(2, String.class);
            itinerariResultSet.close();

            int idCicerone = getIdCiceroneByItinerario(nomeItinerarioCorrente);
            CiceroneClass cicerone = getCicerone(idCicerone);
            ArrayList<TagClass> tagArray = this.getArray(nomeItinerarioCorrente);
            ArrayList<Luogo> luoghiArray = this.getLuoghi(nomeItinerarioCorrente);

            itinerariResultSet = togepiDB.executeQuery(itinerariQuery);
            for (int i = 0; i < iterazioni; i++) {
                itinerariResultSet.next();
            }
            itinerario = new ItinerarioClass(cicerone, nomeItinerarioCorrente,
                    itinerariResultSet.getObject(4, int.class),
                    itinerariResultSet.getObject(5, int.class),
                    itinerariResultSet.getObject(6, String.class),
                    tagArray, luoghiArray,
                    itinerariResultSet.getObject(7, double.class));
            itinerariArray.add(itinerario);
            iterazioni++;
        }
        return itinerariArray;
    }

    /**
     * Recupera identificativo del singolo cicerone autore dell'itinerario
     *
     * @param nomeItinerarioCorrente nome dell'itinerario
     * @return identificativo del singolo cicerone,
     * <code>-1</code> se il cicerone non viene trovato nel database
     * @throws SQLException se si verificano problemi di accesso al database
     */
    private int getIdCiceroneByItinerario(String nomeItinerarioCorrente) throws SQLException {
        String ciceroniQuery = "SELECT c.id " +
                "FROM Ciceroni c, Itinerari i " +
                "WHERE c.id = i.id_cicerone AND i.nome = '" +
                nomeItinerarioCorrente + "';";
        ResultSet ciceroniResultSet = togepiDB.executeQuery(ciceroniQuery);
        ciceroniResultSet.next();
        int idCicerone = ciceroniResultSet.getObject(1, int.class);
        ciceroniResultSet.close();
        return idCicerone;
    }

    /**
     * Recupera identificativo del singolo cicerone autore dell'itinerario
     *
     * @param email nome dell'itinerario
     * @return identificativo del singolo cicerone,
     * <code>-1</code> se il cicerone non viene trovato nel database
     * @throws SQLException se si verificano problemi di accesso al database
     */
    private int getFirstIdCiceroneByAziendaEmail(String email) throws SQLException {
        //TODO levare perche' fa schifo non si fa cosi
        String ciceroniQuery = "SELECT c.id " +
                "FROM Aziende a, Ciceroni c " +
                "WHERE a.id = c.id_azienda AND a.email = '" + email + "';";
        ResultSet ciceroniResultSet = togepiDB.executeQuery(ciceroniQuery);
        ciceroniResultSet.next();
        int idCicerone = ciceroniResultSet.getObject(1, int.class);
        ciceroniResultSet.close();
        return idCicerone;
    }


    /**
     * Recupera azienda a cui appartiene il cicerone autore dell'itinerario
     *
     * @param idCicerone numero identificativo del singolo cicerone autore dell'itinerario
     * @return oggetto cicerone che rappresenta l'azienda a cui appartiene il cicerone,
     * <code>null</code> se il cicerone non viene trovato nel database
     * @throws SQLException se si verificano problemi di accesso al database
     */
    private CiceroneClass getCicerone(int idCicerone) throws SQLException {
        String aziendeQuery = "SELECT a.nome , a.p_iva , a.email , a.password " +
                "FROM Aziende a, Ciceroni c , Itinerari i " +
                "WHERE i.id_cicerone = c.id AND c.id_azienda = a.id AND c.id = " + idCicerone + ";";
        ResultSet aziendeResultSet = togepiDB.executeQuery(aziendeQuery);
        aziendeResultSet.next();
        CiceroneClass cicerone = new CiceroneClass(
                aziendeResultSet.getObject(1, String.class),
                aziendeResultSet.getObject(2, String.class),
                aziendeResultSet.getObject(3, String.class),
                aziendeResultSet.getObject(4, String.class)
        );
        aziendeResultSet.close();
        return cicerone;
    }

    /**
     * Recupera i tag di un itinerario
     *
     * @param nomeItinerario itinerario i cui tag verranno recuperati
     * @return arrayList contenente tutti i tag dell'itinerario
     * @throws SQLException se si verificano problemi di accesso al database
     */
    private ArrayList<TagClass> getArray(String nomeItinerario) throws SQLException {
        ArrayList<TagClass> tagArray = new ArrayList<>();
        String tagQuery = "SELECT t.nome " +
                "FROM Tag t, Itinerari_Tag it , Itinerari i " +
                "WHERE it.id_itinerario = i.id AND it.nome_tag = t.nome AND i.nome = '" + nomeItinerario + "';";
        ResultSet tagResultSet = togepiDB.executeQuery(tagQuery);
        while (tagResultSet.next()) {
            TagClass tag = new TagClass(tagResultSet.getObject(1, String.class));
            tagArray.add(tag);
        }
        tagResultSet.close();
        return tagArray;
    }

    /**
     * Recupera i luoghi dove si svolge l'itinerario
     *
     * @param nomeItinerarioCorrente il nome dell'itinerario i cui luoghi verranno recuperati
     * @return un arrayList contenente i luoghi dove l'itinerario si svolge
     * @throws SQLException se si verificano problemi di accesso al database
     */
    private ArrayList<Luogo> getLuoghi(String nomeItinerarioCorrente) throws SQLException {
        ArrayList<Luogo> luoghiArray = new ArrayList<>();
        String luoghiQuery = "SELECT l.nome, l.citta , l.provincia , l.regione " +
                "FROM Luoghi l , Itinerari i , Itinerari_Luoghi il " +
                "WHERE il.id_itinerario = i.id AND il.id_luogo = l.id AND i.nome = '" + nomeItinerarioCorrente + "';";
        ResultSet luoghiResultSet = togepiDB.executeQuery(luoghiQuery);
        while (luoghiResultSet.next()) {
            LuogoClass luogo = new LuogoClass(
                    luoghiResultSet.getObject(1, String.class),
                    luoghiResultSet.getObject(2, String.class),
                    luoghiResultSet.getObject(3, String.class),
                    luoghiResultSet.getObject(4, String.class)
            );
            luoghiArray.add(luogo);
        }
        luoghiResultSet.close();
        return luoghiArray;
    }

    /**
     * Recupera tutti i tag verificati presenti nel database.
     *
     * @return un ArrayList contenente tutti i tag verificati presenti nel database
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public ArrayList<TagClass> estraiTag() throws SQLException {
        String query = "SELECT * FROM Tag t WHERE approvato = 1;";
        ResultSet resultSet = togepiDB.executeQuery(query);
        ArrayList<TagClass> result = new ArrayList<>();
        while (resultSet.next()) {
            TagClass tag = new TagClass(resultSet.getObject(1, String.class));
            result.add(tag);
        }
        return result;
    }

    /**
     * Recupera tutti i luoghi verificati presenti nel database.
     *
     * @return un ArrayList contenente tutti i luoghi verificati presenti nel database
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public ArrayList<LuogoClass> estraiLuoghi() throws SQLException {
        String query = "SELECT * FROM Luoghi WHERE approvato = 1;";
        ResultSet resultSet = togepiDB.executeQuery(query);
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

    /**
     * Inserisce un nuovo utente nel database
     *
     * @param utente Il nuovo utente che verr&agrave; inserito nel database
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public void inserisciNuovoUtente(UtenteClass utente) throws SQLException {
        String update = "INSERT INTO Utenti (nome, cognome, d_nascita, email, password) " +
                "VALUES ('" + utente.getNome() + "', '" +
                utente.getCognome() + "', '" +
                dateFormatter.format(utente.getDataNascita()) + "', '" +
                utente.getEmail() + "', '" +
                utente.getPassword() + "');";
        togepiDB.executeUpdate(update);
    }

    /**
     * Inserisce un nuovo itinerario nel database in forma di proposta
     *
     * @param itinerario Il nuovo itinerario da proporre
     * @param cicerone   Il cicerone che propone l'itinerario
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public void inserisciItinerario(Itinerario itinerario, CiceroneClass cicerone) throws SQLException {
        int idCicerone = this.getFirstIdCiceroneByAziendaEmail(cicerone.getEmail());
        String update = "INSERT INTO Itinerari (nome, id_cicerone, num_min_utenti," +
                " num_max_utenti, info, durata_in_ore) " +
                "VALUES ('" + itinerario.getNome() + "', " +
                idCicerone + ", " +
                itinerario.getMinPartecipanti() + ", " +
                itinerario.getMaxPartecipanti() + ", '" +
                itinerario.getInfo() + "', " +
                itinerario.getDurata() + ");";
        togepiDB.executeUpdate(update);
    }

    /**
     * Inserisce un nuovo tag nel database in forma di proposta
     *
     * @param tag Il nuovo tag da proporre
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public void inserisciTag(TagClass tag) throws SQLException {
        String update = "INSERT INTO Tag (nome) VALUES ('" + tag.toString() + "');";
        togepiDB.executeUpdate(update);
    }

    /**
     * Effettua una prenotazione di un itinerario e la inserisce nel database
     *
     * @param prenotazione La nuova prenotazione da effettuare e inserire nel database
     * @throws SQLException se si verifica un errore di accesso al database,
     *                      oppure se non viene trovato l'utente che ha eseguito la prenotazione
     */
    public void inserisciPrenotazione(Prenotazione prenotazione) throws SQLException {
        int idUtente;
        int idItinerario;
        idUtente = getIdUtente(prenotazione);
        idItinerario = getIdItinerario(prenotazione);
        String update;
        update = "INSERT INTO Prenotazioni VALUES (id_itinerario, id_utente, n_partecipanti, " +
                "data_inizio, orario_inizio, data_scadenza_prenotazione, data_scadenza_pagamento) " +
                "VALUES (" + idItinerario + ", " +
                idUtente + ", " +
                prenotazione.getNumPartecipanti() + ", '" +
                dateFormatter.format(prenotazione.getData()) + "', '" +
                timeFormatter.format(prenotazione.getOrarioInizio()) + "', '" +
                dateFormatter.format(prenotazione.getDataScadenzaPrenotazione()) + "', '" +
                dateFormatter.format(prenotazione.getDataScadenzaPagamento()) + "');";
        togepiDB.executeUpdate(update);
    }

    /**
     * Recupera identificativo dell'utente che sta effettuando la prenotazione
     *
     * @param prenotazione la prenotazione da cui recuperare l'id utente
     * @return l'identificativo univoco dell'utente che sta effettuando la prenotazione
     * @throws SQLException se si verifica un errore in accesso al database,
     *                      o se l'utente non viene trovato nel database (anomalia)
     */
    private int getIdUtente(Prenotazione prenotazione) throws SQLException {
        int idUtente;
        String utentiQuery = "SELECT u.id " +
                "FROM Utenti u " +
                "WHERE u.email = '" +
                prenotazione.utente.getEmail() + "';";
        ResultSet utentiResultSet = togepiDB.executeQuery(utentiQuery);
        if (utentiResultSet.next()) {
            idUtente = utentiResultSet.getObject(1, int.class);
        } else throw new SQLException("Utente non trovato");
        return idUtente;
    }

    /**
     * Recupera identificativo dell'itinerario che sta venendo prenotato
     *
     * @param prenotazione la prenotazione che sta venendo effettuata
     * @return l'identificativo univoco dell'itinerario che sta venendo prenotato
     * @throws SQLException se si verifica un errore in accesso al database,
     *                      o se l'itinerario non viene trovato nel database (anomalia)
     */
    private int getIdItinerario(Prenotazione prenotazione) throws SQLException {
        int idItinerario;
        String itinerariQuery = "SELECT i.id " +
                "FROM Itinerari i " +
                "WHERE i.nome = '" +
                prenotazione.itinerario.getNome() + "';";
        ResultSet itinerariResultSet = togepiDB.executeQuery(itinerariQuery);
        if (itinerariResultSet.next()) {
            idItinerario = itinerariResultSet.getObject(1, int.class);
        } else throw new SQLException("Itinerario non trovato nel Database");
        return idItinerario;
    }

}
