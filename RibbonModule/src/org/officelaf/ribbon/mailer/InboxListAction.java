/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.officelaf.ribbon.mailer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.gui.mailer.InboxListTopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author lewuaza
 */
public class InboxListAction extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent e) {
        InboxListTopComponent window = (InboxListTopComponent) WindowManager.getDefault().findTopComponent("MailerListMainTopComponent");
        window.setupForm();
        window.open();
        window.requestActive();
    }
    
}
