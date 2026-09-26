package fi.anttir.random;

/**
 * Created by anttir on 14.11.2015.
 */
public class RandomHelpers {
    public static int getRandomBetween(int min, int max){
        return (int)getRandomBetween((long)min, (long)max);
    }
    public static long getRandomBetween(long min, long max){
        long value=min+(long)(Math.random()*(max-min)+0.5);
        return value;
    }

    public static double getRandomBetween(double min, double max){
        double value=min+(Math.random()*(max-min));
        return value;
    }
}
