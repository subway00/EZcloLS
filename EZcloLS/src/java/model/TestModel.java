package model;

public class TestModel {
    private String T_Name;
    private String T_BuildTime;
    public TestModel() {
        
    }
    public TestModel(String T_Name, String T_BuildTime) {
        this.T_Name = T_Name;
        this.T_BuildTime = T_BuildTime;
    }

    public String getT_Name() {
        return T_Name;
    }

    public String getT_BuildTime() {
        return T_BuildTime;
    }
    
}
