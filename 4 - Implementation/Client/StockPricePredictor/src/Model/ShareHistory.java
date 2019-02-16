
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ShareHistory implements Serializable {
    private String enlistID;
    private int closingPrice;
    private Date date;

    public ShareHistory(String enlistID, int closingPrice, Date date) {
        this.enlistID = enlistID;
        this.closingPrice = closingPrice;
        this.date = date;
    }

    public String getEnlistID() {
        return enlistID;
    }

    public void setEnlistID(String enlistID) {
        this.enlistID = enlistID;
    }

    public int getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(int closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShareHistory that = (ShareHistory) o;
        return closingPrice == that.closingPrice &&
                Objects.equals(enlistID, that.enlistID) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enlistID, closingPrice, date);
    }

    @Override
    public String toString() {
        return "ShareHistory{" +
                "enlistID='" + enlistID + '\'' +
                ", closingPrice=" + closingPrice +
                ", date=" + date +
                '}';
    }
}
