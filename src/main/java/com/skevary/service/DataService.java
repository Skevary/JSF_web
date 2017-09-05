package com.skevary.service;

import com.skevary.model.DataBean;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@ManagedBean(name = "dataService")
@ApplicationScoped
public class DataService{
    private List<DataBean> dataBeans;
    private Date dateAfter;
    private Date dateBefore;
    private int numberGen = 50;

    @PostConstruct
    public void init() {
        generateData(5);
    }

    public List<DataBean> getFilteredData() {
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
            if ((bean.getDate().after(dateA) || bean.getDate().equals(dateA)) && (bean.getDate().before(dateB) || bean.getDate().equals(dateB)))
                filteredDataBeans.add(bean);

        return filteredDataBeans;
    }

    public void clearData() {
        dataBeans.clear();
    }

    public void generateData(int size) {
        dataBeans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String random_ID = getRandomId();
            String random_text = "item_" + random_ID;
            dataBeans.add(new DataBean(random_ID, getRandomNumber(), getRandomDate(), random_text));
        }

    }

    public void addData(int number, Date date, String text) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(date==null){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Date can't may be NULL!", "Please fill in the respective field."));
            return;
        }

        for (DataBean bean : dataBeans)
            if (bean.getDate().equals(date)) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Add Data - Warning!", "Given date already exists, try to choose other."));
                return;
            }

        dataBeans.add(new DataBean(getRandomId(), number, date, text));
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add Date - Success!", "The data was successfully added."));
    }

    public void removeItem(DataBean item) {
        dataBeans.remove(item);
    }

    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);  // standard UUID long 8 characters
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

    /**
     * Date Message
     */
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public List<DataBean> getDataBeans() {
        return dataBeans;
    }

    public void setDataBeans(List<DataBean> dataBeans) {
        this.dataBeans = dataBeans;
    }

    public Date getDateAfter() {
        return dateAfter;
    }

    public void setDateAfter(Date dateAfter) {
        this.dateAfter = dateAfter;
    }

    public Date getDateBefore() {
        return dateBefore;
    }

    public void setDateBefore(Date dateBefore) {
        this.dateBefore = dateBefore;
    }

    public int getNumberGen() {
        return numberGen;
    }

    public void setNumberGen(int numberGen) {
        this.numberGen = numberGen;
    }
}


