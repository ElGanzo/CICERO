package CICERO.Controller;

import java.sql.*;

public class DBManager {

    Connection C = DriverManager.getConnection("", "", "");
    Statement ST = C.createStatement();
    String q = "SELECT * FROM Ciceroni";
    ResultSet rs = ST.executeQuery(q);

    public DBManager() throws SQLException {
    }
}
