/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author brunoli
 */
public class LoggerClass {

    private Log logInfo;
    private Log logError;
    private Log logDebug;
    private static LoggerClass loggerClass;
    private static String config;

    private LoggerClass(String configLogger) {
        DOMConfigurator.configure(configLogger);
        logInfo = LogFactory.getLog("info");
        logError = LogFactory.getLog("error");
        logDebug = LogFactory.getLog("debug");
    }

    public static LoggerClass getInstance() {
        if (loggerClass == null) {
            loggerClass = new LoggerClass(config);
        }
        return loggerClass;
    }

    public static void setConfigLogger(String path) {
        LoggerClass.config = path;
    }

    public void info(String msj) {
        logInfo.info(msj);
    }

    public void error(String msj) {
        logError.error(msj);
    }

    public void error(String msj, Exception ex) {
        logError.error(msj,ex);
    }

    public void debug(String msj) {
        logDebug.debug(msj);
    }
}
