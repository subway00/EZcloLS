package model;

public class DBConnectModel {

//    private final String url = "jdbc:sqlserver://C103-30:1433;databaseName=EZclo";
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=EZclo";
    private final String user = "sa";
    private final String pw = "12345";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPw() {
        return pw;
    }

}
