/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.or.use.dvs.controller;

//import com.google.common.collect.Table.Cell;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.FileUploadEvent;
import ug.or.use.dvs.facade.BondDailyPriceFacade;
import ug.or.use.dvs.facade.BondFacade;
import ug.or.use.dvs.facade.EquityDailyPriceFacade;
import ug.or.use.dvs.facade.EquityFacade;
import ug.or.use.dvs.model.Bond;
import ug.or.use.dvs.model.BondDailyPrice;
import ug.or.use.dvs.model.EquityDailyPrice;
//import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author Dell
 */
@Named
@ViewScoped
public class FileUploadController {
@Inject 
private BondDailyPriceFacade bondDailyPriceFacade;
@Inject
private EquityDailyPriceFacade equityDailyPriceFacade;
private BondDailyPrice bondDailyPrice = new BondDailyPrice();
private EquityDailyPrice equityDailyPrice = new EquityDailyPrice();
private Bond bond = new Bond();
@Inject
private BondFacade bondFacade;
@Inject
private EquityFacade equityFacade;
@Inject
private EquityMarketDayController equityMarketDayController;
    /**
     * Creates a new instance of FileUploadController
     */
    public FileUploadController() {
    }

    public void uploadBondDailyPriceExcel(FileUploadEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (event.getFile().equals(null)) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File is null", null));
        }
        InputStream file;
        HSSFWorkbook workbook = null;
        try {
            //Get the uploaded excel file
            file = event.getFile().getInputstream();
            // Get the workbook instance for XLS or XLSX file
            workbook = new HSSFWorkbook(file);
        } catch (IOException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error reading file" + e, null));
        }
        // Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
        // Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();

        Calendar calendar = new GregorianCalendar();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        cell.getBooleanCellValue();
                        System.out.print("Boolean Detected" + "\t\t");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        cell.getNumericCellValue();
                        
                        //Check if it is Date
                        if (HSSFDateUtil.isCellDateFormatted(cell) || HSSFDateUtil.isCellInternalDateFormatted(cell)) {
                            calendar.setTime(cell.getDateCellValue());
                            System.out.print(calendar.getTime());
                        } else {
                            System.out.print(cell.getNumericCellValue() + "\t\t");
                            
                            //Data for bond daily price - For Numeric Columns
                            if(cell.getColumnIndex() == 1){
                                bondDailyPrice.setTradedYield(Float.parseFloat(String.valueOf(cell.getNumericCellValue())));
                            }
                            else if(cell.getColumnIndex() == 2){
                                bondDailyPrice.setDirtyPrice(Float.parseFloat(String.valueOf(cell.getNumericCellValue())));
                            }
                            else if(cell.getColumnIndex() == 3){
                                bondDailyPrice.setCleanPrice(Float.parseFloat(String.valueOf(cell.getNumericCellValue())));
                            }
                        }
//                        System.out.print("Number Detected" + "\t\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        //cell.getStringCellValue();
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        //Data for bond daily price - Bond
                        if(cell.getColumnIndex() == 0){
                            bondDailyPrice.setBond(bondFacade.getEntityRowGivenColumnValue("shortName", cell.getStringCellValue()));
                        }
                        break;
                }

            }
            
            bondDailyPriceFacade.create(bondDailyPrice);
        }

        facesContext.addMessage(null, new FacesMessage("ExcelSheet Uploaded"));

    }
    
    public void uploadEquityDailyPriceExcel(FileUploadEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (event.getFile().equals(null)) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File is null", null));
        }
        InputStream file;
        HSSFWorkbook workbook = null;
        try {
            //Get the uploaded excel file
            file = event.getFile().getInputstream();
            // Get the workbook instance for XLS or XLSX file
            workbook = new HSSFWorkbook(file);
        } catch (IOException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error reading file" + e, null));
        }
        // Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
        // Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();

        Calendar calendar = new GregorianCalendar();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        cell.getBooleanCellValue();
                        System.out.print("Boolean Detected" + "\t\t");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        cell.getNumericCellValue();
                        
                        //Check if it is Date
                        if (HSSFDateUtil.isCellDateFormatted(cell) || HSSFDateUtil.isCellInternalDateFormatted(cell)) {
                            calendar.setTime(cell.getDateCellValue());
                            System.out.print(calendar.getTime());
                        } else {
                            System.out.print(cell.getNumericCellValue() + "\t\t");
                            
                            //Data for equity daily price - For Numeric Columns
                            if(cell.getColumnIndex() == 1){
                                equityDailyPrice.setSharesTraded(Integer.parseInt(String.valueOf(cell.getStringCellValue())));
                            }
                            else if(cell.getColumnIndex() == 2){
                                equityDailyPrice.setTurnOver(Integer.parseInt(String.valueOf(cell.getStringCellValue())));
                            }
                            else if(cell.getColumnIndex() == 3){
                                equityDailyPrice.setVwap(Integer.parseInt(String.valueOf(cell.getStringCellValue())));
                            }
                            else if(cell.getColumnIndex() == 4){
                                equityDailyPrice.setLow(Integer.parseInt(String.valueOf(cell.getStringCellValue())));
                            }
                            else if(cell.getColumnIndex() == 5){
                                equityDailyPrice.setHigh(Integer.parseInt(String.valueOf(cell.getStringCellValue())));
                            }
                            else if(cell.getColumnIndex() == 6){
                                equityDailyPrice.setOutstandingBid(Integer.parseInt(String.valueOf(cell.getStringCellValue())));
                            }
                            else if(cell.getColumnIndex() == 7){
                                equityDailyPrice.setOutstandingOffer(Integer.parseInt(String.valueOf(cell.getStringCellValue())));
                            }
                            else if(cell.getColumnIndex() == 8){
                                equityDailyPrice.setPERatio(Float.parseFloat(String.valueOf(cell.getStringCellValue())));
                            }
                            else if(cell.getColumnIndex() == 9){
                                equityDailyPrice.setMarketCap(Float.parseFloat(String.valueOf(cell.getStringCellValue())));
                            }
                        }
//                        System.out.print("Number Detected" + "\t\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        //cell.getStringCellValue();
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        //Data for bond daily price - Bond
                        if(cell.getColumnIndex() == 0){
                            equityDailyPrice.setEquity(equityFacade.getEntityRowGivenColumnValue("shortName", cell.getStringCellValue()));
                        }
                        break;
                }

            }
            //Insert current the date
            equityDailyPrice.setAutoTimestamp(new Date());
            equityDailyPrice.setEquityMarketDay(equityMarketDayController.getSelected());
            equityDailyPriceFacade.create(equityDailyPrice);
        }

        facesContext.addMessage(null, new FacesMessage("ExcelSheet Uploaded"));

    }
}
