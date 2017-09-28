package com.skevary.controllers;

import com.skevary.model.DataBean;
import com.skevary.service.DataService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@ManagedBean
@SessionScoped
public class DataGenerationController {
    @ManagedProperty("#{dataService}")
    private DataService service;

    private final static String[] GROUP = {"A","B"};
    private int numberGen = 50;

    @PostConstruct
    public void init() {
        generateData(5);
    }

    public void generateData(int size) {
        service.setDataBeans(new ArrayList<>());

        for (int i = 0; i < size; i++) {
            String random_ID = getRandomId();
            String random_text = "item_" + random_ID;
            service.getDataBeans().add(new DataBean(random_ID, getRandomGroup(), getRandomNumber(), getRandomDate(), random_text));
        }

    }

    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);  // standard UUID long 8 characters
    }

    private String getRandomGroup() {
        return GROUP[new Random().nextInt(2)]; // only 0 or 1
    }

    private int getRandomNumber() {
        return new Random().nextInt(200 + 1);
    }

    private Date getRandomDate() {
        long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); // between {01.01.2000} and {current time}
    }

    public int getNumberGen() {
        return numberGen;
    }

    public void setNumberGen(int numberGen) {
        this.numberGen = numberGen;
    }

    public void setService(DataService service) {
        this.service = service;
    }
}
