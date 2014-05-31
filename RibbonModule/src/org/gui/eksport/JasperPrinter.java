package org.gui.eksport;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.database.SqlConnection;
import org.database.models.Obecnosc;
import org.openide.util.Exceptions;
import org.pojos.ObecnoscDTO;
import org.pojos.PlanZajecDTO;
import org.pojos.PrzedmiotDTO;

public class JasperPrinter {

    private static void print(String reportName) {
        String reportSource = "RibbonModule\\src\\org\\gui\\eksport\\" + reportName + ".jasper";
        //String reportDest = "RibbonModule\\src\\org\\gui\\eksport\\"+reportName+".html";

        Map<String, Object> params = new HashMap<String, Object>();

        try {
//            JasperReport jasperReport
//                    = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(
                            reportSource, params, SqlConnection.getInstance().getSqlConnection());

//            JasperExportManager.exportReportToHtmlFile(
//                    jasperPrint, reportDest);
            boolean exitOnClose = false;

            JasperViewer.viewReport(jasperPrint, exitOnClose);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (JRException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static void print(String reportName, Collection c) {
        String reportSource = "RibbonModule\\src\\org\\gui\\eksport\\" + reportName + ".jasper";
        //String reportDest = "RibbonModule\\src\\org\\gui\\eksport\\"+reportName+".html";

        Map<String, Object> params = new HashMap<String, Object>();

        try {
//            JasperReport jasperReport
//                    = JasperCompileManager.compileReport(reportSource);

            JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(c, false);
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(
                            reportSource, params, beanCollectionDataSource);

//            JasperExportManager.exportReportToHtmlFile(
//                    jasperPrint, reportDest);
            boolean exitOnClose = false;

            JasperViewer.viewReport(jasperPrint, exitOnClose);
        } catch (JRException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public static void printAllStudents() {
        print("AllStudents");
    }

    public static void printAllGroups() {
        print("AllGroups");
    }

    public static void printAllSubjects(List<PrzedmiotDTO> przedmioty) {
        //with params
        print("AllSubjects", przedmioty);
    }

    public static void printAllSchemas() {
        print("AllSchemas");
    }

    public static void printMainTopComponent(List<PlanZajecDTO> list) {
        print("PlanZajec", list);
    }

    public static void printGrupaCwiczeniowaTopComponent() {
        //with params
        print("PanelGlowny");
    }

    public static void printPresenceTableTopComponent(List<ObecnoscDTO> list) {
        //with params
        print("Obecnosc", list);
    }

    public static void printNotesTableTopComponent() {
        //with params
        print("Notes");
    }

    public static void printOcenyKoncoweTopComponent() {
        //with params
        print("OcenyKoncowe");
    }

    public static void printOcenyMainTopComponent() {
        //with params
        print("Oceny");
    }
}
