package com.skevary.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class DataGeneration {

    private final static String[] GROUP = {"A","B"};

    public String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);  // standard UUID long 8 characters
    }

    public String getRandomGroup() {
        return GROUP[new Random().nextInt(2)]; // only 0 or 1
    }

    public int getRandomNumber() {
        return new Random().nextInt(200 + 1);
    }

    public Date getRandomDate() {
        long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); // between {01.01.2000} and {current time}
    }
}
