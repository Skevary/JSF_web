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
    private DataBean newDataBean;

    private final static String[] MESSAGE;

    static {
        MESSAGE = new String[10];
        MESSAGE[0] = "Black";
        MESSAGE[1] = "White";
        MESSAGE[2] = "Green";
        MESSAGE[3] = "Red";
        MESSAGE[4] = "Blue";
        MESSAGE[5] = "Orange";
        MESSAGE[6] = "Silver";
        MESSAGE[7] = "Yellow";
        MESSAGE[8] = "Brown";
        MESSAGE[9] = "Maroon";
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
            dataBeans.add(new DataBean( getRandomId(), getRandomNumber(), getRandomDate(), getRandomMessage()));
        }
    }

    //TODO: Remove this method;
    public void temporaryGenData(){
        generateData(10);
    }

    //TODO: This method is not working
    public void addData(){
        dataBeans.add(new DataBean(getRandomId()));
    }

    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private int getRandomNumber() {
        return new Random().nextInt(200 + 1);
    }

    //TODO: this method not work
    private Date getRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    //TODO: Rename to text
    private String getRandomMessage() {
        return MESSAGE[new Random().nextInt(9 + 1)];
    }

    public List<DataBean> getDataBeans() {
        return dataBeans;
    }

    public void setDataBeans(List<DataBean> dataBeans) {
        this.dataBeans = dataBeans;
    }
}
