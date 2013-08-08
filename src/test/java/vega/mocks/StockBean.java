package vega.mocks;


import java.util.Date;

import oahu.exceptions.NotImplementedException;
import oahu.financial.Stock;
import oahu.financial.StockPrice;
import org.joda.time.DateMidnight;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 11/21/12
 * Time: 10:43 PM
 */
public class StockBean implements StockPrice {

    private int id;
    private int tickerId;

    public StockBean() {
    }

    public StockBean(Date dx,
                     double opn,
                     double hi,
                     double lo,
                     double cls,
                     int volume) {
        setDx(dx);
        setOpn(opn);
        setHi(hi);
        setLo(lo);
        setCls(cls);
        setVolume(volume);

    }
    //region Time
    //private Date dx = null;
    private DateMidnight dxJoda;

    public Date getDx() {
        return dxJoda.toDate();
    }

    public void setDx(Date value) {
        dxJoda = new DateMidnight(value);
    }


    public DateMidnight getDxJoda() {
        return dxJoda;
    }

    public void setDxJoda(DateMidnight value) {
        this.dxJoda = value;
    }
    //endregion Time


    double opn;
    public double getOpn() {
        return opn;
    }
    public void setOpn(double value) {
        opn = value;
    }

    double hi;
    public double getHi() {
        return hi;
    }
    public void setHi(double value) {
        hi = value;
    }

    double lo;
    public double getLo() {
        return lo;
    }
    public void setLo(double value) {
        lo = value;
    }

    double cls;
    public double getCls() {
        return cls;
    }
    public void setCls(double value) {
        cls = value;
    }

    int volume;
    public int getVolume() {
        return volume;
    }

    @Override
    public Stock getStock() {
        throw new NotImplementedException();
    }

    public void setVolume(int value) {
        volume = value;
    }

    public double getValue() {
        /*
        if (value == null) {
            value = new SimpleDoubleProperty(getCls());
        }
        return value.get();
        */
        return cls;
    }


    public String getTicker() {
        return "TEST";
    }

}
