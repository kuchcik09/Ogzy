package org.gui.grupy_cwiczeniowe;

import java.awt.Color;
import org.database.models.GrupaCwiczeniowa;
import org.database.models.Przedmiot;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import org.openide.awt.NotificationDisplayer;
import org.openide.windows.WindowManager;

/**
 *
 * @author Ja
 */
public class DodajEdytujGrupeCwiczeniowaPanel extends javax.swing.JPanel {

    /**
     * Creates new form DodajGrupeCwiczeniowaPanel
     */
    public DodajEdytujGrupeCwiczeniowaPanel() {
        initComponents();
        preparePrzedmiotyComboBox();
    }

    public DodajEdytujGrupeCwiczeniowaPanel(String nazwa, Przedmiot przedmiot) {
        initComponents();
        preparePrzedmiotyComboBox();
        nazwaField.setText(nazwa);
        przedmiotyComboBox.setEditable(true);
        przedmiotyComboBox.setSelectedItem(przedmiot);
    }

    private void preparePrzedmiotyComboBox() {
        List<Przedmiot> listaPrzedmitow = Przedmiot.getCurrentYearPrzedmioty();
        for (Przedmiot p : listaPrzedmitow) {
            przedmiotyComboBox.addItem(p);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nazwaField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        przedmiotyComboBox = new javax.swing.JComboBox();

        nazwaField.setText(org.openide.util.NbBundle.getMessage(DodajEdytujGrupeCwiczeniowaPanel.class, "DodajEdytujGrupeCwiczeniowaPanel.nazwaField.text")); // NOI18N
        nazwaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nazwaFieldActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DodajEdytujGrupeCwiczeniowaPanel.class, "DodajEdytujGrupeCwiczeniowaPanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DodajEdytujGrupeCwiczeniowaPanel.class, "DodajEdytujGrupeCwiczeniowaPanel.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(przedmiotyComboBox, 0, 250, Short.MAX_VALUE)
                    .addComponent(nazwaField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nazwaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(przedmiotyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nazwaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nazwaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nazwaFieldActionPerformed

    public void addGroup() {
        try {
            Random rand = new Random();
            Color random = Color.getHSBColor(rand.nextFloat(), 0.15F, 0.9F);
            String color = Integer.toHexString(random.getRGB());
            color = color.substring(2, color.length());
            color = color.toUpperCase();
            color = "#" + color;
            
            GrupaCwiczeniowa.add(nazwaField.getText(), ((Przedmiot) przedmiotyComboBox.getSelectedItem()).getId(),color);
            NotificationDisplayer.getDefault().notify("Dodano grupę ćwiczeniową", new ImageIcon(), "", null, NotificationDisplayer.Priority.LOW, NotificationDisplayer.Category.INFO);
            openGroups();
        } catch (Exception e) {
            NotificationDisplayer.getDefault().notify("Nie udało się dodać grupy ćwiczeniowej", new ImageIcon(), "", null, NotificationDisplayer.Priority.LOW, NotificationDisplayer.Category.WARNING);
        }
    }

    public void editGroup(int id) {
        GrupaCwiczeniowa g = GrupaCwiczeniowa.get(id);
        String nazwa = nazwaField.getText();
        Przedmiot przedmiot = (Przedmiot) przedmiotyComboBox.getSelectedItem();
        try {
            GrupaCwiczeniowa grupaCwiczeniowa = new GrupaCwiczeniowa(id, nazwa, przedmiot,g.getColor());
            GrupaCwiczeniowa.edit(grupaCwiczeniowa);
            NotificationDisplayer.getDefault().notify("Zmieniono dane grupy ćwiczeniowej", new ImageIcon(), "", null, NotificationDisplayer.Priority.LOW, NotificationDisplayer.Category.INFO);
            openGroups();
            //odswiezenie pojedynczej grupy jesli jest ona aktualnie otwarta
            GrupaCwiczeniowaTopComponent gtop = (GrupaCwiczeniowaTopComponent) WindowManager.getDefault().findTopComponent("GrupaCwiczeniowaTopComponent");    
            gtop.setGroup(grupaCwiczeniowa);
            
        } catch (Exception e) {
            NotificationDisplayer.getDefault().notify("Nie udało się zmienić danych grupy ćwiczeniowej", new ImageIcon(), "", null, NotificationDisplayer.Priority.LOW, NotificationDisplayer.Category.WARNING);
        }
    }

    private void openGroups() {
        GroupsListTopComponent window = (GroupsListTopComponent) WindowManager.getDefault().findTopComponent("GroupsListTopComponent");
        window.componentOpened();
        window.open();
        window.requestActive();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nazwaField;
    private javax.swing.JComboBox przedmiotyComboBox;
    // End of variables declaration//GEN-END:variables
}
