/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.database.SqlConnection;

/**
 *
 * @author Mariushrek
 * @edited Michał
 */
public class Termin {

    public enum DZIEN_TYG {

        Poniedzialek, Wtorek, Sroda, Czwartek, Piatek, Sobota, Niedziela
    }

    private int id;
    private GrupaCwiczeniowa grupa;
    private DZIEN_TYG dzien_tygodnia;
    private String godzina_start;
    private String godzina_stop;

    public Termin(int id, GrupaCwiczeniowa grupa, DZIEN_TYG dzien_tygodnia, String godzina_start, String godzina_stop) {
        this.id = id;
        this.grupa = grupa;
        this.dzien_tygodnia = dzien_tygodnia;
        this.godzina_start = godzina_start;
        this.godzina_stop = godzina_stop;
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
     * @return the grupa
     */
    public GrupaCwiczeniowa getGrupa() {
        return grupa;
    }

    /**
     * @param grupa the grupa to set
     */
    public void setGrupa(GrupaCwiczeniowa grupa) {
        this.grupa = grupa;
    }

    /**
     * @return the dzien_tygodnia
     */
    public DZIEN_TYG getDzien_tygodnia() {
        return dzien_tygodnia;
    }

    /**
     * @param dzien_tygodnia the dzien_tygodnia to set
     */
    public void setDzien_tygodnia(DZIEN_TYG dzien_tygodnia) {
        this.dzien_tygodnia = dzien_tygodnia;
    }

    /**
     * @return the godzina_start
     */
    public String getGodzina_start() {
        return godzina_start;
    }

    /**
     * @param godzina_start the godzina_start to set
     */
    public void setGodzina_start(String godzina_start) {
        this.godzina_start = godzina_start;
    }

    /**
     * @return the godzina_stop
     */
    public String getGodzina_stop() {
        return godzina_stop;
    }

    /**
     * @param godzina_stop the godzina_stop to set
     */
    public void setGodzina_stop(String godzina_stop) {
        this.godzina_stop = godzina_stop;
    }

    public static LinkedList<Termin> getAllTerms() {
        Connection conn = null;
        try {
            conn = SqlConnection.getInstance().getSqlConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT T.id,T.id_grupa_cwiczeniowa,T.dzien_tygodnia,T.godzina_start,"
                    + "T.godzina_stop,G.id_przedmiot,G.nazwa FROM terminy AS T JOIN grupa_cwiczeniowa AS G ON "
                    + "T.id_grupa_cwiczeniowa=G.id");
            LinkedList<Termin> terminy = new LinkedList<Termin>();
            while (rs.next()) {
                String start = rs.getString(4);
                String stop = rs.getString(5);

                terminy.addLast(new Termin(rs.getInt(1),
                        new GrupaCwiczeniowa(rs.getInt(2), rs.getString(7), Przedmiot.getPrzedmiot(rs.getInt(6))),
                        Termin.DZIEN_TYG.values()[rs.getInt(3)], start,
                        stop));
            }
            st.close();
            return terminy;
        } catch (SQLException ex) {
            Logger.getLogger(db.models.Termin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //ex.printStackTrace();
                }
            }
        }

        return null;
    }

    public static void dodajTermin(Termin term) {
        Connection conn = null;
        try {
            conn = SqlConnection.getInstance().getSqlConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO terminy(id_grupa_cwiczeniowa,dzien_tygodnia,godzina_start,godzina_stop)"
                    + " VALUES(" + term.getGrupa().getId() + "," + term.dzien_tygodnia.ordinal() + ",'" + term.godzina_start + "','" + term.godzina_stop + "')");
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Termin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //ex.printStackTrace();
                }
            }
        }

    }

    public static void usunTermin(DZIEN_TYG tyg, String godzina_start, String godzina_stop) {
        Connection conn = null;
        try {
            conn = SqlConnection.getInstance().getSqlConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM terminy where dzien_tygodnia=" + tyg.ordinal() + " AND godzina_start ='"
                    + godzina_start + "' AND godzina_stop ='" + godzina_stop + "'");
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(Termin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //ex.printStackTrace();
                }
            }
        }

    }

    public static List<Termin> getByGroup(int id) {
        Connection conn = null;
        try {
            conn = SqlConnection.getInstance().getSqlConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT dzien_tygodnia, godzina_start, godzina_stop FROM terminy WHERE id_grupa_cwiczeniowa=" + id);
            List<Termin> terms = new ArrayList<Termin>();

            while (rs.next()) {
                terms.add(new Termin(0, null, DZIEN_TYG.values()[rs.getInt(1)], rs.getString(2), rs.getString(3)));
            }
            st.close();
            return terms;

        } catch (SQLException ex) {
            Logger.getLogger(Termin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //ex.printStackTrace();
                }
            }
        }

        return null;
    }

}
