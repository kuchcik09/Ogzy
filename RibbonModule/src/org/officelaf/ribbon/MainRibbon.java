package org.officelaf.ribbon;

import org.jvnet.flamingo.common.JCommandButton;
import org.jvnet.flamingo.common.icon.EmptyResizableIcon;
import org.jvnet.flamingo.common.icon.IcoWrapperResizableIcon;
import org.jvnet.flamingo.ribbon.*;
import org.jvnet.flamingo.ribbon.resize.CoreRibbonResizePolicies.Mid2Low;
import org.jvnet.flamingo.ribbon.resize.CoreRibbonResizePolicies.Mid2Mid;
import org.jvnet.flamingo.ribbon.resize.RibbonBandResizePolicy;
import org.netbeans.api.options.OptionsDisplayer;
import org.openide.LifecycleManager;
import org.openide.actions.*;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import org.jvnet.flamingo.common.RichTooltip;
import org.officelaf.ribbon.grupy_cwiczeniowe.DodajGrupeAction;
import org.officelaf.ribbon.grupy_cwiczeniowe.PokazGrupyAction;
import org.officelaf.ribbon.grupy_cwiczeniowe.UsunGrupeAction;
import org.officelaf.ribbon.menu.MailAction;
import org.officelaf.ribbon.menu.PDFAction;
import org.officelaf.ribbon.obecnosc.SprawdzObecnoscAction;
import org.officelaf.ribbon.obecnosc.UsunObecnoscAction;
import org.officelaf.ribbon.oceny.DodajSlupekAction;
import org.officelaf.ribbon.oceny.PodsumowanieAction;
import org.officelaf.ribbon.oceny.PokazTabeleOcenAction;
import org.officelaf.ribbon.oceny.UsunSlupekAction;
import org.officelaf.ribbon.plan.DodajTerminAction;
import org.officelaf.ribbon.plan.PokazPlanAction;
import org.officelaf.ribbon.plan.UsunTerminAction;
import org.officelaf.ribbon.przedmioty.DodajPrzedmiotAction;
import org.officelaf.ribbon.przedmioty.PokazPrzedmiotyAction;
import org.officelaf.ribbon.przedmioty.UsunPrzedmiotAction;
import org.officelaf.ribbon.schematy.DodajSchematAction;
import org.officelaf.ribbon.schematy.PokazSchematyAction;
import org.officelaf.ribbon.schematy.UsunSchematAction;
import org.officelaf.ribbon.studenci.DodajStudentaAction;
import org.officelaf.ribbon.studenci.OdepnijStudentaAction;
import org.officelaf.ribbon.studenci.PodepnijStudentaAction;
import org.officelaf.ribbon.studenci.PokazListeAction;
import org.officelaf.ribbon.studenci.UsunStudentaAction;
import org.openide.util.Exceptions;

public class MainRibbon extends JRibbon {

    public MainRibbon() {
    }

    public void setup() {

        RibbonTask homeTask = new RibbonTask("Narzędzia głowne", createHomeBands());
        RibbonTask subTask = new RibbonTask("Zarządzanie przedmiotami", createSubBands());
        RibbonTask peopleTask = new RibbonTask("Zarządzanie grupami", createPeopleBands());
        RibbonTask mailTask = new RibbonTask("Skrzynka pocztowa", createPeopleBands());
        
        addTask(homeTask);
        addTask(peopleTask);
        addTask(subTask);
        addTask(mailTask);
        
        createApplicationMenu();
    }

    private void createApplicationMenu() {
        
        RibbonApplicationMenu menu = new RibbonApplicationMenu();
        
        setApplicationMenu(new RibbonApplicationMenu());
    }
    
    private AbstractRibbonBand[] createSubBands(){
        AbstractRibbonBand[] bands = new AbstractRibbonBand[2];
        bands[0] = createSubjectsBand();
        bands[1] = createSchemeBand();
        
        return bands;
    }
    
    private AbstractRibbonBand[] createPeopleBands(){
        AbstractRibbonBand[] bands = new AbstractRibbonBand[2];
        bands[0] = createGroupsBand();//Opcje grup zajęciowych
        bands[1] = createStudentsBand();        
        return bands;
    }
    
