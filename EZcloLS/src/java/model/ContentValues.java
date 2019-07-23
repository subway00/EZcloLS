/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author User
 */
public class ContentValues {

    private Hashtable hm = new Hashtable();

    public ContentValues() {
    }

    public void putInt(String tag, Integer num) {
        hm.put(tag, num);
    }

    public void putString(String tag, String txt) {
        hm.put(tag, "'" + txt + "'");
    }

    public void putCMD(String tag, String txt) {
        hm.put(tag, txt);
    }
    
    public void putDate(String tag, Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        hm.put(tag, "'"+sdf.format(date)+"'");
    }

    public String getKeys() {
        String result = "";
        Enumeration e = hm.keys();
        while (e!=null && e.hasMoreElements()) {
            String key = (String) e.nextElement();
            result = result.concat(key);

            if (e.hasMoreElements()) {
                result = result.concat(",");
            }
        }
        return result;
    }

    public String getValues() {
        String result = "";
        Enumeration e = hm.keys();
        while (e!=null && e.hasMoreElements()) {
            String key = e.nextElement().toString();
            
            result = result.concat(hm.get(key).toString());

            if (e.hasMoreElements()) {
                result = result.concat(",");
            }
        }
        return result;
    }

    public String getTable() {
        String result = "";
        Enumeration e = hm.keys();
        while (e!=null && e.hasMoreElements()) {
            String key = (String) e.nextElement();
            result = result.concat(key);
            result = result.concat("=");
            result = result.concat((String) hm.get(key));

            if (e.hasMoreElements()) {
                result = result.concat(",");
            }
        }
        return result;
    }

}
