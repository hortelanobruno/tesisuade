/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.browser;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import com.bruno.elbruto.manager.ElBrutoManager;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Brunoli
 */
public class BrowserManager {

    private SimpleWebBrowser simpleWebBrowser;

    public BrowserManager(ElBrutoManager brutoManager) {
//        try {
            initBrowser(brutoManager);
//            Thread.sleep(5000);
//            SwingUtilities.invokeLater(new Runnable() {
//
//                public void run() {
//                    System.out.println(simpleWebBrowser.getWebBrowser().getResourceLocation());
//                }
//            });
//        } catch (InterruptedException ex) {
//            Logger.getLogger(BrowserManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void initBrowser(ElBrutoManager brutoManager) {
        UIUtils.setPreferredLookAndFeel();
        simpleWebBrowser = new SimpleWebBrowser(brutoManager);
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame("DJ Native Swing Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(simpleWebBrowser, BorderLayout.CENTER);
                frame.setSize(800, 600);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });
        NativeInterface.runEventPump();
    }
}
