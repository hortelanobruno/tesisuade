/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration.defaultontology.types;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brunoli
 */
public class DefaultTranslado {

    private List<DefaultProperty> defaultProperties;

    public DefaultTranslado() {
        defaultProperties = new ArrayList<DefaultProperty>();
    }

    public List<DefaultProperty> getDefaultProperties() {
        return defaultProperties;
    }

    public void setDefaultProperties(List<DefaultProperty> defaultProperties) {
        this.defaultProperties = defaultProperties;
    }
}
