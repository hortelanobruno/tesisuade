/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package varios;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brunoli
 */
public class ErrorTransoformacion {

    private String error;
    private List<String> subErrores;

    public ErrorTransoformacion() {
        subErrores = new ArrayList<String>();
    }

    public String getError() {
        return error;
    }

    public List<String> getSubErrores() {
        return subErrores;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setSubErrores(List<String> subErrores) {
        this.subErrores = subErrores;
    }
}
