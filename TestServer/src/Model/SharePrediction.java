
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SharePrediction implements Serializable {
    private String enlistID;
    private Date date;
    private int futureClosingPrice;

    public SharePrediction(String enlistID, Date date, int futureClosingPrice) {
        this.enlistID = enlistID;
        this.date = date;
        this.futureClosingPrice = futureClosingPrice;
    }

    public String getEnlistID() {
        return enlistID;
    }

    public void setEnlistID(String enlistID) {
        this.enlistID = enlistID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFutureClosingPrice() {
        return futureClosingPrice;
    }

    public void setFutureClosingPrice(int futureClosingPrice) {
        this.futureClosingPrice = futureClosingPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharePrediction that = (SharePrediction) o;
        return futureClosingPrice == that.futureClosingPrice &&
                Objects.equals(enlistID, that.enlistID) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enlistID, date, futureClosingPrice);
    }

    @Override
    public String toString() {
        return "SharePrediction{" +
                "enlistID='" + enlistID + '\'' +
                ", date=" + date +
                ", futureClosingPrice=" + futureClosingPrice +
                '}';
    }
}
