package com.skevary.controller;

import com.skevary.model.DataBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@ManagedBean
@ApplicationScoped
public class DataService {
    private List<DataBean> dataBeans;

    private final static String[] TEXT;

    static {
        TEXT = new String[10];
        TEXT[0] = "Black";
        TEXT[1] = "White";
        TEXT[2] = "Green";
        TEXT[3] = "Red";
        TEXT[4] = "Blue";
        TEXT[5] = "Orange";
        TEXT[6] = "Silver";
        TEXT[7] = "Yellow";
        TEXT[8] = "Brown";
        TEXT[9] = "Maroon";
    }


    @PostConstruct
    public void init() {
        generateData(5);
    }

    public void clearData(){
        dataBeans.clear();
    }

    //TODO: Update modifi public to private
    public void generateData(int size) {
        dataBeans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            dataBeans.add(new DataBean( getRandomId(), getRandomNumber(), getRandomDate(), getRandomText()));
        }
    }

    //TODO: Remove this method;
    public void temporaryGenData(){
        generateData(50);
    }

    public void addData(int number, Date date, String text){
        dataBeans.add(new DataBean(getRandomId(),number,date,text));
    }

    public void removeItem(DataBean item){
        dataBeans.remove(item);
    }

    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private int getRandomNumber() {
        return new Random().nextInt(200 + 1);
    }

    private Date getRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    //TODO: Rename to text
    private String getRandomText() {
        return TEXT[new Random().nextInt(9 + 1)];
    }

    public List<DataBean> getDataBeans() {
        return dataBeans;
    }

    public void setDataBeans(List<DataBean> dataBeans) {
        this.dataBeans = dataBeans;
    }
}
