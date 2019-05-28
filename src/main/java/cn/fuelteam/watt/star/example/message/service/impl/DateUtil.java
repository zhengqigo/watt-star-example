package cn.fuelteam.watt.star.example.message.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    
    public static final String format(Date date) {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    public static final long work(Date date) {
        return date.getTime() / 1000 / 60 / 60 / 24 / 7;
    }
}