package vega.filters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rcs on 03.06.15.
 *
 */

public class Common {

    public static double calcAlpha(long days) {
        return 2.0 / (days + 1.0);
    }

    public static List<Double> calcSmooth(List<Double> data) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < 4; ++i) {
            result.add(data.get(i));
        }

        for (int i = 4; i < data.size() ; ++i) {
            double a = data.get(i-3);
            double b = 2.0 * data.get(i-2);
            double c = 2.0 * data.get(i-1);
            double d = data.get(i);

            double item = (a + b + c + d) / 6.0;

            result.add(item);
        }
        return result;
    }
}
