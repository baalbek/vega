package vega.filters.ehlers;

import clojure.lang.AFn;

import java.util.ArrayList;
import java.util.List;


public class RoofingFilter extends AFn {

    private int highPassPeriod = 48;

    private SuperSmoother smooth;

    public RoofingFilter() {
        smooth = new SuperSmoother(10);
    }


    public RoofingFilter(int highPassPeriod,
                         int lowPassPeriod) {
        this.highPassPeriod = highPassPeriod;
        smooth = new SuperSmoother(lowPassPeriod);
    }

    /*
    {
    Roofing filter
    © 2013 John F. Ehlers
    }

    Vars: alpha1(0), HP(0), a1(0), b1(0), c1(0), c2(0), c3(0), Filt(0), Filt2(0);

    //Highpass filter cyclic components whose periods are shorter than 48 bars
    alpha1 = (Cosine(.707*360 / 48) + Sine (.707*360 / 48) - 1) / Cosine(.707*360 / 48);
    HP =
        (1 - alpha1 / 2)*(1 - alpha1 / 2) * (Close - 2*Close[1] + Close[2]) +
        2*(1 - alpha1) * HP[1] -
        (1 - alpha1)*(1 - alpha1)*HP[2];

    //Smooth with a Super Smoother Filter
    a1 = expvalue(-1.414*3.14159 / 10);
    b1 = 2*a1*Cosine(1.414*180 / 10);
    c2 = b1;
    c3 = -a1*a1;
    c1 = 1 - c2 - c3;
    Filt = c1*(HP + HP[1]) / 2 + c2*Filt[1] + c3*Filt[2];
    Plot1(Filt);
    Plot2(0);
    */


    @Override
    @SuppressWarnings("unchecked")
    public Object invoke(Object data) {

        double f1 = 0.707 * Math.PI / highPassPeriod;

        double alpha1 = (Math.cos(f1) + Math.sin(f1) - 1) / Math.cos(f1);

        double alpha2 = 1.0 - alpha1;

        double alpha22 = alpha2 * alpha2;

        double alpha3 = alpha2 / 2.0;

        double alpha33 = alpha3 * alpha3;


        List<Double> datax = (List<Double>)data;

        List<Double> result = new ArrayList<>();

        for (int i = 0; i < 2; ++i) {
            result.add(datax.get(i));
        }

        int sz = datax.size();

        for (int i = 2; i < sz; ++i) {

            double close = datax.get(i);

            double close_1 = datax.get(i-1);

            double close_2 = datax.get(i-2);

            double hp_1 = result.get(i-1);

            double hp_2 = result.get(i-2);

            double res_1 = alpha33 * (close- (2.0*close_1) + close_2);

            double res_2 = 2.0 * alpha2 * hp_1;

            double res_3 = alpha22 * hp_2;

            result.add(res_1 - res_2 - res_3);
        }

        return smooth.invoke(result);
    }
}
