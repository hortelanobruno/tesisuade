/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bruno.elbruto.browser.thread;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import com.bruno.elbruto.browser.SimpleWebBrowser;

/**
 *
 * @author Administrator
 */
public class GetResourceLocation implements Runnable {

    private JWebBrowser webBrowser;
    private SimpleWebBrowser simple;
    private String resourceLocation = null;

    public GetResourceLocation(JWebBrowser webBrowser, SimpleWebBrowser simple) {
        this.webBrowser = webBrowser;
        this.simple = simple;
    }

    @Override
    public void run() {
        resourceLocation = webBrowser.getResourceLocation();
    }

    public String getResourceLocation() {
        while (resourceLocation == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
        return resourceLocation;
    }
}
