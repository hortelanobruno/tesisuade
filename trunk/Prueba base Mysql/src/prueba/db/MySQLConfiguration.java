/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.db;

/**
 *
 * @author brunoli
 */
public class MySQLConfiguration {

    private String ip;
    private String port;
    private String database;
    private String user;
    private String password;
    private String tableIPDRs;
    private String tablePlanes;
    private String tableSubscribers;
    private String tableHeavyUsers;
    

    public MySQLConfiguration() {
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
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
     * @return the tableIPDRs
     */
    public String getTableIPDRs() {
        return tableIPDRs;
    }

    /**
     * @param tableIPDRs the tableIPDRs to set
     */
    public void setTableIPDRs(String tableIPDRs) {
        this.tableIPDRs = tableIPDRs;
    }

    public String getTablePlanes() {
        return tablePlanes;
    }

    public void setTablePlanes(String tablePlanes) {
        this.tablePlanes = tablePlanes;
    }

    /**
     * @return the tableSubscribers
     */
    public String getTableSubscribers() {
        return tableSubscribers;
    }

    /**
     * @param tableSubscribers the tableSubscribers to set
     */
    public void setTableSubscribers(String tableSubscribers) {
        this.tableSubscribers = tableSubscribers;
    }

    /**
     * @return the tableHeavyUsers
     */
    public String getTableHeavyUsers() {
        return tableHeavyUsers;
    }

    /**
     * @param tableHeavyUsers the tableHeavyUsers to set
     */
    public void setTableHeavyUsers(String tableHeavyUsers) {
        this.tableHeavyUsers = tableHeavyUsers;
    }
}
