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
        animatedModel1.setLegendPosition("ne");
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

        LineChartSeries black = new LineChartSeries();
        black.setLabel(DataService.getText()[0]);
        LineChartSeries white = new LineChartSeries();
        white.setLabel(DataService.getText()[1]);
        LineChartSeries green = new LineChartSeries();
        green.setLabel(DataService.getText()[2]);
        LineChartSeries red = new LineChartSeries();
        red.setLabel(DataService.getText()[3]);
        LineChartSeries blue = new LineChartSeries();
        blue.setLabel(DataService.getText()[4]);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        if(!service.getDataBeans().isEmpty())
            for(DataBean dataBean : service.getDataBeans()){
                switch (dataBean.getText()) {
                    case "Black": black.set(format.format(dataBean.getDate()), dataBean.getNumber()); break;
                    case "White": white.set(format.format(dataBean.getDate()), dataBean.getNumber()); break;
                    case "Green": green.set(format.format(dataBean.getDate()), dataBean.getNumber()); break;
                    case "Red": red.set(format.format(dataBean.getDate()), dataBean.getNumber()); break;
                    default: blue.set(format.format(dataBean.getDate()), dataBean.getNumber()); break;
                }
            }

        model.addSeries(black);
        model.addSeries(white);
        model.addSeries(green);
        model.addSeries(red);
        model.addSeries(blue);

        return model;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries black = new ChartSeries();
        black.setLabel(DataService.getText()[0]);
        ChartSeries white = new ChartSeries();
        white.setLabel(DataService.getText()[1]);
        ChartSeries green = new ChartSeries();
        green.setLabel(DataService.getText()[2]);
        ChartSeries red = new ChartSeries();
        red.setLabel(DataService.getText()[3]);
        ChartSeries blue = new ChartSeries();
        blue.setLabel(DataService.getText()[4]);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        if(!service.getDataBeans().isEmpty())
            for(DataBean dataBean : service.getDataBeans()){
                switch (dataBean.getText()) {
                    case "Black": black.set(format.format(dataBean.getDate()), dataBean.getNumber()); break;
                    case "White": white.set(format.format(dataBean.getDate()), dataBean.getNumber()); break;
                    case "Green": green.set(format.format(dataBean.getDate()), dataBean.getNumber()); break;
                    case "Red": red.set(format.format(dataBean.getDate()), dataBean.getNumber()); break;
                    default: blue.set(format.format(dataBean.getDate()), dataBean.getNumber()); break;
                }
            }

        model.addSeries(black);
        model.addSeries(white);
        model.addSeries(green);
        model.addSeries(red);
        model.addSeries(blue);

        return model;
    }

    public void setService(DataService service) {
        this.service = service;
    }
}