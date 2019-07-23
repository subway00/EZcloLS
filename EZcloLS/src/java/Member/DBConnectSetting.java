package Member;

public class DBConnectSetting {
    private final String url = "jdbc:sqlserver://DESKTOP-8H832KC:1433;databaseName=EZclo";
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
