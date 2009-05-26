/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 *
 * @author Brunoli
 */
public class asdf implements Runnable {

    private JWebBrowser browser;
    private ReferenciaString referenciaString;

    public asdf(JWebBrowser aThis, ReferenciaString referenciaString) {
        this.browser = aThis;
        this.referenciaString = referenciaString;
    }

    @Override
    public void run() {
        referenciaString.setValor(browser.getResourceLocation());
    }
}
