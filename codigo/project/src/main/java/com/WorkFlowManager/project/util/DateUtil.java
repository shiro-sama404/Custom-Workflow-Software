package com.WorkFlowManager.project.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    static String[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public static String FormatLocalDateToDatabaseStyle(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(localDateTime);
    }

    public static String monthNumberToString(int month){
        
        if(month >= 0 && month <= 11){
            return months[month];
        }
        
        return LocalDate.now().getMonth().toString();
    }

    private DateUtil(){};
}
