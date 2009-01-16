/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bruno;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

public class Iterator extends ActionSupport {

    private List<String> days;

    public String execute() throws Exception {
        cargarDays();
        return SUCCESS;
    }

    /**
     * @return the days
     */
    public List<String> getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(List<String> days) {
        this.days = days;
    }

    private void cargarDays() {
        days = new ArrayList<String>();
        days.add("a1");
        days.add("a2");
        days.add("a3");
    }

  

   

}
