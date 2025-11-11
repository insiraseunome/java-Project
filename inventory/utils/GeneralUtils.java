package inventory.utils;
// Utility class for any purpose (data format, logging, validation), used across the project to avoid code duplication.

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.math.BigDecimal;

public class GeneralUtils {
    public static String formatDate(LocalDateTime date){
        try{
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(String message){
        System.out.println();
    }

    public static boolean isValidPrice(BigDecimal price){
        return true;
    }

    public boolean isValidName(String name){
        return true;
    }
}
