/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoli.projectmanager.tests;

import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Admin
 */
public class FTPTest {

    public FTPTest() {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect("10.0.0.44");
            ftpClient.login("root", "root");
            //Esto me sirve para ver si estan los archivos correctos
            FTPFile[] files = ftpClient.listFiles("/");
            for (FTPFile fTPFile : files) {
                System.out.println(fTPFile);
            }
            ftpClient.disconnect();
        } catch (SocketException ex) {
            System.out.println("SocketException. " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException. " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new FTPTest();
    }
}
