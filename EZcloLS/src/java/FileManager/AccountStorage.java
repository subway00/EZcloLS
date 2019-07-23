package FileManager;

import java.util.Date;

public class AccountStorage {

    private String mEmail;
    private String mGender;
    private Date mBorn;
    
    public AccountStorage(String mEmail, String mGender, Date mBorn) {
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
