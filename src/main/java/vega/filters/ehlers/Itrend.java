package vega.filters.ehlers;

import clojure.lang.AFn;

/**
 * Created by rcs on 03.06.15.
 *
 */
public class Itrend extends AFn {
    @Override
    public Object invoke(Object data, Object days) {
        double l = ((Long)days).longValue();
        return new Double(4.9);
    }
}
