/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.browser;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import com.bruno.elbruto.manager.ElBrutoManager;
import com.bruno.elbruto.manager.ElBrutoManagerForFigting;
import com.bruno.elbruto.manager.ElBrutoManagerForReclute;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Brunoli
 */
public class BrowserManager {

    private SimpleWebBrowser simpleWebBrowser;
    private ElBrutoManager brutoManager;

    public BrowserManager(ElBrutoManager manager) {
        initBrowser(manager);
    }

    public void initBrowser(ElBrutoManager brutoManager) {
        UIUtils.setPreferredLookAndFeel();
        simpleWebBrowser = new SimpleWebBrowser(brutoManager);
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame("elbruto.es");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(simpleWebBrowser, BorderLayout.CENTER);
                //frame.setSize(800, 600);
                frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });
        NativeInterface.runEventPump();
    }
}
