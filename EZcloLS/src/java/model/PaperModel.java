/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class PaperModel {
    private String name;
    private String content;
    private String letter;
    private Date time;
    private int f_number;
    private int t_number;

    public PaperModel() {
        this.name = "";
        this.content = "";
        this.letter = "";
        this.time = null;
        this.f_number = -1;
        this.t_number = -1;
    }

    public PaperModel(String name, String content, String letter, Date time, int f_number, int t_number) {
        this.name = name;
        this.content = content;
        this.letter = letter;
        this.time = time;
        this.f_number = f_number;
        this.t_number = t_number;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getLetter() {
        return letter;
    }

    public Date getTime() {
        return time;
    }

    public int getF_number() {
        return f_number;
    }

    public int getT_number() {
        return t_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
    
    
    public void setContent(byte[] content) {
        if(content!=null){
            this.content = new String(content);
        }
    }

    public void setLetter(byte[] letter) {
        if(letter!=null){
            
            this.letter = new String(letter);
        }
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setF_number(int f_number) {
        this.f_number = f_number;
    }

    public void setT_number(int t_number) {
        this.t_number = t_number;
    }
    
    public String[] getAnswes(){
        return letter.split(",");
    }
    
}
