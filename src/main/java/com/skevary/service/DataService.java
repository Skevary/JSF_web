package com.skevary.service;

import com.skevary.model.DataBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@ManagedBean(name = "dataService")
@ApplicationScoped
public class DataService {
    private List<DataBean> dataBeans;
    private final static String[] GROUP;

    static {
        GROUP = new String[2];
        GROUP[0] = "A";
        GROUP[1] = "B";
    }


    @PostConstruct
    public void init() {
        generateData(5);
    }

    public void clearData() {
        dataBeans.clear();
    }

    private void generateData(int size) {
        dataBeans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String random_ID = getRandomId();
            String random_text = "item_" + (random_ID.substring(0,4));
            dataBeans.add(new DataBean(random_ID, getRandomGroup(), getRandomNumber(), getRandomDate(), random_text));
        }
    }

    //TODO: Remove this method;
    public void temporaryGenData() {
        generateData(50);
    }

    public void addData(String group, int number, Date date, String text) {
        dataBeans.add(new DataBean(getRandomId(), group, number, date, text));
    }

    public void removeItem(DataBean item) {
        dataBeans.remove(item);
    }

    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);  // standard UUID long 8 characters
    }

    private int getRandomNumber() {
        return new Random().nextInt(200 + 1);  // between 0 and 200
    }

    private Date getRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); // between {01.01.1970} and {current time}
    }

    private String getRandomGroup() {
        return GROUP[new Random().nextInt(2)]; // only 0 or 1
    }


    public List<DataBean> getDataBeans() {
        return dataBeans;
    }

    public static String[] getGROUP() {
        return GROUP;
    }

    public void setDataBeans(List<DataBean> dataBeans) {
        this.dataBeans = dataBeans;
    }
}
