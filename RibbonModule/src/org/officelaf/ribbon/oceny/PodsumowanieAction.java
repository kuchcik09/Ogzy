package org.officelaf.ribbon.oceny;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.gui.oceny.NotesTableTopComponent;
import org.gui.oceny.OcenyKoncoweTopComponent;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Duży
 * @edited Marcin
 */
public class PodsumowanieAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        NotesTableTopComponent notestop = (NotesTableTopComponent) WindowManager.getDefault().findTopComponent("NotesTableTopComponent");
        if (notestop.isOpened()) {

            Mode mode = WindowManager.getDefault().findMode("editor");
            for (TopComponent tc : WindowManager.getDefault().getOpenedTopComponents(mode)) {
                if (tc instanceof OcenyKoncoweTopComponent) {
                    if (((OcenyKoncoweTopComponent) tc).getGrupa().getId() == notestop.getGrupaCwiczeniowa().getId()) {
                        if (!tc.isOpened()) {
                            tc.open();
                        }
                        tc.requestActive();
                        return;
                    }
                }
            }

            OcenyKoncoweTopComponent oceny = new OcenyKoncoweTopComponent();
            oceny.setArguments(notestop.getGrupaCwiczeniowa(), notestop.getStudentsList());
            oceny.open();
            oceny.requestActive();
        }

    }

}