    private AbstractRibbonBand[] createHomeBands() {

        AbstractRibbonBand[] bands = new AbstractRibbonBand[4];
        bands[0] = createPlansBand(); //Opcje planu zajec
        bands[1] = createNotesBand();
        bands[2] = createPresenceBand();
        bands[3] = createExportBand();
        return bands;
    }

    private JRibbonBand createPlansBand() {
        JRibbonBand band = new JRibbonBand(NbBundle.getMessage(MainRibbon.class, "PLAN_BAND"),
                new EmptyResizableIcon(16));
        
        IcoWrapperResizableIcon table_ico = null;
        IcoWrapperResizableIcon table_plus_ico = null;
        IcoWrapperResizableIcon table_minus_ico = null;

        try {
            table_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/table.ico"), new Dimension(32, 32));
            table_plus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/table_plus.ico"), new Dimension(32, 32));
            table_minus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/table_minus.ico"), new Dimension(32, 32));
        } catch (MalformedURLException ex) {
            Exceptions.printStackTrace(ex);
        }

        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Pokaż plan", "Ukazuje plan zajęć w tabeli", table_ico, null, new PokazPlanAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Dodaj termin", "Dodaje termin do planu zajęć", table_plus_ico, null, new DodajTerminAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Usuń termin", "Usuwa termin z planu zajęć", table_minus_ico, null, new UsunTerminAction()),
                RibbonElementPriority.TOP);

