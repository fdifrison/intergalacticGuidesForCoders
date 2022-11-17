package dev.fdifrison;

public class DataSourceProperties {

    private final String host;
    private final int port;
    private final String db;
    private final String user;
    private final String password;

    // when the no-arg constructor is called, then the variable related to the db ar parsed as env variable
    public DataSourceProperties() {
        this.host = System.getenv("RDS_HOST");
        this.port = Integer.parseInt(System.getenv("RDS_PORT"));
        this.db = System.getenv("RDS_DB");
        this.user = System.getenv("RDS_USER");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public DataSourceProperties(String host, int port, String db, String user, String password) {
        this.host = host;
        this.port = port;
        this.db = db;
        this.user = user;
        this.password = password;
    }

    public String getJdbcUrl() {
        return "jdbc:postgresql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDb();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDb() {
        return db;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
