/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gui.oceny;

import org.database.models.GrupaCwiczeniowa;
import org.database.models.GrupaOcen;
import org.database.models.Przedmiot;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.gui.oceny//OcenyMain//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "OcenyMainTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gui.oceny.OcenyMainTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_OcenyMainAction",
        preferredID = "OcenyMainTopComponent"
)
@Messages({
    "CTL_OcenyMainAction=OcenyMain",
    "CTL_OcenyMainTopComponent=OcenyMain Window",
    "HINT_OcenyMainTopComponent=This is a OcenyMain window"
})
public final class OcenyMainTopComponent extends TopComponent {
    private GrupaCwiczeniowa grupa = null;
    public OcenyMainTopComponent() {
        initComponents();
        setName(Bundle.CTL_OcenyMainTopComponent());
        setToolTipText(Bundle.HINT_OcenyMainTopComponent());
        
        //OpiszOkno();
        OrganizujTabele_Student();
        NazwyRzedow_Oceny();
        OrganizujTabele_Oceny();
    }

    public void setGrupa(GrupaCwiczeniowa f_grupa) {
        //this.grupa = f_grupa;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        przedmiot_nazwa = new javax.swing.JLabel();
        grupa_nazwa = new javax.swing.JLabel();
        schemat_nazwa = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        students_list = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        students_marks = new javax.swing.JTable();

        przedmiot_nazwa.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(przedmiot_nazwa, org.openide.util.NbBundle.getMessage(OcenyMainTopComponent.class, "OcenyMainTopComponent.przedmiot_nazwa.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(grupa_nazwa, org.openide.util.NbBundle.getMessage(OcenyMainTopComponent.class, "OcenyMainTopComponent.grupa_nazwa.text")); // NOI18N

        schemat_nazwa.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        schemat_nazwa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(schemat_nazwa, org.openide.util.NbBundle.getMessage(OcenyMainTopComponent.class, "OcenyMainTopComponent.schemat_nazwa.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(OcenyMainTopComponent.class, "OcenyMainTopComponent.jLabel4.text")); // NOI18N

        students_list.setModel(new StudentModel());
        jScrollPane1.setViewportView(students_list);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(OcenyMainTopComponent.class, "OcenyMainTopComponent.jLabel5.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(OcenyMainTopComponent.class, "OcenyMainTopComponent.jLabel6.text")); // NOI18N

        students_marks.setModel(new OcenyModel());
        students_marks.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(students_marks);
        students_marks.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(grupa_nazwa, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(schemat_nazwa, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(przedmiot_nazwa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(przedmiot_nazwa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grupa_nazwa)
                    .addComponent(schemat_nazwa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel grupa_nazwa;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel przedmiot_nazwa;
    private javax.swing.JLabel schemat_nazwa;
    private javax.swing.JTable students_list;
    private javax.swing.JTable students_marks;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
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
    private void OpiszOkno() {
        przedmiot_nazwa.setText(grupa.getPrzedmiot().getNazwa());
        grupa_nazwa.setText(grupa.getNazwa() + " - " + " termin");
        schemat_nazwa.setText("Schemat oceniania: " + grupa.getPrzedmiot().getGrupaOcen().getNazwa());
    }
    
    private void OrganizujTabele_Student() {
        TableColumn column = students_list.getColumn("Id");
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
    }
    
    private void NazwyRzedow_Oceny() {
        TableColumn column = students_marks.getColumn("Id");
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
        
        column = students_marks.getColumn("");
        column.setMinWidth(120);
        column.setMaxWidth(120);
        column.setPreferredWidth(120);
        OcenyMainTopComponent window = this;
        TableModel model = window.students_marks.getModel();
        clearTable((DefaultTableModel) window.students_marks.getModel());
        List<GrupaOcen> lista = GrupaOcen.getAllGrupaOcen(grupa.getPrzedmiot().getGrupaOcen().getId());
                int rowId = 0;
        for (GrupaOcen p : lista) {
            model.setValueAt(p.getId(), rowId, 0);
            model.setValueAt(p.getNazwa(), rowId, 1);
            rowId++;
        }
    }
    private void OrganizujTabele_Oceny() {
        
    }

    private void clearTable(DefaultTableModel defaultTableModel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
