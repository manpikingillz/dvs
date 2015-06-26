/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.or.use.dvs.controller.chart;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author ALLEN
 */
@Named
@ViewScoped
public class AlsiLineChartController {

    private CartesianChartModel model;

    public AlsiLineChartController() {
        model = new CartesianChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("ALSI");

        boys.set("January", 900);
        boys.set("February", 1200);
        boys.set("March", 1100);
        boys.set("April", 1500);
        boys.set("May", 1600);
        boys.set("June", 1580);
        boys.set("July", 1700);
        boys.set("August", 1900);
        boys.set("September", 1500);
        boys.set("October", 1300);
        boys.set("November", 1900);
        boys.set("December", 2000);
//        
//        ChartSeries girls = new ChartSeries();
//        girls.setLabel("Girls");
//
//        girls.set("2004", 52);
//        girls.set("2005", 60);
        model.addSeries(boys);
//        model.addSeries(girls);
    }

    public CartesianChartModel getModel() {
        return model;
    }
}
