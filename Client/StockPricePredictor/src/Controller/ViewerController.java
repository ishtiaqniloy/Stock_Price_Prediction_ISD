package Controller;

import Model.*;
import java.util.ArrayList;

public class ViewerController {

    private String StCode;
    ArrayList<MarketHistory> marketHistoryList;
    ArrayList<MarketPrediction> marketPredList;
    ArrayList<CompanyEnlistment> comEnlistList;
    ArrayList<ShareHistory> shareHistoryList;
    ArrayList<SharePrediction> sharePredList;

    public ArrayList<CompanyEnlistment> getEnlistList() {
        return comEnlistList;
    }

    public ArrayList<CompanyEntry> generateEntryList() {
        ArrayList<CompanyEntry> cEntry = new ArrayList<>();
        return cEntry;
    }

    public Graph generateShareGraph() {
        Graph g = new Graph();
        return g;
    }

    public Graph generateMarketGraph() {
        Graph g = new Graph();
        return g;
    }

}
