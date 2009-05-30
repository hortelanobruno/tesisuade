/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto;

import com.bruno.elbruto.browser.BrowserManager;
import com.bruno.elbruto.manager.ElBrutoManager;
import com.bruno.elbruto.manager.ElBrutoManagerForFigting;
import com.bruno.elbruto.manager.ElBrutoManagerForReclute;

/**
 *
 * @author Brunoli
 */
public class RunClass {

    public static void main(String[] args) {
        ElBrutoManager manager = new ElBrutoManagerForReclute();
        new BrowserManager(manager);
        manager.init();
    }
}
