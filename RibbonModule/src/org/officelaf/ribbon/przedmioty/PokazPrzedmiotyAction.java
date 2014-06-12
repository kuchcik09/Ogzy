package org.officelaf.ribbon.przedmioty;

import org.gui.przedmioty.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.windows.WindowManager;

/**
 *
 * @author Mariushrek
 */
public class PokazPrzedmiotyAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        PokazPrzedmiotyTopComponent w = (PokazPrzedmiotyTopComponent) WindowManager.getDefault().findTopComponent("PokazPrzedmiotyTopComponent");
        w.open();
        w.aktualizuj();
        w.requestActive();
    }
}
