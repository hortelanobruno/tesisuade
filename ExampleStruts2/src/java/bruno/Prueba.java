package bruno;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;

public class Prueba extends ActionSupport {

    private String username;
    private String password;
    private Double precio;
    private Date fecha1;

    public String execute() throws Exception {
        imprimir();
        return SUCCESS;
    }

    private void imprimir() {
        System.out.println("Usuario: "+getUsername());
        System.out.println("Password: "+getPassword());
        System.out.println("Precio: "+getPrecio());
        System.out.println("Pais: "+getFecha1());
    }

    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

}

