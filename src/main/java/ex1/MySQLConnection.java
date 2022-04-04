package ex1;

import utils.ReadProperties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;

public class MySQLConnection {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        ReadProperties rd = new ReadProperties();
        rd.read("application.properties");
        Class.forName(rd.getProperties().getProperty("mySqlUrl"));
        Connection con = DriverManager.getConnection(
                rd.getProperties().getProperty("urlDB"),
                rd.getProperties().getProperty("username"),
                rd.getProperties().getProperty("password"));

        ArrayList<Tavolo> tables = new ArrayList<>();
        Statement stm = con.createStatement();
        TableOperations op = new TableOperations();
        op.createDatabase(stm);
        op.createTablePR(stm);
        op.createTableTA(stm);
        Ristorante r = new Ristorante();
        /*
        op.insertTav(con, r, "1", 5);
        op.insertTav(con, r, "2", 4);
        op.insertTav(con, r, "3", 5);
        op.insertTav(con, r, "4", 6);
        op.insertTav(con, r, "5", 5);
        op.insertTav(con, r, "6", 5);
        op.insertTav(con, r, "7", 6);

        op.insertPre(con, r, "Smith", Date.valueOf("2022-04-11"), 5, "3100034252");
        op.insertPre(con, r, "Clinton", Date.valueOf("2022-04-10"), 5, "3110034252");
        op.insertPre(con, r, "Vegas", Date.valueOf("2022-04-10"), 4, "3100434252");
        op.insertPre(con, r, "York", Date.valueOf("2022-04-11"), 6, "3900034252");
        op.insertPre(con, r, "Adams", Date.valueOf("2022-04-11"), 6, "3770034252");
        */
        String res1 = op.readPre(stm);
        String res2 = op.readTav(stm);

        Files.writeString(Path.of("prenotazione.txt"), res1 + "\n" + res2);
    }
}
