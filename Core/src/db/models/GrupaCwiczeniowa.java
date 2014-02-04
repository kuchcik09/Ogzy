/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.database.SqlConnection;

/**
 *
 * @author Marcin
 */
public class GrupaCwiczeniowa {

    private int id;
    private String nazwa;
    private Przedmiot przedmiot;

    public GrupaCwiczeniowa(int id, String nazwa, Przedmiot przedmiot) {
        this.id = id;
        this.nazwa = nazwa;
        this.przedmiot = przedmiot;
    }

    public GrupaCwiczeniowa() {

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nazwa
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * @param nazwa the nazwa to set
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * @return the przedmiot
     */
    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    /**
     * @param przedmiot the przedmiot to set
     */
    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }

    @Override
    public String toString() {
        //rozwazanie TYMCZASOWE dla celu uzupelnienia comboboxa encjami grup cwiczeniowych (podobnie jak dla przedmiotu)
        return nazwa + " " + przedmiot.getNazwa();
    }

    public static List<GrupaCwiczeniowa> getAll() {
        List<GrupaCwiczeniowa> grupyCw = new ArrayList<GrupaCwiczeniowa>();
        Connection conn = null;
        try {
            conn = SqlConnection.getInstance().getSqlConnection();

            PreparedStatement prepStmt = conn.prepareStatement(
                    "SELECT G.id, G.nazwa, P.id, P.nazwa, P.typ_oceniania, P.rok_akademicki_start, "
                    + "P.semestr FROM grupa_cwiczeniowa as G join przedmioty as P on G.id_przedmiot=P.id");
            ResultSet resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select G.id from przedmioty as P join grupa_ocen as G on G.id=P.id_grupa_ocen where P.id=" + resultSet.getInt(3));
                rs.next();

                GrupaOcen go = GrupaOcen.getGrupaOcen(rs.getInt(1));

                st.close();

                GrupaCwiczeniowa grupaCw = new GrupaCwiczeniowa(resultSet.getInt(1), resultSet.getString(2),
                        new Przedmiot(resultSet.getInt(3), resultSet.getString(4), go,
                                TYP_OCENIANIA.values()[resultSet.getInt(5)], resultSet.getInt(6),
                                SEMESTR.values()[resultSet.getInt(7)]));

                grupyCw.add(grupaCw);
            }
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    // Logger.getLogger(GrupaCwiczeniowa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return grupyCw;
    }

    public static List<GrupaCwiczeniowa> getAllForStudent(Integer studentId) {
        List<GrupaCwiczeniowa> grupyCw = new ArrayList<GrupaCwiczeniowa>();
        Connection conn = null;
        try {
            conn = SqlConnection.getInstance().getSqlConnection();
            PreparedStatement prepStmt = conn.prepareStatement(
                    "SELECT G.id, G.nazwa, P.id, P.nazwa, P.typ_oceniania, P.rok_akademicki_start, "
                    + "P.semestr FROM grupa_cwiczeniowa as G join przedmioty as P on G.id_przedmiot=P.id WHERE g.id IN (SELECT id_grupa_cwiczeniowa FROM grupa_student WHERE id_student = ?)");
            prepStmt.setInt(1, studentId);
            ResultSet resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select G.id from przedmioty as P join grupa_ocen as G on G.id=P.id_grupa_ocen where P.id=" + resultSet.getInt(3));
                rs.next();

                GrupaOcen go = GrupaOcen.getGrupaOcen(rs.getInt(1));

                GrupaCwiczeniowa grupaCw = new GrupaCwiczeniowa(resultSet.getInt(1), resultSet.getString(2),
                        new Przedmiot(resultSet.getInt(3), resultSet.getString(4), go,
                                TYP_OCENIANIA.values()[resultSet.getInt(5)], resultSet.getInt(6),
                                SEMESTR.values()[resultSet.getInt(7)]));

                grupyCw.add(grupaCw);
            }
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    // Logger.getLogger(GrupaCwiczeniowa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return grupyCw;
    }

    /**
     * metoda zwraca grupy na które student nie jest zapisany, a moze sie
     * zapisac
     *
     * @return
     */
    public static List<GrupaCwiczeniowa> getAllPossibleForStudent(Integer studentId) {
        List<GrupaCwiczeniowa> grupyCw = new ArrayList<GrupaCwiczeniowa>();
        Connection conn = null;
        try {
            conn = SqlConnection.getInstance().getSqlConnection();
            PreparedStatement prepStmt = conn.prepareStatement(
                    "SELECT G.id, G.nazwa, P.id, P.nazwa, P.typ_oceniania, P.rok_akademicki_start, "
                    + "P.semestr FROM grupa_cwiczeniowa as G join przedmioty as P on G.id_przedmiot=P.id WHERE g.id NOT IN (SELECT id_grupa_cwiczeniowa FROM grupa_student WHERE id_student = ?)");
            prepStmt.setInt(1, studentId);
            ResultSet resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select G.id from przedmioty as P join grupa_ocen as G on G.id=P.id_grupa_ocen where P.id=" + resultSet.getInt(3));
                rs.next();

                GrupaOcen go = GrupaOcen.getGrupaOcen(rs.getInt(1));

                st.close();

                GrupaCwiczeniowa grupaCw = new GrupaCwiczeniowa(resultSet.getInt(1), resultSet.getString(2),
                        new Przedmiot(resultSet.getInt(3), resultSet.getString(4), go,
                                TYP_OCENIANIA.values()[resultSet.getInt(5)], resultSet.getInt(6),
                                SEMESTR.values()[resultSet.getInt(7)]));

                grupyCw.add(grupaCw);
            }
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    // Logger.getLogger(GrupaCwiczeniowa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return grupyCw;
    }

    public static void add(String nazwa, int przedmiotId) {
        Connection conn = null;
        try {
            conn = SqlConnection.getInstance().getSqlConnection();
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO grupa_cwiczeniowa values (?, ?, ?)");
            prepStmt.setInt(2, przedmiotId);
            prepStmt.setString(3, nazwa);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    // Logger.getLogger(GrupaCwiczeniowa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void edit(GrupaCwiczeniowa grupaCw) {
        Connection conn = null;
        try {
            conn = SqlConnection.getInstance().getSqlConnection();
            PreparedStatement prepStmt = conn.prepareStatement(
                    "UPDATE grupa_cwiczeniowa SET nazwa = ?,id_przedmiot = ? WHERE id = ?");
            prepStmt.setInt(3, grupaCw.getId());
            prepStmt.setString(1, grupaCw.getNazwa());
            prepStmt.setInt(2, grupaCw.getPrzedmiot().getId());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    // Logger.getLogger(GrupaCwiczeniowa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void delete(int id) {
        Connection conn = null;
        try {
            conn = SqlConnection.getInstance().getSqlConnection();
            PreparedStatement prepStmt = conn.prepareStatement(
                    "DELETE FROM grupa_cwiczeniowa WHERE id = ?");
            prepStmt.setInt(1, id);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    // Logger.getLogger(GrupaCwiczeniowa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
