package br.com.wagner.crud.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static Date stringToDate(String data){
        try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = formatter.parse(data);
			return date;
		} catch (Exception e1) {
			return null;
		}
    }

    public static Date dataAtualZeroHora(){
        Calendar now = Calendar.getInstance();
			now.set(Calendar.HOUR, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
            now.set(Calendar.MILLISECOND, 0);
            now.set(Calendar.HOUR_OF_DAY, 0);
        
        return now.getTime();
    }
}
