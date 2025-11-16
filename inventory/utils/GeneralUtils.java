package inventory.utils;
// Utility class for any purpose (data format, logging, validation), used across the project to avoid code duplication.

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;

public class GeneralUtils {
    
    public static String formatDate(LocalDateTime date){

        try{

            DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            return date.format(formater);

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    public static void log(String message){

        System.out.println("[LOG] " + message);

    }

    public static boolean isValidPrice(BigDecimal price){

        if(price == null || price.compareTo(BigDecimal.ZERO) < 0){
            return false;
        }
        return true;

    }

    public static boolean isValidName(String name){
        if(name == null || name.trim().isEmpty()){
            return false;
        }
        return true;

    }

}