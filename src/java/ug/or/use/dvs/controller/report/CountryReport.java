/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.or.use.dvs.controller.report;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.omnifaces.util.Faces;
import ug.or.use.dvs.facade.CountryFacade;
import ug.or.use.dvs.model.Country;

/**
 *
 * @author Dell
 */
@Named
@ViewScoped
public class CountryReport implements Serializable {

    private JRBeanCollectionDataSource beanCollectionDataSource;
    private JasperPrint jasperPrint;
    private HttpServletResponse httpServletResponse;
    private HttpServletRequest request;
    @Inject
    private CountryFacade countryFacade;
    private List<Country> countryList;

    /**
     * Creates a new instance of CountryReport
     */
    public CountryReport() {
    }

    public List<Country> getCountryList() {
        return countryList = countryFacade.findAll();
    }

    public void init() throws JRException {
        beanCollectionDataSource = new JRBeanCollectionDataSource(getCountryList());
        //jasperPrint = JasperFillManager.fillReport("/home/ahabwe/NetBeansProjects/ReportDesigning/Jrxml/Letter.jasper", new HashMap(), beanCollectionDataSource);
//        "C:"+File.separator+"Users"+File.separator+"Dell"+File.separator+"JaspersoftWorkspace"+File.separator+"MyReports"+File.separator+"ChargeSheet.jasper"
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/country.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public void PDF() throws JRException, IOException {
        init();
//        httpServletResponse = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "inline; filename=report_gillz.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//        InputStream reportStream = Faces.getServletContext().getResourceAsStream("/reports/country.jasper");
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
//        try {
//            JasperRunManager.runReportToPdfStream(reportStream,
//                    servletOutputStream, new HashMap(), beanCollectionDataSource);
//
//            httpServletResponse.setContentType("application/pdf");
//            servletOutputStream.flush();
//            servletOutputStream.close();
//        } catch (JRException e) {
//// display stack trace in the browser
//            StringWriter stringWriter = new StringWriter();
//            PrintWriter printWriter = new PrintWriter(stringWriter);
//            e.printStackTrace(printWriter);
//            httpServletResponse.setContentType("text/plain");
//            httpServletResponse.getOutputStream().print(stringWriter.toString());
//        }
    }
    
    public void VIEWPDF(ActionEvent actionEvent) throws JRException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        File reportFile = new File(Faces.getServletContext()
                .getRealPath("/reports/country.jasper"));
        byte[] bytes = null;
        getCountryList();
        beanCollectionDataSource = new JRBeanCollectionDataSource(countryList);

        try {
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),
                    new HashMap(), new JREmptyDataSource());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);

            servletOutputStream.write(bytes, 0, bytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (JRException e) {
            // display stack trace in the browser
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
        }
    }
}



