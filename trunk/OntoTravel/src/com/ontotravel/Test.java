/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ontotravel;

import com.hp.hpl.jena.datatypes.xsd.impl.XSDDateType;
import java.util.Calendar;

/**
 *
 * @author Brunoli
 */
public class Test {

    public static void main(String[] args) {
        XSDDateType a = new XSDDateType(Calendar.getInstance().getTime().toString());
        System.out.println(a);
    }
}
