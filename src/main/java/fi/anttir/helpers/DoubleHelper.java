package fi.anttir.helpers;

/**
 * Created by anttir on 23.10.2015.
 */
public class DoubleHelper {

    public enum ComparisonEqualityType{
        EQUAL,

        GREATER_THAN_OR_EQUAL,
        GREATER_THAN,
        LESS_THAN_OR_EQUAL,
        LESS_THAN
    }

    public enum TwinComparisonEqualityType{
        /**
         * Greater Than or Equal & Less Than or Equal : a <= value <= b
         */
        GREATER_THAN_OR_EQUAL_AND_LESS_THAN_OR_EQUAL,
        /**
         * Greater Than or Equal & Less Than : a <= value < b
         */
        GREATER_THAN_OR_EQUAL_AND_LESS_THAN,
        /**
         * Greater Than & Less Than : a < value < b
         */
        GREATER_THAN_AND_LESS_THAN,
        /**
         * Greater Than & Less or Equal : a < value <= b
         */
        GREATER_THAN_AND_LESS_THAN_OR_EQUAL // a < value <= b
    }

    /**
     * Check if value is a <= value <= b
     * @param value
     * @param a
     * @param b
     * @return
     */
    public static boolean between(double value, double a, double b){
        return between(value, a, b, TwinComparisonEqualityType.GREATER_THAN_OR_EQUAL_AND_LESS_THAN_OR_EQUAL);
    }

    /**
     * Check if value is a <= value <= b
     * @param value
     * @param a
     * @param b
     * @return
     */
    public static boolean between(double value, double a, double b, TwinComparisonEqualityType f){
        /*
        if(f.equals(TwinComparisonEqualityType.GREATER_THAN_AND_LESS_THAN) ||f.equals(TwinComparisonEqualityType.GREATER_THAN_OR_EQUAL_AND_LESS_THAN_OR_EQUAL) ){
            if()
        }*/


        if(a<=value && value<=b)
            return true;

        return false;
    }

    private void f(){
        between(5.0, 1.0, 7.0, TwinComparisonEqualityType.GREATER_THAN_AND_LESS_THAN_OR_EQUAL);

    }

    /*

    GELE a <= value <= b
    GELT a <= value < b
    GTLT a < value < b
    GTLE a < value <= b

     */

    //public boolean compare(double value, double other)
}
