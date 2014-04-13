/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.officelaf.listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.gui.MainTopComponent;
import org.gui.grupy_cwiczeniowe.GrupaCwiczeniowaTopComponent;
import org.jvnet.flamingo.common.AbstractCommandButton;
import org.jvnet.flamingo.ribbon.RibbonElementPriority;
import org.officelaf.ribbon.MainRibbon;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Duży
 */
public class TopComponentsManagerListener implements PropertyChangeListener{

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("activated")){
            //gdy aktywowano component
            TopComponent top = WindowManager.getDefault().getRegistry().getActivated();
            if(top instanceof MainTopComponent){
                MainTopComponentActivated((MainTopComponent)top);
            }
            else if(top instanceof GrupaCwiczeniowaTopComponent){
                System.out.println("Otwarto grupe");
            }
        }
        else if(evt.getPropertyName().equals("tcOpened")){
            //gdy otwarto top component
        }
        else if(evt.getPropertyName().equals("tcClosed")){
            //gdy zamknięto top component
        }
        else if(evt.getPropertyName().equals("opened")){
            //gdy otwarto component
        }
    }
    
    public static void MainTopComponentActivated(MainTopComponent top){
        AbstractCommandButton addTermButton = MainRibbon.getRibbonButton(0, 0, 0, RibbonElementPriority.TOP, 1);
        AbstractCommandButton removeTermButton = MainRibbon.getRibbonButton(0, 0, 0, RibbonElementPriority.TOP, 2);
        AbstractCommandButton showNotes = MainRibbon.getRibbonButton(0, 1, 0, RibbonElementPriority.TOP, 0);
        
        JTable topTable = top.getTable();
        DefaultTableModel topTableModel = top.getTableModel();
        int selectedRow = topTable.getSelectedRow();
        int selectedColumn = topTable.getSelectedColumn();
        Object topValue = null;
        
        if(selectedRow != -1 && selectedColumn != -1) topValue = topTableModel.getValueAt(selectedRow, selectedColumn);
        
        if(topValue != null || (topValue == null && selectedColumn == -1)){
            if(topTable.getSelectedColumn() <= 0){
                addTermButton.setEnabled(false);
                removeTermButton.setEnabled(false);
                lockAllNotesButtons();
                lockAllPresenceButtons();
            }else{
                addTermButton.setEnabled(false);
                removeTermButton.setEnabled(true);
                lockAdditionNotesButtons();
                showNotes.setEnabled(true);
                lockOnlyDeletePresenceButton();
            }
        }else{
            addTermButton.setEnabled(true);
            removeTermButton.setEnabled(false);
            lockAllNotesButtons();
            lockAllPresenceButtons();
        }
    }
    
    public static void lockAllNotesButtons(){
        AbstractCommandButton showNotes = MainRibbon.getRibbonButton(0, 1, 0, RibbonElementPriority.TOP, 0);
        showNotes.setEnabled(false);
        lockAdditionNotesButtons();
    }
    
    public static void lockAdditionNotesButtons(){
        AbstractCommandButton addColumn = MainRibbon.getRibbonButton(0, 1, 0, RibbonElementPriority.TOP, 1);
        AbstractCommandButton deleteColumn = MainRibbon.getRibbonButton(0, 1, 0, RibbonElementPriority.TOP, 2);
        AbstractCommandButton showSum = MainRibbon.getRibbonButton(0, 1, 0, RibbonElementPriority.TOP, 3);
        addColumn.setEnabled(false);
        deleteColumn.setEnabled(false);
        showSum.setEnabled(false);
    }
    
    public static void lockAllPresenceButtons(){
        AbstractCommandButton showPresences = MainRibbon.getRibbonButton(0, 2, 0, RibbonElementPriority.TOP, 0);
        AbstractCommandButton deletePresence = MainRibbon.getRibbonButton(0, 2, 0, RibbonElementPriority.TOP, 1);
        showPresences.setEnabled(false);
        deletePresence.setEnabled(false);
    }
    
    public static void lockOnlyDeletePresenceButton(){
        AbstractCommandButton showPresences = MainRibbon.getRibbonButton(0, 2, 0, RibbonElementPriority.TOP, 0);
        AbstractCommandButton deletePresence = MainRibbon.getRibbonButton(0, 2, 0, RibbonElementPriority.TOP, 1);
        showPresences.setEnabled(true);
        deletePresence.setEnabled(false);
    }
}
