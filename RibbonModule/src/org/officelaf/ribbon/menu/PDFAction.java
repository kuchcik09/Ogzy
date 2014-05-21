package org.officelaf.ribbon.menu;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.gui.eksport.JasperPrinter;
import org.gui.grupy_cwiczeniowe.GroupsListTopComponent;
import org.gui.przedmioty.PokazPrzedmiotyTopComponent;
import org.gui.schematy.SchemeTopComponent;
import org.gui.studenci.StudentsListTopComponent;
import org.openide.windows.TopComponent;

public class PDFAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        TopComponent top = TopComponent.getRegistry().getActivated();

        if (top instanceof StudentsListTopComponent) {
            JasperPrinter.printAllStudents();
        } else if (top instanceof GroupsListTopComponent) {
            JasperPrinter.printAllGroups();
        } else if (top instanceof PokazPrzedmiotyTopComponent) {
            JasperPrinter.printAllSubjects();
        } else if (top instanceof SchemeTopComponent) {
            JasperPrinter.printAllSchemas();
        }

    }

}
