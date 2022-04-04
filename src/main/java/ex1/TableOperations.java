package ex1;

import java.sql.*;
import java.util.Scanner;

public class TableOperations {
    public void createDatabase(Statement stm) throws SQLException {
        String sqlDatabase = "CREATE DATABASE IF NOT EXISTS RISTORANTE";
        stm.executeUpdate(sqlDatabase);
    }

    public void createTablePR(Statement stm) throws SQLException {
        String sqlTable = "CREATE TABLE IF NOT EXISTS RISTORANTE.PrenotazioneRistorante " +
                "(Cognome VARCHAR(255) NOT NULL, " +
                " Data Date NOT NULL," +
                " NumeroPersone INT NOT NULL," +
                " Cellulare INT NOT NULL," +
                " PRIMARY KEY(Cognome, Data))";
        stm.executeUpdate(sqlTable);
    }

    public void createTableTA(Statement stm) throws SQLException {
        String sqlTable = "CREATE TABLE IF NOT EXISTS RISTORANTE.Tavolo " +
                "(Numero VARCHAR(255) NOT NULL, " +
                " Capienza INT NOT NULL," +
                " PRIMARY KEY(Numero))";
        stm.executeUpdate(sqlTable);
    }

    public void readTables(Statement stm) throws SQLException {
        String sqlRead = "SELECT Numero FROM RISTORANTE.Tavolo";
        ResultSet resSet = stm.executeQuery(sqlRead);
    }

    public boolean insertPre(Connection con, Ristorante r, String cognome, Date data, int numeroPersone, String cellulare) throws SQLException {
        boolean disp = r.richiestaPrenotazione(cognome, data, numeroPersone, cellulare);
        if (disp) {
            String sqlInsert = "INSERT INTO RISTORANTE.PrenotazioneRistorante (Cognome, Data, NumeroPersone, Cellulare) VALUES (?, ?, ?, ?);";
            PreparedStatement ins = con.prepareStatement(sqlInsert);
            ins.setString(1, cognome);
            ins.setDate(2, data);
            ins.setInt(3, numeroPersone);
            ins.setString(4, cellulare);
            ins.executeUpdate();

            return true;
        } else {
            return false;
        }
    }

    public void insertTav(Connection con, Ristorante r, String numero, int capienza) throws SQLException {
        r.aggTable(new Tavolo(numero, capienza));
        String sqlInsert = "INSERT INTO RISTORANTE.Tavolo(Numero, Capienza) VALUES (?, ?);";
        PreparedStatement ins = con.prepareStatement(sqlInsert);
        ins.setString(1, numero);
        ins.setInt(2, capienza);
        ins.executeUpdate();
    }

    public void deletePre(Connection con, Ristorante r) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Write surname and date of the reservation that you want to remove:");
        String surname = in.next();
        Date date = Date.valueOf(in.next());

        for (Tavolo t: r.getTables()) {
            for (PrenotazioneRistorante p: t.getPrenotazioni()) {
                if (p.getCognome().equals(surname) && (p.getData().equals(date))) {
                    t.getPrenotazioni().remove(p);
                    break;
                }
            }
        }

        String sqlDelete = "DELETE FROM RISTORANTE.PrenotazioneRistorante WHERE Cognome = ? AND Data = ?";
        PreparedStatement del = con.prepareStatement(sqlDelete);
        del.setString(1, surname);
        del.setDate(2, date);
        del.executeUpdate();
    }

    public void deleteTav(Connection con, Ristorante r) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Write number of table that you want to remove:");
        String number = in.next();

        for (Tavolo t: r.getTables()) {
            if (t.getNumero().equals(number)) {
                r.rimTable(t);
            }
        }

        String sqlDelete = "DELETE FROM RISTORANTE.Tavolo WHERE Numero = ?";
        PreparedStatement del = con.prepareStatement(sqlDelete);
        del.setString(1, number);
        del.executeUpdate();
    }

    public String readPre(Statement stm) throws SQLException {
        String sqlRead = "SELECT * FROM RISTORANTE.PrenotazioneRistorante";
        ResultSet resSet = stm.executeQuery(sqlRead);
        ResultSetMetaData md = resSet.getMetaData();

        String res = "| Cognome | Data | NumeroPersone | Cellulare | \n";
        while(resSet.next()) {
            for (int i = 1; i <= md.getColumnCount(); i++) {
                res += "|" + resSet.getString(i) + " | ";
            }
            res += "\n";
        }
        return res;
    }

    public String readTav(Statement stm) throws SQLException {
        String sqlRead = "SELECT * FROM RISTORANTE.Tavolo";
        ResultSet resSet = stm.executeQuery(sqlRead);
        ResultSetMetaData md = resSet.getMetaData();

        String res = "| Numero | Capienza | \n";
        while(resSet.next()) {
            for (int i = 1; i <= md.getColumnCount(); i++) {
                res += "|" + resSet.getString(i) + " | ";
            }
            res += "\n";
        }
        return res;
    }
}
