/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bruno.elbruto.browser.thread;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import com.bruno.elbruto.browser.SimpleWebBrowser;

/**
 *
 * @author Brunoli
 */
public class Navigate implements Runnable {

    private JWebBrowser webBrowser;
    private SimpleWebBrowser simple;
    private String url;
    
    public Navigate(JWebBrowser webBrowser, SimpleWebBrowser simple, String url) {
        this.webBrowser = webBrowser;
        this.simple = simple;
        this.url = url;
    }

    
    @Override
    public void run() {
        webBrowser.navigate(url);
    }

}
