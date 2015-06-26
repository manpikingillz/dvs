package ug.or.use.dvs.controller.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple date formatting utility class
 * @author Emre Simtay <emre@simtay.com>
 */

public class DateUtility {

	public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

	public static String getCurrentDateTime() {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            Date date = new Date();
            return dateFormat.format(date);
	}
       
}