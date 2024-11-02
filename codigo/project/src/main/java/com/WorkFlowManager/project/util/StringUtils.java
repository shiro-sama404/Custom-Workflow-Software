package com.WorkFlowManager.project.util;

public class StringUtils {

    private StringUtils(){};

    public static boolean isNullorEmpty(String str){
        return str == null || str.trim().isEmpty();
    }

    public static String capitalizeFirstLetter(String str){
        if(isNullorEmpty(str)){return str;}
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
