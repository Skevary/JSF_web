package com.skevary.service;

import com.skevary.model.DataBean;
import com.skevary.util.Message;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "dataService", eager = true)
@ApplicationScoped
public class DataService implements Message {
    private List<DataBean> dataBeans;
    private Date dateAfter;
    private Date dateBefore;

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
            if ((bean.getDate().after(dateA) || bean.getDate().equals(dateA))
                    && (bean.getDate().before(dateB) || bean.getDate().equals(dateB)))
                filteredDataBeans.add(bean);

        return filteredDataBeans;
    }

    public void resetCalendars(){
        dateAfter = null;
        dateBefore = null;
    }

    public int getNumberAllRecords(){
        return dataBeans.size();
    }

    public void clearData() {
        dataBeans.clear();
    }

    public void addData(DataBean data) {
        dataBeans.add(data);
        Message.showMessage("message.add_data.success.summary",
                "message.add_data.success.detail", FacesMessage.SEVERITY_INFO);
    }

    public void removeItem(DataBean item) {
        dataBeans.remove(item);
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


