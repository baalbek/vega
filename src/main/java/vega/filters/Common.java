package vega.filters;

/**
 * Created by rcs on 03.06.15.
 *
 */

public class Common {

    public static double calcAlpha(long days) {
        return 2.0 / (days + 1.0);
    }
}
