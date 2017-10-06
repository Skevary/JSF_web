package com.skevary.model;

import org.omnifaces.cdi.ViewScoped;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@ManagedBean
@ViewScoped
public class DataItem implements Serializable {
    private static final long serialVersionUID = -6363197086621921565L;
    private String id;
    private String group;
    private int number;
    private Date date;
    private String text;

    public DataItem() {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }

    public DataItem(String id, String group, int number, Date date, String text) {
        this.id = id;
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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
