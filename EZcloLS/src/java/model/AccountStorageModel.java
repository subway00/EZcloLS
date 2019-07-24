package model;

import java.util.Date;

public class AccountStorageModel {

    private String mEmail;
    private String mGender;
    private Date mBorn;
    
    public AccountStorageModel(String mEmail, String mGender, Date mBorn) {
        this.mEmail = mEmail;
        this.mGender = mGender;
        this.mBorn = mBorn;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmGender() {
        return mGender;
    }

    public Date getmBorn() {
        return mBorn;
    }
    
}
