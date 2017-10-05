package com.skevary.service;

import com.skevary.controllers.DataGeneration;
import com.skevary.model.DataBean;

import javax.annotation.PostConstruct;
import java.util.*;

//@ManagedBean(name = "server",eager = true)
//@ApplicationScoped
public class DataService2 {
    private static final Map<String, String> users;
    static {
        users = new HashMap<>();
        users.put("foo@mail.com", "12345");
        users.put("bar@mail.com", "12345");
    }

    private List<DataBean> dataBeans;
    private Date dateAfter;
    private Date dateBefore;
    private int numberGen = 50;

    @PostConstruct
    public void init() {
        generateData(5);
    }

    public List<DataBean> getFilteredData() {
        System.out.println("getFilteredData ");

        List<DataBean> filteredDataBeans = new ArrayList<>();
        Date dateA = dateAfter;
        Date dateB = dateBefore;

        if (dateAfter == null && dateBefore == null) return dataBeans;
        //Get the min of the available dates or create new Date(0) - 1970/01/01;
        if (dateA == null)
            dateA = dataBeans.isEmpty() ? new Date(0) : dataBeans.stream().map(DataBean::getDate).min(Date::compareTo).get();

        //Get the max of the available dates or create new Date() - current date;
        if (dateB == null)
            dateB = dataBeans.isEmpty() ? new Date() : dataBeans.stream().map(DataBean::getDate).max(Date::compareTo).get();

        for (DataBean bean : dataBeans)
            if ((bean.getDate().after(dateA) || bean.getDate().equals(dateA))
                    && (bean.getDate().before(dateB) || bean.getDate().equals(dateB)))
                filteredDataBeans.add(bean);

        return filteredDataBeans;
    }

    public void generateData(int size) {
        System.out.println("generateData: " + size);

        dataBeans = new ArrayList<>();
        DataGeneration data = new DataGeneration();

        for (int i = 0; i < size; i++) {
            String random_ID = data.getRandomId();
            String random_text = "item_" + random_ID;
            dataBeans.add(new DataBean(random_ID, data.getRandomGroup(), data.getRandomNumber(), data.getRandomDate(), random_text));
        }

    }

    public void resetCalendars(){
        System.out.println("resetCalendars ");

        dateAfter = null;
        dateBefore = null;
    }

    public int getNumberAllRecords(){
        System.out.println("getNumberAllRecords ");

        return dataBeans.size();
    }

    public void clearData() {
        System.out.println("clearData ");

        dataBeans.clear();
    }

    public void addData(DataBean data) {
        System.out.println("addItem: " + data.toString());

        dataBeans.add(data);
    }

    public void removeItem(DataBean item) {
        System.out.println("removeItem: " + item.toString());

        dataBeans.remove(item);
    }

    public List<DataBean> getDataBeans() {
        System.out.println("getDataBeans ");

        return dataBeans;
    }

    public void setDataBeans(List<DataBean> dataBeans) {
        System.out.println("setDataBeans: "+ dataBeans.toString());

        this.dataBeans = dataBeans;
    }

    public Date getDateAfter() {
        System.out.println("getDateAfter ");

        return dateAfter;
    }

    public void setDateAfter(Date dateAfter) {
        System.out.println("setDateAfter: " + dateAfter.toString());

        this.dateAfter = dateAfter;
    }

    public Date getDateBefore() {
        System.out.println("getDateBefore ");

        return dateBefore;
    }

    public void setDateBefore(Date dateBefore) {
        System.out.println("setDateBefore: " + dateBefore.toString());

        this.dateBefore = dateBefore;
    }

    public static Map<String, String> getUsers() {
        return users;
    }

    public int getNumberGen() {
        System.out.println("getNumberGen ");

        return numberGen;
    }

    public void setNumberGen(int numberGen) {
        System.out.println("setNumberGen: " + numberGen);

        this.numberGen = numberGen;
    }
}


