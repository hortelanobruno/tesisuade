/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.browser;

import com.bruno.elbruto.browser.thread.GetSourceCode;
import com.bruno.elbruto.manager.ElBrutoManager;
import com.bruno.elbruto.manager.ElBrutoManagerForFigting;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import com.bruno.elbruto.browser.thread.GetResourceLocation;
import com.bruno.elbruto.browser.thread.Navigate;
import com.bruno.elbruto.util.LoggerClass;
import javax.swing.SwingUtilities;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

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
        webBrowserPanel.setBorder(BorderFactory.createTitledBorder("elbruto.es Web Browser"));
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

    public String getIPInternet() {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet("http://www.comosabermiip.net/");
            //HttpPost httpost = new HttpPost("http://www.whatismyip.com");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String html = httpclient.execute(httpget, responseHandler);
            httpclient.getConnectionManager().shutdown();
            html = html.replace(" ", "");
            html = html.replace("\"", "");
            html = html.toLowerCase();
            html = html.split("<divid='ip'>")[1];
            html = html.split("</div><divid='mensaje'></div><divid='adsense'>")[0];
            return html;
        } catch (IOException ex) {
            LoggerClass.getInstance().error("Error al obtener la ip publica");
        }
        return null;
    }
}

