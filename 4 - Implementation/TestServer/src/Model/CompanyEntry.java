
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class CompanyEntry implements Serializable {
    private String enlistID;
    private String TradeCode;
    private String StCode;
    private String companyName;
    private int LastTradingPrice;
    public ArrayList<ShareHistory> historyList;
    public ArrayList<SharePrediction> predictionList;
    public Graph shareGraph;

    public CompanyEntry(String enlistID, String TradeCode, String StCode, String companyName, int LastTradingPrice) {
        this.enlistID = enlistID;
        this.TradeCode = TradeCode;
        this.StCode = StCode;
        this.companyName = companyName;
        this.LastTradingPrice = LastTradingPrice;

        historyList = new ArrayList<ShareHistory>();
        predictionList = new ArrayList<SharePrediction>();

        shareGraph = new Graph();

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

    public void setTradeCode(String TradeCode) {
        this.TradeCode = TradeCode;
    }

    public String getStCode() {
        return StCode;
    }

    public void setStCode(String StCode) {
        this.StCode = StCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getLastTradingPrice() {
        return LastTradingPrice;
    }

    public void setLastTradingPrice(int LastTradingPrice) {
        this.LastTradingPrice = LastTradingPrice;
    }

    public ArrayList<ShareHistory> getShareHistoryList() {
        return historyList;
    }

    public void setShareHistoryList(ArrayList<ShareHistory> historyList) {
        this.historyList = historyList;
    }

    public ArrayList<SharePrediction> getSharePredList() {
        return predictionList;
    }

    public void setSharePredList(ArrayList<SharePrediction> predictionList) {
        this.predictionList = predictionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntry that = (CompanyEntry) o;
        return enlistID == that.enlistID &&
                LastTradingPrice == that.LastTradingPrice &&
                Objects.equals(TradeCode, that.TradeCode) &&
                Objects.equals(StCode, that.StCode) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(historyList, that.historyList) &&
                Objects.equals(predictionList, that.predictionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enlistID, TradeCode, StCode, companyName, LastTradingPrice, historyList, predictionList);
    }

    @Override
    public String toString() {
        return "CompanyEntry{" +
                "enlistID=" + enlistID +
                ", TradeCode='" + TradeCode + '\'' +
                ", StCode='" + StCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", LastTradingPrice=" + LastTradingPrice +
                ", historyList=" + historyList +
                ", predictionList=" + predictionList +
                '}';
    }
}
