package org.officelaf.ribbon.eksport;

import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractAction;
import org.database.models.Przedmiot;
import org.database.models.Termin;
import org.gui.MainTopComponent;
import org.gui.eksport.JasperPrinter;
import org.gui.grupy_cwiczeniowe.GroupsListTopComponent;
import org.gui.grupy_cwiczeniowe.GrupaCwiczeniowaTopComponent;
import org.gui.obecnosci.PresenceTableTopComponent;
import org.gui.oceny.NotesTableTopComponent;
import org.gui.oceny.OcenyKoncoweTopComponent;
import org.gui.oceny.OcenyMainTopComponent;
import org.gui.przedmioty.PokazPrzedmiotyTopComponent;
import org.gui.przedmioty.UniversalFunctions;
import org.gui.schematy.SchemeTopComponent;
import org.gui.studenci.StudentsListTopComponent;
import org.openide.windows.TopComponent;
import org.pojos.ObecnoscDTO;
import org.pojos.PlanZajecDTO;
import org.pojos.PrzedmiotDTO;

public class EksportAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        TopComponent top = TopComponent.getRegistry().getActivated();

        if (top instanceof StudentsListTopComponent) {
            JasperPrinter.printAllStudents();
        } else if (top instanceof GroupsListTopComponent) {
            JasperPrinter.printAllGroups();
        } else if (top instanceof PokazPrzedmiotyTopComponent) {
            boolean archiwalne = ((PokazPrzedmiotyTopComponent) top).isArchiwalne_przedmioty();
            List<Przedmiot> list;
            if (archiwalne) {
                list = Przedmiot.getPrzedmioty(0);
            } else {
                list = Przedmiot.getPrzedmioty(UniversalFunctions.startRokuAkademickiego());
            }
            List<PrzedmiotDTO> dtosList = new ArrayList<PrzedmiotDTO>();
            for (Przedmiot przedmiot : list) {
                dtosList.add(new PrzedmiotDTO(przedmiot.getId(), przedmiot.getNazwa(), przedmiot.getGrupaOcen().getNazwa(), przedmiot.getTypOceniania(), przedmiot.getRokAkademicki(), przedmiot.getSemestr()));
            }
            JasperPrinter.printAllSubjects(dtosList);
        } else if (top instanceof SchemeTopComponent) {
            JasperPrinter.printAllSchemas();
        } else if (top instanceof MainTopComponent) {
            List<PlanZajecDTO> list = new ArrayList<PlanZajecDTO>();
            LinkedList<Termin> allTerms = Termin.getAllTermsForYearAndSemester(); //pobieram wszystkie terminy

            int startScaleTime = 6; //od jakiej godziny zaczynam liczyć
            //tu jest 6-ta bo w pętli będę dodwać po 2 godziny

            Time tempToTable = new Time(startScaleTime, 0, 0); //ustalam startowy czas
        /*
             Pętla idzie 6-króków to daje od 8 do 20 składając w każdym kroku stringa do wyświetlania i dodając
             puste narazie wartości null do tabeli ustawając czas o 2godziny wiecej
             czyli poprostu inicjalizuje się tu pusta tabela
             */
            for (int i = 0; i <= 6; i++) {
                String temp = tempToTable.toString().substring(0, tempToTable.toString().length() - 3) + " - ";
                tempToTable.setTime(tempToTable.getTime() + 7200000); // 7200000 milisec = 1h
                list.add(new PlanZajecDTO(temp + tempToTable.toString().substring(0, tempToTable.toString().length() - 3)));
            }

            //teraz przechodząc po terminach dodaję odpowiednio grupy
            for (int i = 0; i < allTerms.size(); i++) {
                Termin term = allTerms.get(i);
                int rowIndex = (Integer.parseInt(term.getGodzina_start().substring(0, 2)) / 2) - 3;
                String s = "<html><div style=\"margin:5px;\"><b>" + term.getGrupa().getNazwa() + "</b><br>" + term.getGrupa().getPrzedmiot().getNazwa() + "</div></html>";

                if (term.getDzien_tygodnia().equals(Termin.DZIEN_TYG.Poniedzialek)) {
                    list.get(rowIndex).setPn(s);
                } else if (term.getDzien_tygodnia().equals(Termin.DZIEN_TYG.Wtorek)) {
                    list.get(rowIndex).setWt(s);
                } else if (term.getDzien_tygodnia().equals(Termin.DZIEN_TYG.Sroda)) {
                    list.get(rowIndex).setSr(s);
                } else if (term.getDzien_tygodnia().equals(Termin.DZIEN_TYG.Czwartek)) {
                    list.get(rowIndex).setCzw(s);
                } else if (term.getDzien_tygodnia().equals(Termin.DZIEN_TYG.Piatek)) {
                    list.get(rowIndex).setPt(s);
                } else if (term.getDzien_tygodnia().equals(Termin.DZIEN_TYG.Sobota)) {
                    list.get(rowIndex).setSb(s);
                } else if (term.getDzien_tygodnia().equals(Termin.DZIEN_TYG.Niedziela)) {
                    list.get(rowIndex).setNd(s);
                }
            }
            JasperPrinter.printMainTopComponent(list);
        } else if (top instanceof GrupaCwiczeniowaTopComponent) {
            JasperPrinter.printGrupaCwiczeniowaTopComponent();
        } else if (top instanceof PresenceTableTopComponent) {
            List<ObecnoscDTO> list = new ArrayList<ObecnoscDTO>();
            JasperPrinter.printPresenceTableTopComponent(list);
        } else if (top instanceof NotesTableTopComponent) {
            JasperPrinter.printNotesTableTopComponent();
        } else if (top instanceof OcenyKoncoweTopComponent) {
            JasperPrinter.printOcenyKoncoweTopComponent();
        } else if (top instanceof OcenyMainTopComponent) {
            JasperPrinter.printOcenyMainTopComponent();
        }

    }

}
