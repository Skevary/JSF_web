package com.skevary.controller;

import com.skevary.model.DataBean;
import com.skevary.service.DataService;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.Date;

@ManagedBean
public class ChartController {
    @ManagedProperty("#{dataService}")
    private DataService service;

    private LineChartModel animatedModel;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public LineChartModel getAnimatedModel() {
        return animatedModel;
    }

    private void createAnimatedModels() {
        animatedModel = initLinearModel();
        animatedModel.setTitle("Line Data Chart");
        animatedModel.setAnimate(true);
        animatedModel.setLegendPosition("ne");

        Axis yAxis = animatedModel.getAxis(AxisType.Y);
        yAxis.setLabel("Number");
        yAxis.setMin(-5);
        yAxis.setMax(210);

        DateAxis xAis = new DateAxis("Dates");
        xAis.setMax(new Date().getTime()+10000000000L);
        xAis.setTickFormat("%d-%m-%Y");
        animatedModel.getAxes().put(AxisType.X, xAis);
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series = new LineChartSeries();
        series.setLabel("Data");

        if (!service.getFilteredData().isEmpty())
            for (DataBean dataBean : service.getFilteredData())
                series.set(dataBean.getDate().getTime(), dataBean.getNumber());

        else
            series.set(null, null);

        model.addSeries(series);
        return model;
    }

    public void setService(DataService service) {
        this.service = service;
    }
}