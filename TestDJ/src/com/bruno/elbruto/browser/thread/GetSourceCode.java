/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.browser.thread;

import com.bruno.elbruto.browser.*;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 *
 * @author Brunoli
 */
public class GetSourceCode implements Runnable {

    private JWebBrowser webBrowser;
    private SimpleWebBrowser simple;
    private String html = null;

    public GetSourceCode(JWebBrowser webBrowser, SimpleWebBrowser simple) {
        this.webBrowser = webBrowser;
        this.simple = simple;
    }

    @Override
    public void run() {
        html = webBrowser.getHTMLContent();
    }

    public String getSourceCode() {
        while (html == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
        return html;
    }
}
