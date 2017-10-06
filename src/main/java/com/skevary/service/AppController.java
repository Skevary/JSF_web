package com.skevary.service;

import com.skevary.Service;
import one.nio.rpc.client.RPCConnectionException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Date;

@ManagedBean(name = "appController",eager = true)
@ApplicationScoped
public class AppController{
    Service service = DataServiceRPCO.get();

    private int numberGen = 50;

    private Date dateAfter;
    private Date dateBefore;

    public AppController() throws RPCConnectionException {}

    @PostConstruct
    public void init(){
        service.generateData(5);
    }

    public void resetCalendars() {
        dateAfter = null;
        dateBefore = null;
    }

    public Service getService() {
        return service;
    }

    public int getNumberGen() {
        return numberGen;
    }

    public void setNumberGen(int numberGen) {
        this.numberGen = numberGen;
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
