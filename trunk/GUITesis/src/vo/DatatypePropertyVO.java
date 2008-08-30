/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vo;

import java.util.List;

/**
 *
 * @author Administrador
 */
public class DatatypePropertyVO {

    private String name;
    private List<String> domain;
    private String range;
    
    public DatatypePropertyVO() {
    }

    public DatatypePropertyVO(String name, List<String> domain, String range) {
        this.name = name;
        this.domain = domain;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDomain() {
        return domain;
    }

    public void setDomain(List<String> domain) {
        this.domain = domain;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        if(range.equalsIgnoreCase("Date")){
            this.range = "Date";
        } else if(range.equalsIgnoreCase("String")){
            this.range = "String";
        } else if(range.equalsIgnoreCase("int")){
            this.range = "Int";
        } else if(range.equalsIgnoreCase("Double")){
            this.range = "Double";
        } else if(range.equalsIgnoreCase("Float")){
            this.range = "Float";
        } else if(range.equalsIgnoreCase("Boolean")){
            this.range = "Boolean";
        }
    }

    
}
