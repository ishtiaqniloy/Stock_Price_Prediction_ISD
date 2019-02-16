
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class MarketHistory implements Serializable {
    private String StCode;
    private Date date;
    private float index;
    private int totalTrade;
    private int totalVolume;
    private float totalValue;

    public MarketHistory(String StCode, Date date, float index, int totalTrade, int totalVolume, float totalValue) {
        this.StCode = StCode;
        this.date = date;
        this.index = index;
        this.totalTrade = totalTrade;
        this.totalVolume = totalVolume;
        this.totalValue = totalValue;
    }

    public String getStCode() {
        return StCode;
    }

    public void setStCode(String StCode) {
        this.StCode = StCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getIndex() {
        return index;
    }

    public void setIndex(float index) {
        this.index = index;
    }

    public int getTotalTrade() {
        return totalTrade;
    }

    public void setTotalTrade(int totalTrade) {
        this.totalTrade = totalTrade;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketHistory that = (MarketHistory) o;
        return Float.compare(that.index, index) == 0 &&
                totalTrade == that.totalTrade &&
                totalVolume == that.totalVolume &&
                Float.compare(that.totalValue, totalValue) == 0 &&
                Objects.equals(StCode, that.StCode) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StCode, date, index, totalTrade, totalVolume, totalValue);
    }

    @Override
    public String toString() {
        return "MarketHistory{" +
                "StCode='" + StCode + '\'' +
                ", date=" + date +
                ", index=" + index +
                ", totalTrade=" + totalTrade +
                ", totalVolume=" + totalVolume +
                ", totalValue=" + totalValue +
                '}';
    }
}
