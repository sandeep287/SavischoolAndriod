package com.savitroday.savischools.util;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Rahul Gautam (RG) on 22/04/16.
 */
public class DateDeserializer implements JsonDeserializer<Date> {
    //    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String[] DATE_FORMATS = new String[]{
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSX",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd hh:mm",
            "yyyy-dd-MM"
            
    };
//    public Date deserialize(JsonElement dateStr, Type typeOfSrc, JsonDeserializationContext context)
//    {
//        try
//        {
//            return dateFormat.parse(dateStr.getAsString());
//        }
//        catch (ParseException e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public Date deserialize(JsonElement jsonElement, Type typeOF,
                            JsonDeserializationContext context) throws JsonParseException {
        for (String format : DATE_FORMATS) {
            try {
                return new SimpleDateFormat(format).parse(jsonElement.getAsString());
            } catch (ParseException e) {
                Log.d("Parse error", "Date"+jsonElement.getAsString());
            }
        }
        throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
    }
}
