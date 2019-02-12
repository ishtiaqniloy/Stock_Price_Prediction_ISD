
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CompanyEnlistment implements Serializable {
    private String  enlistID;
    private String TradeCode;
    private String StCode;
    private int LastTradingPrice;
    private Date enlistDate;
    private int initialPrice;

    public CompanyEnlistment(String enlistID, String tradeCode, String stCode, int lastTradingPrice, Date enlistDate, int initialPrice) {
        this.enlistID = enlistID;
        TradeCode = tradeCode;
        StCode = stCode;
        LastTradingPrice = lastTradingPrice;
        this.enlistDate = enlistDate;
        this.initialPrice = initialPrice;
    }

    public String getEnlistID() {
        return enlistID;
    }

    public void setEnlistID(String enlistID) {
        this.enlistID = enlistID;
    }

    public String getTradeCode() {
        return TradeCode;
    }

    public void setTradeCode(String tradeCode) {
        TradeCode = tradeCode;
    }

    public String getStCode() {
        return StCode;
    }

    public void setStCode(String stCode) {
        StCode = stCode;
    }

    public int getLastTradingPrice() {
        return LastTradingPrice;
    }

    public void setLastTradingPrice(int lastTradingPrice) {
        LastTradingPrice = lastTradingPrice;
    }

    public Date getEnlistDate() {
        return enlistDate;
    }

    public void setEnlistDate(Date enlistDate) {
        this.enlistDate = enlistDate;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice = initialPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEnlistment that = (CompanyEnlistment) o;
        return LastTradingPrice == that.LastTradingPrice &&
                initialPrice == that.initialPrice &&
                Objects.equals(enlistID, that.enlistID) &&
                Objects.equals(TradeCode, that.TradeCode) &&
                Objects.equals(StCode, that.StCode) &&
                Objects.equals(enlistDate, that.enlistDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enlistID, TradeCode, StCode, LastTradingPrice, enlistDate, initialPrice);
    }

    @Override
    public String toString() {
        return "CompanyEnlistment{" +
                "enlistID='" + enlistID + '\'' +
                ", TradeCode='" + TradeCode + '\'' +
                ", StCode='" + StCode + '\'' +
                ", LastTradingPrice=" + LastTradingPrice +
                ", enlistDate=" + enlistDate +
                ", initialPrice=" + initialPrice +
                '}';
    }
}
