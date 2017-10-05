package com.skevary.service;

import com.skevary.DataService;
import one.nio.rpc.client.RPCConnectionException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;

@ManagedBean(name = "server",eager = true)
@ApplicationScoped
public class Service implements Serializable{

    private static final long serialVersionUID = 271820448473039991L;
    DataService service = DataServiceRPCO.get();
    private int numberGen = 50;
    private Date dateAfter;
    private Date dateBefore;

    @PostConstruct
    public void init(){
        try {
            service.generateData(15);
        } catch (RPCConnectionException e) {
            e.printStackTrace();
        }
    }

    public Service() throws RPCConnectionException {}

    public DataService getService() {
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
