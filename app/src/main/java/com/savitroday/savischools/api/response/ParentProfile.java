package com.savitroday.savischools.api.response;

/**
 * Created by Harshita Ahuja on 30/09/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParentProfile {
    
    @SerializedName("parentId")
    @Expose
    public Object parentId;
    @SerializedName("createdOn")
    @Expose
    public Object createdOn;
    @SerializedName("schoolId")
    @Expose
    public Integer schoolId;
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("middleName")
    @Expose
    public String middleName;
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("country")
    @Expose
    public Object country;
    @SerializedName("countryName")
    @Expose
    public String countryName;
    @SerializedName("state")
    @Expose
    public Object state;
    @SerializedName("stateName")
    @Expose
    public String stateName;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("addressLine1")
    @Expose
    public String addressLine1;
    @SerializedName("adressLine2")
    @Expose
    public Object adressLine2;
    @SerializedName("postCode")
    @Expose
    public String postCode;
    @SerializedName("phone1")
    @Expose
    public String phone1;
    @SerializedName("emailAddress")
    @Expose
    public String emailAddress;
    public String getName()
    {
        StringBuilder sb = new StringBuilder();
        for (String s : Arrays.asList(firstName,middleName,lastName)) {
            if (s != null) {
                sb.append(s).append(' ');
            }
        }
        String itemList = sb.toString();
           return itemList;
                   //(firstName + " " + middleName + " " + lastName);
    }


}



