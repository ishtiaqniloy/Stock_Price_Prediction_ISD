
package Model;

import java.io.Serializable;
import java.util.Objects;

public class StockExchange implements Serializable {
    private String StCode;
    private String name;
    private float lastUpdatedIndex;

    public StockExchange(String StCode, String name, float lastUpdatedIndex) {
        this.StCode = StCode;
        this.name = name;
        this.lastUpdatedIndex = lastUpdatedIndex;
    }

    public String getStCode() {
        return StCode;
    }

    public void setStCode(String StCode) {
        this.StCode = StCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLastUpdatedIndex() {
        return lastUpdatedIndex;
    }

    public void setLastUpdatedIndex(float lastUpdatedIndex) {
        this.lastUpdatedIndex = lastUpdatedIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockExchange that = (StockExchange) o;
        return Float.compare(that.lastUpdatedIndex, lastUpdatedIndex) == 0 &&
                Objects.equals(StCode, that.StCode) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StCode, name, lastUpdatedIndex);
    }

    @Override
    public String toString() {
        return "StockExchange{" +
                "StCode='" + StCode + '\'' +
                ", name='" + name + '\'' +
                ", lastUpdatedIndex=" + lastUpdatedIndex +
                '}';
    }
}
