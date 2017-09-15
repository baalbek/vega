package vega.filters.ehlers;

import clojure.lang.AFn;
import vega.filters.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rcs on 03.06.15.
 *
 */
public class Itrend extends AFn {

    private double alpha;

    public Itrend(long days) {
        alpha = Common.calcAlpha(days);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object invoke(Object data) {
        List<Double> datax = (List<Double>)data;
        int count = datax.size();

        List<Double> result = new ArrayList<Double>();

        for (int i=0; i<8; ++i) {
            double a = datax.get(i);
            double b = 2 * datax.get(i+1);
            double c = datax.get(i+2);

            result.add(0.25 *(a+b+c));
        }

        double aa = alpha * alpha;
        double f1 = alpha - (0.25 * aa);
        double f2 = aa * 0.5;
        double f3 = alpha - (0.75 * aa);
        double f4 = 1.0  - alpha;
        double f5 = f4 * f4;

        double v1 = result.get(7);
        double v2 = result.get(6);

        for (int i=8; i<count; ++i) {
            double a = datax.get(i-2);
            double b = datax.get(i-1);
            double c = datax.get(i);

            double posval = (f1*c) + (f2*b) + (2.0*f4*v1);

            double negval = (f3*a) + (f5*v2);

            double resval =  posval - negval;

            result.add(resval);
            v2 = v1;
            v1 = resval;
        }
        return result;
    }
}
