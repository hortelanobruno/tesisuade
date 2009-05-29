/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.browser;

import com.bruno.elbruto.browser.thread.GetSourceCode;
import com.bruno.elbruto.manager.ElBrutoManager;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import com.bruno.elbruto.browser.thread.GetResourceLocation;
import com.bruno.elbruto.browser.thread.Navigate;
import javax.swing.SwingUtilities;

/**
 *
 * @author Brunoli
 */
public class SimpleWebBrowser extends JPanel {

    private JWebBrowser webBrowser;
    private ElBrutoManager brutoManager;

    public SimpleWebBrowser(ElBrutoManager brutoManager) {
        super(new BorderLayout());
        this.brutoManager = brutoManager;
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
        webBrowser = new JWebBrowser(brutoManager);
        brutoManager.setWebBrowser(this);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        add(webBrowserPanel, BorderLayout.CENTER);
        // Create an additional bar allowing to show/hide the menu bar of the web browser.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
        JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());
        menuBarCheckBox.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                webBrowser.setMenuBarVisible(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        buttonPanel.add(menuBarCheckBox);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JWebBrowser getWebBrowser() {
        return webBrowser;
    }

    public String getSourceCode() {
        GetSourceCode get = new GetSourceCode(webBrowser, this);
        SwingUtilities.invokeLater(get);
        return get.getSourceCode();
    }

    public String getUrl() {
        GetResourceLocation get = new GetResourceLocation(webBrowser, this);
        SwingUtilities.invokeLater(get);
        return get.getResourceLocation();
    }

    public void navigate(String url) {
        Navigate nav = new Navigate(webBrowser, this, url);
        SwingUtilities.invokeLater(nav);
    }
}
