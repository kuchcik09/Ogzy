/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import org.database.models.GrupaCwiczeniowa;
import org.database.models.Termin;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.gui.grupy_cwiczeniowe.GrupaCwiczeniowaTopComponent;
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
        dtd = "-//org.gui//Main//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "MainTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "org.gui.MainTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_MainAction",
        preferredID = "MainTopComponent"
)
@Messages({
    "CTL_MainAction=Plan zajęć",
    "CTL_MainTopComponent=Plan zajęć",
    "HINT_MainTopComponent=To okno ukazuje plan zajęć"
})
public final class MainTopComponent extends TopComponent {
    
    private LinkedList<Termin> allTerms;
    private Termin[][] terms = new Termin[7][7];
    
    public Termin getTerms(int r, int c){
        return terms[r][c];
    }
    
    public MainTopComponent() {
        this.allTerms = new LinkedList<Termin>();
        initComponents();
        setName(Bundle.CTL_MainTopComponent());
        setToolTipText(Bundle.HINT_MainTopComponent());
        //putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        this.termsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final MainTopComponent me = this;
        ListSelectionListener tableListener = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                TopComponentsManagerListener.MainTopComponentActivated(me);
            }
        };
        this.termsTable.getSelectionModel().addListSelectionListener(tableListener);
        this.termsTable.getColumnModel().getSelectionModel().addListSelectionListener(tableListener);
        this.termsTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        this.termsTable.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DefaultTableModel model = (DefaultTableModel) termsTable.getModel();
                String value = (String)model.getValueAt(termsTable.getSelectedRow(), termsTable.getSelectedColumn());

                if(value != null && termsTable.getSelectedColumn() >0){
                    openGroupTopComponent(terms[termsTable.getSelectedRow()][termsTable.getSelectedColumn()-1]);
                }
            }
        });
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    this.termsTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
    this.termsTable.getColumnModel().getColumn(1).setCellRenderer( new CustomRenderer() );
    this.termsTable.getColumnModel().getColumn(2).setCellRenderer( new CustomRenderer() );
    this.termsTable.getColumnModel().getColumn(3).setCellRenderer( new CustomRenderer() );
    this.termsTable.getColumnModel().getColumn(4).setCellRenderer( new CustomRenderer() );
    this.termsTable.getColumnModel().getColumn(5).setCellRenderer( new CustomRenderer() );
    this.termsTable.getColumnModel().getColumn(6).setCellRenderer( new CustomRenderer() );
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        termsTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        termsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Czas", "Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        termsTable.setColumnSelectionAllowed(true);
        termsTable.setRowHeight(60);
        termsTable.getTableHeader().setReorderingAllowed(false);
        termsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                termsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(termsTable);
        termsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (termsTable.getColumnModel().getColumnCount() > 0) {
            termsTable.getColumnModel().getColumn(0).setResizable(false);
            termsTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(MainTopComponent.class, "MainTopComponent.termsTable.columnModel.title0")); // NOI18N
            termsTable.getColumnModel().getColumn(1).setResizable(false);
            termsTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(MainTopComponent.class, "MainTopComponent.termsTable.columnModel.title1")); // NOI18N
            termsTable.getColumnModel().getColumn(2).setResizable(false);
            termsTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(MainTopComponent.class, "MainTopComponent.termsTable.columnModel.title2")); // NOI18N
            termsTable.getColumnModel().getColumn(3).setResizable(false);
            termsTable.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(MainTopComponent.class, "MainTopComponent.termsTable.columnModel.title3")); // NOI18N
            termsTable.getColumnModel().getColumn(4).setResizable(false);
            termsTable.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(MainTopComponent.class, "MainTopComponent.termsTable.columnModel.title4")); // NOI18N
            termsTable.getColumnModel().getColumn(5).setResizable(false);
            termsTable.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(MainTopComponent.class, "MainTopComponent.termsTable.columnModel.title5")); // NOI18N
            termsTable.getColumnModel().getColumn(6).setResizable(false);
            termsTable.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(MainTopComponent.class, "MainTopComponent.termsTable.columnModel.title6")); // NOI18N
            termsTable.getColumnModel().getColumn(7).setResizable(false);
            termsTable.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(MainTopComponent.class, "MainTopComponent.termsTable.columnModel.title7")); // NOI18N
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    private void openGroupTopComponent(Termin term){
        List<GrupaCwiczeniowa> groups = GrupaCwiczeniowa.getAll();
            GrupaCwiczeniowa grupa = null;

            String grupa_name = term.getGrupa().getNazwa();
            String przedmiot_name = term.getGrupa().getPrzedmiot().getNazwa();

            for(int i=0;i<groups.size();i++){
                if(groups.get(i).getNazwa().equals(grupa_name) && groups.get(i).getPrzedmiot().getNazwa().equals(przedmiot_name)){
                    grupa = groups.get(i);
                    break;
                }
            }

            GrupaCwiczeniowaTopComponent top = (GrupaCwiczeniowaTopComponent) WindowManager.getDefault().findTopComponent("GrupaCwiczeniowaTopComponent");
            top.setGroup(grupa);
            if(top.isOpened() == false)
                top.open();
            top.requestActive();
    }
    
    private void termsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_termsTableMouseClicked
        DefaultTableModel model = (DefaultTableModel) this.termsTable.getModel();
        String value = (String)model.getValueAt(this.termsTable.getSelectedRow(), this.termsTable.getSelectedColumn());

        if(value != null && this.termsTable.getSelectedColumn() >0 && evt.getClickCount() == 2){
            openGroupTopComponent(terms[termsTable.getSelectedRow()][termsTable.getSelectedColumn()-1]);
        }
    }//GEN-LAST:event_termsTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable termsTable;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        
        allTerms = Termin.getAllTermsForYearAndSemester(); //pobieram wszystkie terminy
        
        DefaultTableModel model = (DefaultTableModel) this.termsTable.getModel(); //pobieram model tabeli
        
        while(model.getRowCount()>0) model.removeRow(0); //czyszcze całą tabele
        
        int StartScaleTime = 6; //od jakiej godziny zaczynam liczyć
        //tu jest 6-ta bo w pętli będę dodwać po 2 godziny
        
        Time tempToTable = new Time(StartScaleTime, 0, 0); //ustalam startowy czas
        /*
            Pętla idzie 6-króków to daje od 8 do 20 składając w każdym kroku stringa do wyświetlania i dodając
            puste narazie wartości null do dabeli ustawając czas o 2godziny wiecej
            czyli poprostu inicjalizuje się tu pusta tabela
        */
        for(int i=0;i<=6;i++){
            String temp = tempToTable.toString().substring(0, tempToTable.toString().length()-3)+" - ";
            tempToTable.setTime(tempToTable.getTime()+ 7200000); // 7200000 milisec = 1h
            model.addRow(new Object[]{temp+tempToTable.toString().substring(0, tempToTable.toString().length()-3),null,null,null,null,null,null,null});
            
        }
        
        //teraz przechodząc po terminach dodaję odpowiednio grupy
        for(int i=0;i<allTerms.size();i++){
            Termin term = allTerms.get(i);
            int rowIndex = (Integer.parseInt(term.getGodzina_start().toString().substring(0, 2))/2)-3; 
            int columnIndex = term.getDzien_tygodnia().ordinal()+1;
            
            terms[rowIndex][columnIndex-1] = term;
            model.setValueAt("<html><div style=\"margin:5px;\"><b>"+term.getGrupa().getNazwa()+ "</b><br>"+term.getGrupa().getPrzedmiot().getNazwa()+"</div></html>", rowIndex, columnIndex);
            
        }
        
        termsTable.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        termsTable.setDefaultRenderer(String.class, centerRenderer);
    }

    @Override
    public void componentClosed() {
    
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

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel)this.termsTable.getModel();
    }

    public JTable getTable() {
        return this.termsTable;
    }
    
    class CustomRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if(terms[row][column-1] != null){
                if(!isSelected){
                    cellComponent.setBackground(Color.decode(terms[row][column-1].getGrupa().getColor()));
                }
            }else{
                cellComponent.setBackground(null);
            }
            return cellComponent;
        }
    }
}