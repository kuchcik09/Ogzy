package org.gui.studenci;

import org.database.models.GrupaCwiczeniowa;
import org.database.models.GrupaStudent;
import org.database.models.Student;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.openide.awt.NotificationDisplayer;

/**
 *
 * @author Ja
 */
public class OdepnijStudentaPanel extends javax.swing.JPanel {

    /**
     * Creates new form PodepnijOdepnijStudentaPanel
     */
    public OdepnijStudentaPanel() {
        initComponents();
    }

    public OdepnijStudentaPanel(Integer id) {
        initComponents();

        Student s = Student.getById(id);
        imieLabel.setText(s.getImie());
        nazwiskoLabel.setText(s.getNazwisko());
        emailLabel.setText(s.getEmail());
        indeksLabel.setText(String.valueOf(s.getIndeks()));

        initTable(id);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaGrupStudenta = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        imieLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nazwiskoLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        indeksLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        tabelaGrupStudenta.setModel(new org.gui.studenci.GrupyStudentaTableModel());
        jScrollPane1.setViewportView(tabelaGrupStudenta);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(OdepnijStudentaPanel.class, "OdepnijStudentaPanel.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(imieLabel, org.openide.util.NbBundle.getMessage(OdepnijStudentaPanel.class, "OdepnijStudentaPanel.imieLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(OdepnijStudentaPanel.class, "OdepnijStudentaPanel.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(nazwiskoLabel, org.openide.util.NbBundle.getMessage(OdepnijStudentaPanel.class, "OdepnijStudentaPanel.nazwiskoLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(OdepnijStudentaPanel.class, "OdepnijStudentaPanel.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(emailLabel, org.openide.util.NbBundle.getMessage(OdepnijStudentaPanel.class, "OdepnijStudentaPanel.emailLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(OdepnijStudentaPanel.class, "OdepnijStudentaPanel.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(indeksLabel, org.openide.util.NbBundle.getMessage(OdepnijStudentaPanel.class, "OdepnijStudentaPanel.indeksLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(OdepnijStudentaPanel.class, "OdepnijStudentaPanel.jLabel9.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imieLabel)
                            .addComponent(nazwiskoLabel)
                            .addComponent(emailLabel)
                            .addComponent(indeksLabel))
                        .addGap(0, 327, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(imieLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nazwiskoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(emailLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(indeksLabel))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel imieLabel;
    private javax.swing.JLabel indeksLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nazwiskoLabel;
    private javax.swing.JTable tabelaGrupStudenta;
    // End of variables declaration//GEN-END:variables

    private void initTable(Integer studentId) {
        tabelaGrupStudenta.removeAll();
        DefaultTableModel model = (DefaultTableModel) tabelaGrupStudenta.getModel();
        List<GrupaCwiczeniowa> list = GrupaCwiczeniowa.getAllForStudent(studentId);
        int rowId = 0;
        for (GrupaCwiczeniowa grupa : list) {
            model.setValueAt(grupa.getId(), rowId, 0);
            model.setValueAt(grupa.getNazwa(), rowId, 1);
            model.setValueAt(grupa.getPrzedmiot(), rowId, 2);
            rowId++;
        }
        hideIdColumn();
        tabelaGrupStudenta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void hideIdColumn() {
        TableColumn column = tabelaGrupStudenta.getColumn("Id");
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
    }

    public void odepnijStudenta(Integer studentId) {
        int selectedRow = tabelaGrupStudenta.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tabelaGrupStudenta.getModel();
            int grupaId = (Integer) model.getValueAt(selectedRow, 0);
            GrupaStudent.delete(studentId, grupaId);
            NotificationDisplayer.getDefault().notify("Odpięto studenta z wybranej grupy", new ImageIcon(), "", null, NotificationDisplayer.Priority.LOW, NotificationDisplayer.Category.INFO);
        } else {
            JOptionPane.showMessageDialog(this, "Nie wybrano grupy!", "Odpinanie studenta - BŁĄD", JOptionPane.ERROR_MESSAGE);
        }
    }
}
