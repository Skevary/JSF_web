package com.skevary;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ChartView {

    private BarChartModel animatedModel;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public BarChartModel getAnimatedModel() {
        System.out.println(animatedModel.getTitle());
        return animatedModel;
    }

    private void createAnimatedModels() {
        animatedModel = initBarModel();
        animatedModel.setTitle("Bar Charts");
        animatedModel.setAnimate(true);
        animatedModel.setLegendPosition("ne");
        Axis yAxis = animatedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Dogs");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Cats");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }
}