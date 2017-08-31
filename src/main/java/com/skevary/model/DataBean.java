package com.skevary.model;

import javax.faces.bean.ManagedBean;
import java.util.Date;

@ManagedBean
public class DataBean {
    private Date date;
    private String id;
    private int number;
    private String text;

    public DataBean(Date date, String id, int number, String text) {
        this.date = date;
        this.id = id;
        this.number = number;
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
