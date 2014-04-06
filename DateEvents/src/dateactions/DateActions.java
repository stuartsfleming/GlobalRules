package dateevents;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class DateEvents {
    /** http://javarevisited.blogspot.com/2013/02/convert-xmlgregoriancalendar-to-date-xmlgregoriancalendar-java-example-tutorial.html
     * Java program to convert XMLGregorianCalendar to Date and inverse i.e. java.util.Date
     * to XMLGregorianCalendar. If you are using XJC to create Java classes from XML Schema
     * or XSD file, By default JAXB map XSD data types xs:date, xs:time and xs:dateTime
     * to XMLGregorianCalendar in Java.
     *
     * @author Javin Paul
     */

    public static XMLGregorianCalendar getXMLGC_DateOnly(XMLGregorianCalendar xGCal) {
        // this removes the timestamp that is appended on an XMLGregorianCalendar
        // input 1964-02-26-05:00  ouput  YYYY-MM-DD
        // XMLGregorianCalendar x = DateEvents.getXMLGC_DateOnly(xGCal) ;
        XMLGregorianCalendar x = null;

        try {
            x =
  DatatypeFactory.newInstance().newXMLGregorianCalendarDate(xGCal.getYear(), xGCal.getMonth(), xGCal.getDay(), DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return x;
    }


    public static XMLGregorianCalendar createGregorianCalendarDate() {
        // creates a new xgcal date
        GregorianCalendar gcal = new GregorianCalendar();
        XMLGregorianCalendar xgcal = null;
        try {
            xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return xgcal;
    }
    /*
         * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
         */

    public static XMLGregorianCalendar toXMLGregorianCalendarDate(Date date) {

        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
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

    public static java.util.Date toJavaUtilDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null ;
        }
        return calendar.toGregorianCalendar().getTime();
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
    public static Date FormattingExamples () {
        Calendar cal = new GregorianCalendar();
         java.util.Date creationDate = cal.getTime();
        System.out.println("creationDate before format is: " + creationDate.toString());
        SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        System.out.println(date_format.format(creationDate));
        date_format = new SimpleDateFormat("MMM dd,yyyy");
        System.out.println(date_format.format(creationDate));
        date_format = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println(date_format.format(creationDate));
        
        return creationDate;
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
      System.out.println(  DateEvents.getJavaUtilDate() );

    }
}

