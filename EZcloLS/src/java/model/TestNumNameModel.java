package model;

public class TestNumNameModel {
    private String tname;
    private int tnumber;
    public TestNumNameModel() {
        
    }
    public TestNumNameModel(int tnumber, String tname) {
        this.tnumber = tnumber;
        this.tname = tname;
    }
    public int getTnumber() {
        return tnumber;
    }
    
    public String getTname() {
        return tname;
    }

}
