package org.officelaf.ribbon.schematy;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Duży
 */
public class PokazSchematyAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        TopComponent top = WindowManager.getDefault().findTopComponent("SchemeTopComponent");
        top.open();
        top.requestActive();
    }

}
