package com.example.khang.sharecar.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Date getDateFromString(String stringDate) throws Exception{
        String DEFAULT_PATTERN = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);

        return formatter.parse(stringDate);
    }
}
