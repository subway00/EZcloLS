/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class RecordModel {

    private int right;
    private int wrong;
    private Date datetime;
    private int number;
    
    public RecordModel() {
        this.right = 0;
        this.wrong = 0;
        this.datetime = null;
        this.number = 0;
    }

    public RecordModel(int right, int wrong, Date datetime, int number) {
        this.right = right;
        this.wrong = wrong;
        this.datetime = datetime;
        this.number = number;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    
    
}
