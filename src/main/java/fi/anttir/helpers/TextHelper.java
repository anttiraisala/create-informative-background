package fi.anttir.helpers;

/**
 * Created by anttir on 9.1.2016.
 */
public class TextHelper {
    public static String cleanString(String s){
        String value = s.replaceAll("[\"]", "");
        value = value.replaceAll("[']", "");
        value = value.replaceAll("[\\s]+", " ");
        value = value.trim();

        return value;
    }
}
