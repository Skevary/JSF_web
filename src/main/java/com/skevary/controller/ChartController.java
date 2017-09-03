package com.skevary.controller;

import com.skevary.model.DataBean;
import com.skevary.service.DataService;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.text.SimpleDateFormat;

@ManagedBean
public class ChartController {
    @ManagedProperty("#{dataService}")
    private DataService service;
    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }


    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Line Data Chart");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        animatedModel1.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Number");
        yAxis.setMin(0);
        yAxis.setMax(205);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Bar Data Charts");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        animatedModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        yAxis = animatedModel2.getAxis(AxisType.Y);
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

        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        if (!service.getDataBeans().isEmpty())
            for (DataBean dataBean : service.getDataBeans()) {
                if (dataBean.getGroup().equals("A")) groupA.set(format.format(dataBean.getDate()), dataBean.getNumber());
                else groupB.set(format.format(dataBean.getDate()), dataBean.getNumber());
            }

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

        SimpleDateFormat format = new SimpleDateFormat("yyyy");
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