package com.savitroday.savischools.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mohit on 9/28/2017.
 */

public class DateUtility {
    
    private static final String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
    
    public static String convertIntoAlphaDate(String date) {
        String[] dArray = date.split("-");
        if (dArray.length == 3) {
            return dArray[2] + " " + monthArray[Integer.parseInt(dArray[1]) - 1] + " " + dArray[0];
        }
        return "";
    }
    
    public static String getDateFull(Date date) {
        String str = "";
        
        Date today = new Date();
        
        long diff = today.getTime() - date.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) ((today.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
        int diffInWeeks = diffInDays / 7;
        
        if(diffInWeeks > 1){
            str += diffInWeeks + " Weeks";
        }
        else if(diffInWeeks == 1){
            str += diffInWeeks + " Week";
        }
        else if (diffInDays > 1) {
            str += diffInDays + " Days";
        }
        else if (diffInDays == 1) {
            str += diffInDays + " Day";
        } else if (diffHours > 1) {
            str += diffHours + " Hours";
        } else if (diffMinutes > 1) {
            str += new SimpleDateFormat("HH:mm a").format(date);
        } else {
            str += new SimpleDateFormat("HH:mm a").format(date);
        }
        return str;
    }
}
