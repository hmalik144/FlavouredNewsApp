package com.example.h_mal.flavourednewsapp.utils;

public class DateUtils {

    public static String dateStringOnly(String date){
        try{
            return date.split("T")[0];
        }catch (Exception e){
            return date;
        }
    }
}
