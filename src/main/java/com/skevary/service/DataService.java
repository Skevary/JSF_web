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
public class DataService {
    private List<DataBean> dataBeans;
    private Date dateAfter;
    private Date dateBefore;

    @PostConstruct
    public void init() {
        generateData(5);
    }

    public List<DataBean> getFilteredData() {
        List<DataBean> filteredDataBeans = new ArrayList<>();
        if(dateAfter==null) dateAfter = new Date(0);
        if(dateBefore==null) dateBefore = new Date();

        for (DataBean bean : dataBeans)
            if(bean.getDate().after(dateAfter) && bean.getDate().before(dateBefore))
                filteredDataBeans.add(bean);

        return filteredDataBeans;
    }

    public void clearData() {
        dataBeans.clear();
    }

    private void generateData(int size) {
        dataBeans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String random_ID = getRandomId();
            String random_text = "item_" + random_ID;
            dataBeans.add(new DataBean(random_ID, getRandomNumber(), getRandomDate(), random_text));
        }
    }

    //TODO: Remove this method;
    public void temporaryGenData() {
        generateData(50);
    }

    public void addData(int number, Date date, String text) {
        dataBeans.add(new DataBean(getRandomId(), number, date, text));
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

    /**
     * Ajax date messages
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
}


