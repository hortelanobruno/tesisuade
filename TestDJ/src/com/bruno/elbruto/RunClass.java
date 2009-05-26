/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto;

import com.bruno.elbruto.browser.BrowserManager;
import com.bruno.elbruto.manager.ElBrutoManager;

/**
 *
 * @author Brunoli
 */
public class RunClass {

    public static void main(String[] args) {
        ElBrutoManager manager = new ElBrutoManager();
        new BrowserManager(manager);
        manager.init();
    }
}
