package com.skevary.service;

import com.skevary.model.DataBean;
import com.skevary.util.Message;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ManagedBean(name = "dataService")
@ApplicationScoped
public class DataService implements Message{
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

    public void addData(int number, String group, Date date, String text) {
        if(date==null){
            Message.showMessage("message.add_data.not_null.summary",
                    "message.add_data.not_null.detail");

            return;
        }

        for (DataBean bean : dataBeans)
            if (bean.getDate().equals(date)) {
                Message.showMessage("message.add_data.already_exists.summary",
                        "message.add_data.already_exists.detail");

                return;
            }
        String id = UUID.randomUUID().toString().substring(0, 8);
        dataBeans.add(new DataBean(id, group, number, date, text));
        Message.showMessage("message.add_data.success.summary",
                "message.add_data.success.detail");
    }

    public void removeItem(DataBean item) {
        dataBeans.remove(item);
    }

    /**
     * Date Message
     */
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                Message.getString("chart.on_date_selected.summary"), format.format(event.getObject())));
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


