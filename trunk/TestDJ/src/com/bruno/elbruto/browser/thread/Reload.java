/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.browser.thread;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 *
 * @author Brunoli
 */
public class Reload implements Runnable {

    private JWebBrowser webBrowser;

    public Reload(JWebBrowser webBrowser) {
        this.webBrowser = webBrowser;
    }

    @Override
    public void run() {
        webBrowser.reloadPage();
    }
}
