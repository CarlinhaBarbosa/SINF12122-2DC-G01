package servidor;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBFactory {

    static final String URL = "jdbc:mysql://localhost:3306/" + "pushingpark";
    static final String USER = "teste";
    static final String PASS = "teste";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println(String.format(" Connection t o %s " + "succeded ! ", conn.getCatalog()));
            return conn;
        } catch (SQLException exc) {
            throw new RuntimeException(" ! ! ! ! E r r o r c o n n e c ti n g ! ! ! ! ", exc);
        }
    }
}
