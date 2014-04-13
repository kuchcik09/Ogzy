/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gui.grupy_cwiczeniowe;

import org.database.models.GrupaCwiczeniowa;
import org.database.models.Przedmiot;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.gui.MainTopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.officelaf.listeners.TopComponentsManagerListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.gui//GroupsList//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "GroupsListTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "rightSlidingSide", openAtStartup = false)
@ActionID(category = "Window", id = "org.gui.GroupsListTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_GroupsListAction",
        preferredID = "GroupsListTopComponent"
)
@Messages({
    "CTL_GroupsListAction=Lista grupć wiczeniowych",
    "CTL_GroupsListTopComponent=Grupy",
    "HINT_GroupsListTopComponent=Okno zawiera listę grup ćwiczeniowych"
})
public final class GroupsListTopComponent extends TopComponent {

    public GroupsListTopComponent() {
        initComponents();
        setName(Bundle.CTL_GroupsListTopComponent());
        setToolTipText(Bundle.HINT_GroupsListTopComponent());
        tabelaGrupCwiczeniowych.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setListener();
    }

    private void setListener() {
        tabelaGrupCwiczeniowych.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    GroupsListTopComponent window = (GroupsListTopComponent) WindowManager.getDefault().findTopComponent("GroupsListTopComponent");
                    String nazwa = table.getValueAt(row, 1).toString();
                    Przedmiot przedmiot = (Przedmiot) table.getValueAt(row, 2);
                    DodajEdytujGrupeCwiczeniowaPanel panel = new DodajEdytujGrupeCwiczeniowaPanel(nazwa, przedmiot);
                    Object[] options = {
                        "Edytuj", "Anuluj"
                    };
                    int input = JOptionPane.showOptionDialog(window, panel, "Edytuj grupę ćwiczeniową", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (input == JOptionPane.OK_OPTION) {
                        panel.editGroup((Integer) table.getValueAt(row, 0));
                        MainTopComponent maintop = (MainTopComponent) WindowManager.getDefault().findTopComponent("MainTopComponent");
                        maintop.componentOpened(); // odświeżenie listy terminów
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaGrupCwiczeniowych = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        tabelaGrupCwiczeniowych.setModel(new GroupsTableModel());
        tabelaGrupCwiczeniowych.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaGrupCwiczeniowychMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaGrupCwiczeniowych);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaGrupCwiczeniowychMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaGrupCwiczeniowychMouseClicked
        TopComponentsManagerListener.GroupsListTopComponentActivated(this);
    }//GEN-LAST:event_tabelaGrupCwiczeniowychMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaGrupCwiczeniowych;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
        GroupsListTopComponent window = this;
        TableModel model = window.getTabelaGrupCwiczeniowych().getModel();
        clearTable((DefaultTableModel) window.getTabelaGrupCwiczeniowych().getModel());
        List<GrupaCwiczeniowa> list = GrupaCwiczeniowa.getAll();
        int rowId = 0;
        for (GrupaCwiczeniowa grupa : list) {
            model.setValueAt(grupa.getId(), rowId, 0);
            model.setValueAt(grupa.getNazwa(), rowId, 1);
            model.setValueAt(grupa.getPrzedmiot(), rowId, 2);
            rowId++;
        }
        hideIdColumn();
    }

    private void hideIdColumn() {
        TableColumn column = tabelaGrupCwiczeniowych.getColumn("Id");
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
    }

    private void clearTable(DefaultTableModel model) {
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            model.removeRow(0);
        }
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    public JTable getTabelaGrupCwiczeniowych() {
        return tabelaGrupCwiczeniowych;
    }

}