        band.setResizePolicies(Arrays.<RibbonBandResizePolicy>asList(
                new Mid2Mid(band.getControlPanel()),
                new Mid2Mid(band.getControlPanel()),
                new Mid2Low(band.getControlPanel())
        ));
        return band;
    }
    
    private JRibbonBand createSchemeBand(){
        JRibbonBand band = new JRibbonBand(NbBundle.getMessage(MainRibbon.class, "SCHEME_BAND"),
                new EmptyResizableIcon(16));
        
        IcoWrapperResizableIcon per_ico = null;
        IcoWrapperResizableIcon per1_ico = null;
        IcoWrapperResizableIcon per2_ico = null;
        try {
            per_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/per.ico"), new Dimension(32, 32));
            per1_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/per1.ico"), new Dimension(32, 32));
            per2_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/per2.ico"), new Dimension(32, 32));
        } catch (MalformedURLException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Pokaż schematy", "Ukazuje schematy w liście", per_ico, null, new PokazSchematyAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Dodaj schemat", "Dodaje schemat do bazy", per1_ico, null, new DodajSchematAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Usuń schemat", "Usuwa schemat z bazy", per2_ico, null, new UsunSchematAction()),
                RibbonElementPriority.TOP);
        
        band.setResizePolicies(Arrays.<RibbonBandResizePolicy>asList(
                new Mid2Mid(band.getControlPanel()),
                new Mid2Mid(band.getControlPanel()),
                new Mid2Low(band.getControlPanel())
        ));
        
        return band;
    }
    
    private JRibbonBand createSubjectsBand() {
        JRibbonBand band = new JRibbonBand(NbBundle.getMessage(MainRibbon.class, "SUBJECT_BAND"),
                new EmptyResizableIcon(16));

        IcoWrapperResizableIcon table_ico = null;
        IcoWrapperResizableIcon table_plus_ico = null;
        IcoWrapperResizableIcon table_minus_ico = null;

        try {
            table_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/subject.ico"), new Dimension(32, 32));
            table_plus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/subject_plus.ico"), new Dimension(32, 32));
            table_minus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/subject_minus.ico"), new Dimension(32, 32));
        } catch (MalformedURLException ex) {
            Exceptions.printStackTrace(ex);
        }

        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Pokaż przedmioty", "Ukazuje przedmioty w tabeli", table_ico, null, new PokazPrzedmiotyAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Dodaj przedmiot", "Dodaje przedmioty do bazy", table_plus_ico, null, new DodajPrzedmiotAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Usuń przedmiot", "Usuwa przedmioty z bazy", table_minus_ico, null, new UsunPrzedmiotAction()),
                RibbonElementPriority.TOP);

        band.setResizePolicies(Arrays.<RibbonBandResizePolicy>asList(
                new Mid2Mid(band.getControlPanel()),
                new Mid2Mid(band.getControlPanel()),
                new Mid2Low(band.getControlPanel())
        ));
        return band;
    }
    
    private JRibbonBand createGroupsBand() {
        JRibbonBand band = new JRibbonBand(NbBundle.getMessage(MainRibbon.class, "GROUP_BAND"),
                new EmptyResizableIcon(16));

        IcoWrapperResizableIcon group_ico = null;
        IcoWrapperResizableIcon group_plus_ico = null;
        IcoWrapperResizableIcon group_minus_ico = null;

        try {
            group_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/group.ico"), new Dimension(32, 32));
            group_plus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/group_plus.ico"), new Dimension(32, 32));
            group_minus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/group_minus.ico"), new Dimension(32, 32));
        } catch (MalformedURLException ex) {
            Exceptions.printStackTrace(ex);
        }

        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Pokaż grupy", "Ukazuje grupę w nowym oknie", group_ico, null, new PokazGrupyAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Dodaj grupę", "Dodaję grupę do systemu", group_plus_ico, null, new DodajGrupeAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Usuń grupę", "Usuwa grupę z systemu", group_minus_ico, null, new UsunGrupeAction()),
                RibbonElementPriority.TOP);

        band.setResizePolicies(Arrays.<RibbonBandResizePolicy>asList(
                new Mid2Mid(band.getControlPanel()),
                new Mid2Mid(band.getControlPanel()),
                new Mid2Low(band.getControlPanel())
        ));
        return band;
    }

    private JRibbonBand createStudentsBand() {
        JRibbonBand band = new JRibbonBand(NbBundle.getMessage(MainRibbon.class, "STUDENTS_BAND"),
                new EmptyResizableIcon(16));

        IcoWrapperResizableIcon student_ico = null;
        IcoWrapperResizableIcon student_plus_ico = null;
        IcoWrapperResizableIcon student_minus_ico = null;
        IcoWrapperResizableIcon student_add_ico = null;
        IcoWrapperResizableIcon student_delete_ico = null;

        try {
            student_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/student.ico"), new Dimension(32, 32));
            student_plus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/student_plus.ico"), new Dimension(32, 32));
            student_minus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/student_minus.ico"), new Dimension(32, 32));
            student_add_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/student_add.ico"), new Dimension(32, 32));
            student_delete_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/student_delete.ico"), new Dimension(32, 32));
        } catch (MalformedURLException ex) {
            Exceptions.printStackTrace(ex);
        }

        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Pokaż listę", "Ukazuje wszystkich studentów w nowym oknie", student_ico, null, new PokazListeAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Dodaj studenta", "Dodaję studenta do systemu", student_plus_ico, null, new DodajStudentaAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Usuń studenta", "Usuwa studenta z systemu", student_minus_ico, null, new UsunStudentaAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Podepnij studenta", "Podpina studenta do grupy ćwiczeniowej", student_add_ico, null, new PodepnijStudentaAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Odepnij studenta", "Odpina studenta z grupy ćwiczeniowej", student_delete_ico, null, new OdepnijStudentaAction()),
                RibbonElementPriority.TOP);

        band.setResizePolicies(Arrays.<RibbonBandResizePolicy>asList(
                new Mid2Mid(band.getControlPanel()),
                new Mid2Mid(band.getControlPanel()),
                new Mid2Mid(band.getControlPanel()),
                new Mid2Mid(band.getControlPanel())
        ));
        return band;
    }

    private JRibbonBand createNotesBand() {
        JRibbonBand band = new JRibbonBand(NbBundle.getMessage(MainRibbon.class, "NOTES_BAND"),
                new EmptyResizableIcon(16));

        IcoWrapperResizableIcon notes_ico = null;
        IcoWrapperResizableIcon notes_group_plus_ico = null;
        IcoWrapperResizableIcon notes_group_minus_ico = null;
        IcoWrapperResizableIcon notes_plus_ico = null;
        IcoWrapperResizableIcon notes_minus_ico = null;
        IcoWrapperResizableIcon notes_last_ico = null;

        try {
            notes_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/notes.ico"), new Dimension(32, 32));
            notes_group_plus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/notes_group_plus.ico"), new Dimension(32, 32));
            notes_group_minus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/notes_group_minus.ico"), new Dimension(32, 32));
            notes_plus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/notes_plus.ico"), new Dimension(32, 32));
            notes_minus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/notes_minus.ico"), new Dimension(32, 32));
            notes_last_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/notes_last.ico"), new Dimension(32, 32));
        } catch (MalformedURLException ex) {
            Exceptions.printStackTrace(ex);
        }

        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Pokaż tabele", "Ukazuje tabele wszystkich ocen w grupie", notes_ico, null, new PokazTabeleOcenAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Dodaj słupek", "Dodaje słupek ocen do kategorii w grupie ocen", notes_plus_ico, null, new DodajSlupekAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Usuń słupek", "Usuwa słupek ocen z kategorii w grupie ćwiczeniowej", notes_minus_ico, null,new UsunSlupekAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Podsumuj", "Podsumowuje i wylicza oceny końcowe", notes_last_ico, null, new PodsumowanieAction()),
                RibbonElementPriority.TOP);

        band.setResizePolicies(Arrays.<RibbonBandResizePolicy>asList(
                new Mid2Mid(band.getControlPanel()),
                new Mid2Mid(band.getControlPanel()),
                new Mid2Low(band.getControlPanel())
        ));
        return band;
    }

    private JRibbonBand createPresenceBand() {
        JRibbonBand band = new JRibbonBand(NbBundle.getMessage(MainRibbon.class, "PRESENCE_BAND"),
                new EmptyResizableIcon(16));

        IcoWrapperResizableIcon presence_table_ico = null;
        IcoWrapperResizableIcon presence_minus_ico = null;

        try {
            presence_table_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/presence.ico"), new Dimension(32, 32));
            presence_minus_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/presence_minus.ico"), new Dimension(32, 32));
        } catch (MalformedURLException ex) {
            Exceptions.printStackTrace(ex);
        }

        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Sprawdź ocebność", "Dodaje słupek obecności z dzisiejszą datą", presence_table_ico, null, new SprawdzObecnoscAction()),
                RibbonElementPriority.TOP);
        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Usuń ocebność", "Usuwa zaznaczony słupek obecności", presence_minus_ico, null, new UsunObecnoscAction()),
                RibbonElementPriority.TOP);

        band.setResizePolicies(Arrays.<RibbonBandResizePolicy>asList(
                new Mid2Mid(band.getControlPanel()),
                new Mid2Mid(band.getControlPanel()),
                new Mid2Low(band.getControlPanel())
        ));
        return band;
    }

    private AbstractRibbonBand[] createMailBands() {
        AbstractRibbonBand[] bands = new AbstractRibbonBand[1];
        bands[0] = new JRibbonBand("Test", null);
        return bands;
    }
    
    private JRibbonBand createExportBand() {
        JRibbonBand band = new JRibbonBand(NbBundle.getMessage(MainRibbon.class, "EXPORT_BAND"),
                new EmptyResizableIcon(16));
        
        IcoWrapperResizableIcon pdf_ico = null;
        
        try {
            pdf_ico = IcoWrapperResizableIcon.getIcon(new URL(new URL("file:"), "./RibbonModule/src/org/officelaf/icons/images/pdf.ico"), new Dimension(32, 32));
        } catch (MalformedURLException ex) {
            Exceptions.printStackTrace(ex);
        }

        band.addCommandButton(
                new BoundCommandButton(JCommandButton.CommandButtonKind.ACTION_ONLY, "Eksport do PDF", "Eksportuje widok do PDF", pdf_ico, null, new PokazPlanAction()),
                RibbonElementPriority.TOP);

        band.setResizePolicies(Arrays.<RibbonBandResizePolicy>asList(
                new Mid2Mid(band.getControlPanel()),
                new Mid2Mid(band.getControlPanel()),
                new Mid2Low(band.getControlPanel())
        ));
        return band;
    }
}
