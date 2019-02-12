
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class MarketPrediction implements Serializable {
    private String StCode;
    private Date date;
    private float futureIndex;
    private int futureTotalTrade;
    private int futureTotalVolume;
    private float futureTotalValue;

    public MarketPrediction(String StCode, Date date, float futureIndex, int futureTotalTrade, int futureTotalVolume, float futureTotalValue) {
        this.StCode = StCode;
        this.date = date;
        this.futureIndex = futureIndex;
        this.futureTotalTrade = futureTotalTrade;
        this.futureTotalVolume = futureTotalVolume;
        this.futureTotalValue = futureTotalValue;
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

    public float getFutureIndex() {
        return futureIndex;
    }

    public void setFutureIndex(float futureIndex) {
        this.futureIndex = futureIndex;
    }

    public int getFutureTotalTrade() {
        return futureTotalTrade;
    }

    public void setFutureTotalTrade(int futureTotalTrade) {
        this.futureTotalTrade = futureTotalTrade;
    }

    public int getFutureTotalVolume() {
        return futureTotalVolume;
    }

    public void setFutureTotalVolume(int futureTotalVolume) {
        this.futureTotalVolume = futureTotalVolume;
    }

    public float getFutureTotalValue() {
        return futureTotalValue;
    }

    public void setFutureTotalValue(float futureTotalValue) {
        this.futureTotalValue = futureTotalValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketPrediction that = (MarketPrediction) o;
        return Float.compare(that.futureIndex, futureIndex) == 0 &&
                futureTotalTrade == that.futureTotalTrade &&
                futureTotalVolume == that.futureTotalVolume &&
                Float.compare(that.futureTotalValue, futureTotalValue) == 0 &&
                Objects.equals(StCode, that.StCode) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StCode, date, futureIndex, futureTotalTrade, futureTotalVolume, futureTotalValue);
    }

    @Override
    public String toString() {
        return "MarketPrediction{" +
                "StCode='" + StCode + '\'' +
                ", date=" + date +
                ", futureIndex=" + futureIndex +
                ", futureTotalTrade=" + futureTotalTrade +
                ", futureTotalVolume=" + futureTotalVolume +
                ", futureTotalValue=" + futureTotalValue +
                '}';
    }
}
