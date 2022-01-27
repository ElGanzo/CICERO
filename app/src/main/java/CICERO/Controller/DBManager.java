package CICERO.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
    Connection C = DriverManager.getConnection("", "", "");
    Statement ST = C.createStatement();
    String q = "SELECT * FROM Ciceroni";
    ResultSet rs = ST.executeQuery(q);
}
