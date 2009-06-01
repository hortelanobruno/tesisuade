/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto;

import com.bruno.elbruto.browser.BrowserManager;
import com.bruno.elbruto.manager.ElBrutoManager;
import com.bruno.elbruto.manager.ElBrutoManagerForFigting;

/**
 *
 * @author Brunoli
 */
public class RunClassForFighting {

    public static void main(String[] args) {
        ElBrutoManager manager = new ElBrutoManagerForFigting();
        new BrowserManager(manager);
        manager.init();
    }
}
