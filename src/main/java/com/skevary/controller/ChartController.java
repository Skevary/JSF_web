package com.skevary.controller;

import com.skevary.model.DataBean;
import com.skevary.service.DataService;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean
public class ChartController {
    @ManagedProperty("#{dataService}")
    private DataService service;
    private LineChartModel firstAnimatedModel;
    private BarChartModel secondAnimatedModel;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public LineChartModel getFirstAnimatedModel() {
        return firstAnimatedModel;
    }

    public BarChartModel getSecondAnimatedModel() {
        return secondAnimatedModel;
    }


    private void createAnimatedModels() {
        firstAnimatedModel = initLinearModel();
        firstAnimatedModel.setTitle("Line Data Chart");
        firstAnimatedModel.setAnimate(true);
        firstAnimatedModel.setLegendPosition("se");

        firstAnimatedModel.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        DateAxis xAis = new DateAxis("Date");
        xAis.setMin(0);
        xAis.setMax(new Date().getTime());
        if(service.getDataBeans().size()<10) xAis.setTickAngle(-90);

        Axis yAxis = firstAnimatedModel.getAxis(AxisType.Y);
        yAxis.setLabel("Number");
        yAxis.setMin(0);
        yAxis.setMax(205);

//        DateAxis xAxis = new DateAxis("Dates");
//        if(service.getDataBeans().size()>10) xAxis.setTickAngle(-90);
//        xAxis.setMin(1970);
//        xAxis.setMax(new Date().getTime());
//        firstAnimatedModel.getAxes().put(AxisType.X, xAxis);


        secondAnimatedModel = initBarModel();
        secondAnimatedModel.setTitle("Bar Data Charts");
        secondAnimatedModel.setAnimate(true);
        secondAnimatedModel.setLegendPosition("ne");
        secondAnimatedModel.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        yAxis = secondAnimatedModel.getAxis(AxisType.Y);
        yAxis.setLabel("Number");
        yAxis.setMin(0);
        yAxis.setMax(205);

    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries groupA = new LineChartSeries();
        groupA.setLabel("Group A");
        LineChartSeries groupB = new LineChartSeries();
        groupB.setLabel("Group B");

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if (!service.getDataBeans().isEmpty())
            for (DataBean dataBean : service.getDataBeans()) {
                if (dataBean.getGroup().equals("A")) groupA.set(format.format(dataBean.getDate()), dataBean.getNumber());
                else groupB.set(format.format(dataBean.getDate()), dataBean.getNumber());
            }

//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        groupA.set(format.format(service.getDataBeans().get(0).getDate()), service.getDataBeans().get(0).getNumber());
//        groupA.set("2014-01-06", 22);
//        groupA.set("2014-01-12", 65);
//        groupA.set("2014-01-18", 74);
//        groupA.set("2014-01-24", 24);
//        groupA.set("2014-01-30", 51);
//
//
//        groupB.set(format.format(service.getDataBeans().get(1).getDate()), service.getDataBeans().get(1).getNumber());
//        groupB.set("2014-01-06", 73);
//        groupB.set("2014-01-12", 24);
//        groupB.set("2014-01-18", 12);
//        groupB.set("2014-01-24", 74);
//        groupB.set("2014-01-30", 62);

        model.addSeries(groupA);
        model.addSeries(groupB);

        return model;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries groupA = new ChartSeries();
        groupA.setLabel("Group A");
        ChartSeries groupB = new ChartSeries();
        groupB.setLabel("Group B");

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if (!service.getDataBeans().isEmpty())
            for (DataBean dataBean : service.getDataBeans()) {
                if (dataBean.getGroup().equals("A")) groupA.set(format.format(dataBean.getDate()), dataBean.getNumber());
                else groupB.set(format.format(dataBean.getDate()), dataBean.getNumber());
            }
        model.addSeries(groupA);
        model.addSeries(groupB);

        return model;
    }

    public void setService(DataService service) {
        this.service = service;
    }
}