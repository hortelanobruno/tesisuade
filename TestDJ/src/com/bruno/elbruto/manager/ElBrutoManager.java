/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.manager;

import com.bruno.elbruto.browser.SimpleWebBrowser;

/**
 *
 * @author Brunoli
 */
public abstract class ElBrutoManager {

    public ElBrutoManager() {
    }

    public abstract void avisarDone();

    public abstract void setWebBrowser(SimpleWebBrowser aThis);

    public abstract void init();
}
