package model;

import java.util.Date;

public class TestModel {

    private String tname;
    private int tnumber;
    private Date tbuildtime;
    private Date rtesttime;

    public TestModel() {

    }
    public TestModel(int tnumber, String tname) {
        this.tnumber = tnumber;
        this.tname = tname;
    }

    public TestModel(int tnumber, String tname, Date tbuildtime, Date rtesttime) {
        this.tnumber = tnumber;
        this.tname = tname;
        this.tbuildtime = tbuildtime;
        this.rtesttime = rtesttime;
    }

    public int getTnumber() {
        return tnumber;
    }

    public String getTname() {
        return tname;
    }

    public Date getTbuildtime() {
        return tbuildtime;
    }
    public Date getRtesttime() {
        return rtesttime;
    }
    
}
