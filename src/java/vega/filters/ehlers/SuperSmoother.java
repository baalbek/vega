package vega.filters.ehlers;

import clojure.lang.AFn;
import vega.filters.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rcs on 14.09.17.
 *
 */
public class SuperSmoother extends AFn {
    @Override
    @SuppressWarnings("unchecked")
    public Object invoke(Object data, Object cyclePeriod) {
        long cyclePeriodx = (Long)cyclePeriod;
        List<Double> datax = (List<Double>)data;

        List<Double> result = new ArrayList<Double>();
     
        double a1 = Math.exp(-1.414*3.14159 / cyclePeriodx);

        double rads = Math.toRadians(180);

        double b1 = 2 * a1 * Math.cos(1.414*rads / cyclePeriodx);

        double c3 = -a1*a1;

        double c1 = 1 - b1 - c3;


        return result;
    }
}
/*
Vars: a1(0), b1(0), c1(0), c2(0), c3(0), Filt(0);
a1 = expvalue(-1.414*3.14159 / 10);
b1 = 2*a1*Cosine(1.414*180 / 10);
c2 = b1;
c3 = -a1*a1;
c1 = 1 - c2 - c3;
Filt = c1*(Close + Close[1]) / 2 + c2*Filt[1] + c3*Filt[2]; 
*/

