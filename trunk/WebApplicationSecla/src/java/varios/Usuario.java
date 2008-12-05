/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package varios;

/**
 *
 * @author Administrador
 */
public class Usuario {

    private String usuario;
    private String responsable;
    private String sector;
    private String sede;
    private String digarea;
    private String digresp;
    private String password;
    private String tipoCuenta;

    public Usuario() {
    }

    public Usuario(String usuario, String responsable, String sector, String sede, String digarea, String digresp) {
        this.usuario = usuario;
        this.responsable = responsable;
        this.sector = sector;
        this.sede = sede;
        this.digarea = digarea;
        this.digresp = digresp;
    }

    public Usuario(String usuario, String responsable, String sector, String sede, String digarea, String digresp, String password, String tipoCuenta) {
        this.usuario = usuario;
        this.responsable = responsable;
        this.sector = sector;
        this.sede = sede;
        this.digarea = digarea;
        this.digresp = digresp;
        this.password = password;
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * @return the sede
     */
    public String getSede() {
        return sede;
    }

    /**
     * @param sede the sede to set
     */
    public void setSede(String sede) {
        this.sede = sede;
    }

    /**
     * @return the digarea
     */
    public String getDigarea() {
        return digarea;
    }

    /**
     * @param digarea the digarea to set
     */
    public void setDigarea(String digarea) {
        this.digarea = digarea;
    }

    /**
     * @return the digresp
     */
    public String getDigresp() {
        return digresp;
    }

    /**
     * @param digresp the digresp to set
     */
    public void setDigresp(String digresp) {
        this.digresp = digresp;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tipoCuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}
