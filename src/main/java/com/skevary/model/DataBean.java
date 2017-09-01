package com.skevary.model;

import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@ManagedBean
@ViewScoped
public class DataBean implements Serializable{
    private static final long serialVersionUID = -6363197086621921565L;
    private String id;
    private int number;
    private Date date;
    private String text;

    @PostConstruct
    public void init() {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }

    public DataBean() {
    }

    public DataBean(String id, int number, Date date, String text) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.text = text;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
