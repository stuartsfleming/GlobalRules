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


public class dateActions { 
    /** 
     *JUDate  java.util.date
     * XGC    XMLGregorianCalendar
     */

     public static void main(String[] args) {

         Calendar cal = getCalendarDate();
         System.out.println("getCalendarDate:  " + cal );
         
         java.util.Date jud = getJUDate() ;
         System.out.println("getJUDate:  " + jud );
         
         XMLGregorianCalendar xgc = getXGCDate();
         System.out.println("getXGCDate:  " + xgc );
         
         System.out.println("xgcDateRemoveTstamp " + xgcDateRemoveTstamp(xgc)) ;
         // date formatted strings
         System.out.println("date long:  " + dateActions.judToFormatStrings("long",null));
         System.out.println("date med:  " + dateActions.judToFormatStrings("med",null));
         System.out.println("date short:  " + dateActions.judToFormatStrings("short",null));
         System.out.println("date short, with param:  " + dateActions.judToFormatStrings("short",jud));
         System.out.println("date other:  " + dateActions.judToFormatStrings("other",jud));
         
         // Calendar formatted strings
         System.out.println("Calendar long:  " + dateActions.CalendarToFormatStrings("long",null));
         System.out.println("Calendar med:  " + dateActions.CalendarToFormatStrings("med",null));
         System.out.println("Calendar short:  " + dateActions.CalendarToFormatStrings("short",null));
         System.out.println("Calendar short, with param:  " + dateActions.CalendarToFormatStrings("short",cal));
         System.out.println("Calendar other:  " + dateActions.CalendarToFormatStrings("other",cal));

         System.out.println("createCalendar " + getCalendarDate().toString());
         
     }


    public static Calendar getCalendarDate(){
        GregorianCalendar gcal = new GregorianCalendar();      
        return gcal.getInstance();
    }
    
    public static java.util.Date getJUDate() {
        Calendar cal = new GregorianCalendar();
        java.util.Date creationDate = cal.getTime(); 
        return creationDate ;
    }

    public static XMLGregorianCalendar getXGCDate() {
        GregorianCalendar gcal = new GregorianCalendar();       
        XMLGregorianCalendar xgc = null;
        try {
            xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return xgc;
    }
    
    public static XMLGregorianCalendar xgcDateRemoveTstamp(XMLGregorianCalendar xgcIn) {
        // this removes the timestamp that is appended on an XMLGregorianCalendar
        // input 1962-12-29-09:00 converts to  ouput  YYYY-MM-DD
 
        XMLGregorianCalendar xgc = null;

        try {
            xgc =
    DatatypeFactory.newInstance().newXMLGregorianCalendarDate(xgcIn.getYear(), xgcIn.getMonth(), xgcIn.getDay(), DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return xgc;
    }
    
        public static XMLGregorianCalendar juDateToXGCDate( java.util.Date jud) {

        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(jud);
        XMLGregorianCalendar xgc = null;
        try {
            xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
        return xgc;
    }

    public static java.sql.Date xgcToJSD(XMLGregorianCalendar xgc) {
        //  converts a XMLGregorianCalendar date (xs:date) to a java.sql.date

        java.util.Date dt = xgc.toGregorianCalendar().getTime();
        //Then transform the dt to java.sql.date
        java.sql.Date sqlDt = new java.sql.Date(dt.getTime());
        return sqlDt;
    }

    public static java.sql.Timestamp xgcToJSTimestamp(XMLGregorianCalendar xgc) {
        //  converts a XMLGregorianCalendar date (xs:dateTime) to a java.sql.Timestamp
        // Timestamp t = dateActions.xgcToJSTimestamp(xgc) ;
        if (xgc == null) {
            return null;
        }
        return new Timestamp(xgc.toGregorianCalendar().getTime().getTime());
    }

    public static java.util.Date toJUDate(XMLGregorianCalendar xgc) {
        if (xgc == null) {
            return null;
        }
        return xgc.toGregorianCalendar().getTime();
    }

    public static String xgcToString(XMLGregorianCalendar xgc) {
        Calendar cal = xgc.toGregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.setTimeZone(cal.getTimeZone());
        String dateString = formatter.format(cal.getTime());

        return dateString;
    }
    public static String getCalendarStrings (String type, Date inCal ) {
    
    return "x" ;
    }
    public static String judToFormatStrings (String type, Date dt) {
 
        SimpleDateFormat date_format;
        String dateString = null;
 
        String formattedDate ;
        if (dt == null) {
            dt = getJUDate();
        }    else {
            dt = dt;
        }
        
         if (type == "long") {
            date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            dateString= date_format.format(dt) ;
        } else if (type == "med") {
            date_format = new SimpleDateFormat("MMM dd,yyyy");
            dateString=  date_format.format(dt);
        } else if (type == "short") {
            date_format = new SimpleDateFormat("MM/dd/yyyy");
            dateString=  date_format.format(dt);
        } else {
            dateString=  dt.toString() ;
        }
        return dateString;
    }

    public static String CalendarToFormatStrings (String type, Calendar cal ) {
        String formattedDate ;
        if (cal == null) {
            cal = getCalendarDate();
        }    else {
            cal = cal;
        }
        
        java.util.Date creationDate = cal.getTime();
        SimpleDateFormat date_format;
        String s = null;
        
         if (type == "long") {
            date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            formattedDate= date_format.format(creationDate) ;
        } else if (type == "med") {
            date_format = new SimpleDateFormat("MMM dd,yyyy");
            formattedDate=  date_format.format(creationDate);
        } else if (type == "short") {
            date_format = new SimpleDateFormat("MM/dd/yyyy");
            formattedDate=  date_format.format(creationDate);
        } else {
            formattedDate=  creationDate.toString() ;
        }
        return formattedDate;
 
    }



}

