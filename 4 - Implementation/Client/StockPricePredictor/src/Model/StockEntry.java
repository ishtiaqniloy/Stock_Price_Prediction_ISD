
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class StockEntry implements Serializable {
    private String StCode;
    private ArrayList<MarketHistory> historyList;
    private ArrayList<MarketPrediction> predictionList;

    public StockEntry(String StCode) {
        this.StCode = StCode;
    }

    public String getStCode() {
        return StCode;
    }

    public void setStCode(String StCode) {
        this.StCode = StCode;
    }

    public ArrayList<MarketHistory> getMarketHistoryList() {
        return historyList;
    }

    public void setMarketHistoryList(ArrayList<MarketHistory> historyList) {
        this.historyList = historyList;
    }

    public ArrayList<MarketPrediction> getMarketPredList() {
        return predictionList;
    }

    public void setMarketPredList(ArrayList<MarketPrediction> predictionList) {
        this.predictionList = predictionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockEntry that = (StockEntry) o;
        return Objects.equals(StCode, that.StCode) &&
                Objects.equals(historyList, that.historyList) &&
                Objects.equals(predictionList, that.predictionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StCode, historyList, predictionList);
    }

    @Override
    public String toString() {
        return "StockEntry{" +
                "StCode='" + StCode + '\'' +
                ", historyList=" + historyList +
                ", predictionList=" + predictionList +
                '}';
    }
}
