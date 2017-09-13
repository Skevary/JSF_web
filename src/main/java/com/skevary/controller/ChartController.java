package com.skevary.controller;

import com.skevary.model.DataBean;
import com.skevary.service.DataService;
import com.skevary.util.Message;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.Date;

@ManagedBean
public class ChartController implements Message {
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
        animatedModel.setAnimate(true);
        animatedModel.setLegendPosition("ne");

        Axis yAxis = animatedModel.getAxis(AxisType.Y);
        yAxis.setLabel(Message.getString("chart.y_axis.label"));
        yAxis.setMin(-5);
        yAxis.setMax(210);


        DateAxis xAxis = new DateAxis(Message.getString("chart.x_axis.label"));

        if (!service.getFilteredData().isEmpty()) {
            Long max_xAxis = service.getFilteredData().stream().map(DataBean::getDate).max(Date::compareTo).get().getTime();
            xAxis.setMax(max_xAxis + 30000000000L); // increases the threshold xAxis
        }

        xAxis.setTickFormat("%d-%m-%Y");
        animatedModel.getAxes().put(AxisType.X, xAxis);
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries groupA = new LineChartSeries();
        groupA.setLabel(Message.getString("chart.linear_model.groupA.label"));

        LineChartSeries groupB = new LineChartSeries();
        groupB.setLabel(Message.getString("chart.linear_model.groupB.label"));

        if (!service.getFilteredData().isEmpty())
            for (DataBean dataBean : service.getFilteredData())
                if (dataBean.getGroup().equals("A")) groupA.set(dataBean.getDate().getTime(), dataBean.getNumber());
                else groupB.set(dataBean.getDate().getTime(), dataBean.getNumber());


        if (groupA.getData().isEmpty()) groupA.set(null, null);
        if (groupB.getData().isEmpty()) groupB.set(null, null);

        model.addSeries(groupA);
        model.addSeries(groupB);
        return model;
    }

    public void setService(DataService service) {
        this.service = service;
    }
}