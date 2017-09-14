package vega.filters.ehlers;

import clojure.lang.AFn;

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

        double twoPoleFactor = 1.414;

        double a1 = Math.exp(-twoPoleFactor * 3.14159 / cyclePeriodx);

        double rads180 = Math.toRadians(180);

        double b1 = 2 * a1 * Math.cos(twoPoleFactor * rads180 / cyclePeriodx);

        double c3 = -a1*a1;

        double c1 = 1 - b1 - c3;

        result.add(datax.get(0));

        result.add(datax.get(1));

        for (int i = 1; i<datax.size(); ++i) {
            double close = datax.get(i);
            double close_1 = datax.get(i-1);
        }

        return result;
    }
}
/*
    var Smooth(var *Data,int Period)
    {
        var f = (1.414*PI) / Period;
        var a = exp(-f);
        var c2 = 2*a*cos(f);
        var c3 = -a*a;
        var c1 = 1 - c2 - c3;
        var *S = series(Data[0]);
        return S[0] = c1*(Data[0]+Data[1])*0.5 + c2*S[1] + c3*S[2];
    }
*/

/*
Vars: a1(0), b1(0), c1(0), c2(0), c3(0), Filt(0);
a1 = expvalue(-1.414*3.14159 / 10);
b1 = 2*a1*Cosine(1.414*180 / 10);
c2 = b1;
c3 = -a1*a1;
c1 = 1 - c2 - c3;
Filt = c1*(Close + Close[1]) / 2 + c2*Filt[1] + c3*Filt[2]; 
*/

