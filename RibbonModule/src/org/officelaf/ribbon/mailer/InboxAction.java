package org.officelaf.ribbon.mailer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.gui.mailer.MailerTopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author lewuaza
 */
public class InboxAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        final MailerTopComponent window = (MailerTopComponent) WindowManager.getDefault().findTopComponent("MailerMainTopComponent");
        new Thread(new Runnable() {
            @Override
            public void run() {
                window.setupForm();
            }
        }).start();
        window.open();
        window.requestActive();
    }

}
