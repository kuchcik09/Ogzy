/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.core;
import org.database.intallation.Database_install;
/**
 *
 * @author Mariushrek
 */
public class Tworzenie_bazy {
    public Tworzenie_bazy() {
        Database_install ala = new Database_install();
        System.out.println(ala.Install_Database());
    }
}
