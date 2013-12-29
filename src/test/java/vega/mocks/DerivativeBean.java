package vega.mocks;

import oahu.exceptions.NotImplementedException;
import oahu.financial.Derivative;
import oahu.financial.OptionCalculator;
import oahu.financial.Stock;
import oahu.financial.StockPrice;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 5/1/13
 * Time: 9:35 AM
 */
public class DerivativeBean implements Derivative {
    private final OptionCalculator calculator;

    StockPrice parent;

    public DerivativeBean(String ticker,
                                    int opType,
                                    double x,
                                    double buy,
                                    double sell,
                                    double days,
                                    StockPrice parent,
                                    OptionCalculator calculator) {
        this.ticker = ticker;
        this.opType = opType;
        this.x = x;
        this.buy = buy;
        this.sell = sell;
        this.days = days;
        this.parent = parent;
        this.calculator = calculator;
    }

    public StockPrice getParent() {
        return parent;
    }

    //--------------------------------------------------
    //------------- Expiry
    //--------------------------------------------------
    private Date expiry;
    public Date getExpiry() {
        return expiry;
    }

    @Override
    public String getSeries() {
        throw new NotImplementedException();
    }

    @Override
    public double getWatermark() {
        throw new NotImplementedException();
    }

    @Override
    public void setWatermark(double v) {
        throw new NotImplementedException();
    }

    @Override
    public void updateWatermark(double v) {
        throw new NotImplementedException();
    }

    @Override
    public double getDiffFromBought() {
        throw new NotImplementedException();
    }

    @Override
    public void setDiffFromBought(double v) {
        throw new NotImplementedException();
    }

    public void setExpiry(Date value) {
        expiry = value;
    }

    //--------------------------------------------------
    //------------- OpType
    //--------------------------------------------------
    private int opType;
    @Override
    public int getOpType() {
        return opType;
    }

    @Override
    public String getOpTypeStr() {
        throw new NotImplementedException();
    }

    public void setOpType(int value) {
        opType = value;
    }

    //--------------------------------------------------
    //------------- X
    //--------------------------------------------------
    double x;
    @Override
    public double getX() {
        return x;
    }

    public void setX(double value) {
        x = value;
    }

    //--------------------------------------------------
    //------------- Buy
    //--------------------------------------------------
    double buy;
    @Override
    public double getBuy() {
        return buy;
    }
    public void setBuy(double value) {
        buy = value;
    }

    //--------------------------------------------------
    //------------- Sell
    //--------------------------------------------------
    double sell;
    @Override
    public double getSell() {
        return sell;
    }
    public void setSell(double value) {
        sell = value;
    }

    //--------------------------------------------------
    //------------- Ticker
    //--------------------------------------------------
    String ticker;
    public String getTicker() {
        return ticker;
    }

    @Override
    public int getOid() {
        throw new NotImplementedException();
    }

    public void setTicker(String value) {
        ticker = value;
    }

    //--------------------------------------------------
    //------------- days
    //--------------------------------------------------
    double days;
    public double getDays() {
        return days;
    }
    public void setDays(double value) {
        days = value;
    }
    public double getYears() {
        return getDays() / 365.0;
    }
    //--------------------------------------------------
    //------------- ivBuy
    //--------------------------------------------------

    public double getIvBuy() {
        return calculator.iv(this,Derivative.BUY);
    }
    //--------------------------------------------------
    //------------- ivSell
    //--------------------------------------------------
    public double getIvSell() {
        return calculator.iv(this,Derivative.SELL);
    }
    //--------------------------------------------------
    //------------- Delta
    //--------------------------------------------------
    public double getDelta() {
        return calculator.delta(this);
    }

    //--------------------------------------------------
    //------------- Break-even
    //--------------------------------------------------
    public double getBreakeven() {
        return calculator.breakEven(this);
    }

    //--------------------------------------------------
    //------------- Spread
    //--------------------------------------------------
    public double getSpread() {
        return calculator.spread(this);
    }

}