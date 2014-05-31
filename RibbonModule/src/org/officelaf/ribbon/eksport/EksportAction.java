package org.officelaf.ribbon.eksport;

import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import org.database.models.Obecnosc;
import org.database.models.Oceny;
import org.database.models.Przedmiot;
import org.database.models.Student;
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
            GrupaCwiczeniowaTopComponent grupaComponent = ((GrupaCwiczeniowaTopComponent) top);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("nazwaGrupy", grupaComponent.getGroupName());
            params.put("przedmiot", grupaComponent.getSubjectName());
            params.put("schematOceniania", grupaComponent.getSchematOceniania());
            params.put("typOceniania", grupaComponent.getTypOceniania());
            params.put("grupaId", grupaComponent.getGrupa().getId());
            JasperPrinter.printGrupaCwiczeniowaTopComponent(params);
        } else if (top instanceof PresenceTableTopComponent) {
            PresenceTableTopComponent ptComponent = ((PresenceTableTopComponent) top);
            List<ObecnoscDTO> list = new ArrayList<ObecnoscDTO>();
            for (Student student : ptComponent.getStudents()) {
                List<Boolean> listaObecnosci = new ArrayList<Boolean>();
                for (int i = 0; i < ptComponent.getDates().size(); i++) {
                    listaObecnosci.add(ptComponent.znajdzObecnosc(Obecnosc.getObecnosci(ptComponent.getTermin()), ptComponent.getDates().get(i), student));
                }
                list.add(new ObecnoscDTO(student.getImie() + " " + student.getNazwisko(), listaObecnosci));
            }
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("title", ptComponent.getTitle());
            params.put("daty", ptComponent.getDates());

            JasperPrinter.printPresenceTableTopComponent(list, params);
        } else if (top instanceof NotesTableTopComponent) {
            NotesTableTopComponent notesComponent = ((NotesTableTopComponent) top);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("nazwaGrupy", notesComponent.getGrupaCwiczeniowa().getNazwa());
            params.put("nazwaPrzedmiotu", notesComponent.getGrupaCwiczeniowa().getPrzedmiot().getNazwa());
            params.put("schematOceniania", notesComponent.getGrupaCwiczeniowa().getPrzedmiot().getGrupaOcen().getNazwa());
            params.put("grupaId", notesComponent.getGrupaCwiczeniowa().getId());
            if (notesComponent.getStudentsTable().getSelectedRow() != -1) {
                //TODO poprawic raport i przesylane parametry
                params.put("grupaOcenId", notesComponent.getGrupaCwiczeniowa().getPrzedmiot().getGrupaOcen().getId());
                Student student = notesComponent.getLista_studentow().get(notesComponent.getStudentsTable().getSelectedRow());
                List<Oceny> oceny_studenta = Oceny.getOceny(student, notesComponent.getGrupaCwiczeniowa());
                List<List<Float>> oceny = new ArrayList<List<Float>>();
                for (int i = 0; i < notesComponent.getPodkategorie().size(); i++) {
                    List<Float> list = new ArrayList<Float>();
                    oceny.add(list);
                    for (Oceny o : oceny_studenta) {
                        if (o.getGrupaOcen().getId() == notesComponent.getPodkategorie().get(i).getId()) {
                            list.add(o.getWartoscOceny());
                        }
                    }
                }
                params.put("oceny", oceny);
            }
            JasperPrinter.printNotesTableTopComponent(params);
        } else if (top instanceof OcenyKoncoweTopComponent) {
            JasperPrinter.printOcenyKoncoweTopComponent();
        } else if (top instanceof OcenyMainTopComponent) {
            JasperPrinter.printOcenyMainTopComponent();
        }

    }

}
