package dateactions;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class DateActions {
    /** http://javarevisited.blogspot.com/2013/02/convert-xmlgregoriancalendar-to-date-xmlgregoriancalendar-java-example-tutorial.html
     * Java program to convert XMLGregorianCalendar to Date and inverse i.e. java.util.Date
     * to XMLGregorianCalendar. If you are using XJC to create Java classes from XML Schema
     * or XSD file, By default JAXB map XSD data types xs:date, xs:time and xs:dateTime
     * to XMLGregorianCalendar in Java.
     *
     * @author Javin Paul
     */



    public static XMLGregorianCalendar createXMLGregorianCalendarDate() {
        // creates a new xgcal date
        GregorianCalendar gcal = new GregorianCalendar();
        XMLGregorianCalendar xgc = null;
        try {
            xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return xgc;
    }
    
    public static XMLGregorianCalendar xmlGCDateRemoveTstamp(XMLGregorianCalendar xgc) {
        // this removes the timestamp that is appended on an XMLGregorianCalendar
        // input 1964-02-26-05:00  ouput  YYYY-MM-DD
        // XMLGregorianCalendar x = DateEvents.getXMLGC_DateOnly(xGCal) ;
        XMLGregorianCalendar x = null;

        try {
            x =
    DatatypeFactory.newInstance().newXMLGregorianCalendarDate(xgc.getYear(), xgc.getMonth(), xgc.getDay(), DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return x;
    }
    
    
    /*
         * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
         */

    public static XMLGregorianCalendar toXMLGregorianCalendarDate( java.util.Date jud) {

        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(jud);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
        return xmlCalendar;
    }

    /*
         * Converts XMLGregorianCalendar to java.util.Date in Java
         */

    public static java.sql.Date toJavaSQLDate(XMLGregorianCalendar xgc) {
        //  converts a XMLGregorianCalendar date (xs:date) to a java.sql.date

        java.util.Date dt = xgc.toGregorianCalendar().getTime();
        //Then transform the dt to java.sql.date
        java.sql.Date sqlDt = new java.sql.Date(dt.getTime());
        return sqlDt;
    }

    public static java.sql.Timestamp toJavaSQLTimestamp(XMLGregorianCalendar xgc) {
        //  converts a XMLGregorianCalendar date (xs:dateTime) to a java.sql.Timestamp
        // Timestamp t = DateEvents.toJavaTimestamp(xgc) ;
        if (xgc == null) {
            return null;
        }
        return new Timestamp(xgc.toGregorianCalendar().getTime().getTime());
    }

    public static java.util.Date toJavaUtilDate(XMLGregorianCalendar xgc) {
        if (xgc == null) {
            return null;
        }
        return xgc.toGregorianCalendar().getTime();
    }

    public static String GregorianCalToString(XMLGregorianCalendar cal) {
        //String dt = util.DateEvents.GregorianCalToString( GregCal) ;
        Calendar calendar = cal.toGregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.setTimeZone(calendar.getTimeZone());
        String dateString = formatter.format(calendar.getTime());

        return dateString;
    }

    public static java.util.Date getJavaUtilDate() {
        Calendar cal = new GregorianCalendar();
        java.util.Date creationDate = cal.getTime(); 
        return creationDate ;
    }

    public static String getDateStrings (String type, Calendar inCal ) {
        Calendar cal ;
        
        if (inCal == null) {
            cal = new GregorianCalendar();
        }    else {
            cal = inCal;
        }
        
        java.util.Date creationDate = cal.getTime();
        SimpleDateFormat date_format;
        String s = null;
        
         if (type == "full") {
            date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            return date_format.format(creationDate) ;
        } else if (type == "short") {
            date_format = new SimpleDateFormat("MMM dd,yyyy");
            return  date_format.format(creationDate);
        } else if (type == "mmddyyyy") {
            date_format = new SimpleDateFormat("MM/dd/yyyy");
            return  date_format.format(creationDate);
        } else {
            return  creationDate.toString() ;
        }
 
 
    }


    public static void main(String[] args) {


        java.util.Date today = new java.util.Date();
        //Converting date to XMLGregorianCalendar in Java
        /*        XMLGregorianCalendar xmlDate = DateEvents.toXMLGregorianCalendarDate(today);
        System.out.println("XMLGregorianCalendar from java.util.Date: " + xmlDate);

        //convert Gregorian Calendar to string
        Strin dt = GregorianCalToString(xmlDate);
        System.out.println("Gregorian Calendar in String format: " + dt);

        //Converting XMLGregorianCalendar to java.util.Date in Java
        Date javaDate = DateEvents.toJavaUtilDate(xmlDate);
        System.out.println("java.util.Date from XMLGregorianCalendar: " + javaDate);

        // java.sql.Timestamp toJavaTimestamp
        java.sql.Timestamp t = DateEvents.toJavaSQLTimestamp(xmlDate);
        System.out.println("java.sql.Timestamp from XMLGregorianCalendar: " + javaDate);
*/
        System.out.println(DateActions.getDateStrings("full",null));
        System.out.println(DateActions.getDateStrings("short",null));
        System.out.println(DateActions.getDateStrings("mmddyyyy",null));
        System.out.println(DateActions.getDateStrings("x",null));
    }
}

