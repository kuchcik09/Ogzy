package org.database.intallation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.database.SqlConnection;
/**
 *
 * @author Mariushrek
 * 
 * Klasa umozliwiajaca utworzenie calej bazy danych od poczatku
 */
public class Database_install {
    private Statement stat = null; 
    public boolean Install_Database() {
        /*
            Tabela 'student'
        */
        try {
            Connection conn = SqlConnection.getInstance().getSqlConnection();
            stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS student( "
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " imie TEXT,"
                    + " nazwisko TEXT,"
                    + " email TEXT,"
                    + " indeks INTEGER)");
            System.out.println("Tabela 'student' utworzona pomyslnie");
        } catch (SQLException ex) {
            Logger.getLogger(Database_install.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
         /*
            Tabela 'grupa_ocen'
        */
        try {
            Connection conn = SqlConnection.getInstance().getSqlConnection();
            stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS grupa_ocen( "
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " sub_id INTEGER,"
                    + " nazwa TEXT,"
                    + " waga REAL,"
                    + " FOREIGN KEY(sub_id) REFERENCES grupa_ocen(id))");
            System.out.println("Tabela 'grupa_ocen' utworzona pomyslnie");
        } catch (SQLException ex) {
            Logger.getLogger(Database_install.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        /*
            Tabela 'przedmioty'
        */
        try {
            Connection conn = SqlConnection.getInstance().getSqlConnection();
            stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS przedmioty( "
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " id_grupa_ocen INTEGER,"
                    + " nazwa TEXT,"
                    + " typ_oceniania TEXT,"
                    + " rok_akademicki_start INTEGER,"
                    + " semestr TEXT,"
                    + " FOREIGN KEY(id_grupa_ocen) REFERENCES grupa_ocen(id))");
            System.out.println("Tabela 'przedmioty' utworzona pomyslnie");
        } catch (SQLException ex) {
            Logger.getLogger(Database_install.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        /*
            Tabela 'grupa_cwiczeniowa'
        */
        try {
            Connection conn = SqlConnection.getInstance().getSqlConnection();
            stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS grupa_cwiczeniowa( "
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " id_przedmiot INTEGER,"
                    + " nazwa TEXT,"
                    + " FOREIGN KEY(id_przedmiot) REFERENCES przedmioty(id))");
            System.out.println("Tabela 'grupa_cwiczeniowa' utworzona pomyslnie");
        } catch (SQLException ex) {
            Logger.getLogger(Database_install.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        /*
            Tabela 'grupa_student'
        */
        try {
            Connection conn = SqlConnection.getInstance().getSqlConnection();
            stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS grupa_student( "
                    + " id_student INTEGER,"
                    + " id_grupa_cwiczeniowa INTEGER,"
                    + " FOREIGN KEY(id_student) REFERENCES student(id),"
                    + " FOREIGN KEY(id_grupa_cwiczeniowa) REFERENCES grupa_cwiczeniowa(id))");
            System.out.println("Tabela 'grupa_student' utworzona pomyslnie");
        } catch (SQLException ex) {
            Logger.getLogger(Database_install.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        /*
            Tabela 'terminy'
        */
        try {
            Connection conn = SqlConnection.getInstance().getSqlConnection();
            stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS terminy( "
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " id_grupa_cwiczeniowa INTEGER,"
                    + " dzien_tygodnia TEXT,"
                    + " godzina_start TEXT,"
                    + " godzina_stop TEXT,"
                    + " FOREIGN KEY(id_grupa_cwiczeniowa ) REFERENCES grupa_cwiczeniowa(id))");
            System.out.println("Tabela 'terminy' utworzona pomyslnie");
        } catch (SQLException ex) {
            Logger.getLogger(Database_install.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        /*
            Tabela 'obecnosc'
        */
        try {
            Connection conn = SqlConnection.getInstance().getSqlConnection();
            stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS obecnosc( "
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " id_termin INTEGER,"
                    + " id_student INTEGER,"
                    + " data TEXT,"
                    + " obecnosc INTEGER"
                    + " FOREIGN KEY(id_termin ) REFERENCES grupa_terminy(id),"
                    + " FOREIGN KEY(id_student ) REFERENCES student(id))");
            System.out.println("Tabela 'obecnosc' utworzona pomyslnie");
        } catch (SQLException ex) {
            Logger.getLogger(Database_install.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        /*
            Tabela 'oceny'
        */
        try {
            Connection conn = SqlConnection.getInstance().getSqlConnection();
            stat = conn.createStatement();
            stat.execute("CREATE TABLE IF NOT EXISTS oceny( "
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " id_grupa_ocen INTEGER,"
                    + " id_grupa_cwiczeniowa INTEGER,"
                    + " id_student INTEGER,"
                    + " wartosc_oceny REAL,"
                    + " FOREIGN KEY(id_grupa_ocen ) REFERENCES grupa_ocen(id),"
                    + " FOREIGN KEY(id_grupa_cwiczeniowa ) REFERENCES grupa_cwiczeniowa(id),"
                    + " FOREIGN KEY(id_student ) REFERENCES student(id))");
            System.out.println("Tabela 'oceny' utworzona pomyslnie");
        } catch (SQLException ex) {
            Logger.getLogger(Database_install.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
