package Model;

import java.io.Serializable;
import java.util.Objects;

public class Company implements Serializable {
    private String TradeCode;
    private String companyName;
    private float authorizedCapital;
    private float paidUpCapital;
    private float outstandingShareNum;
    private String sector;

    public Company(String tradeCode, String companyName, float authorizedCapital, float paidUpCapital, float outstandingShareNum, String sector) {
        TradeCode = tradeCode;
        this.companyName = companyName;
        this.authorizedCapital = authorizedCapital;
        this.paidUpCapital = paidUpCapital;
        this.outstandingShareNum = outstandingShareNum;
        this.sector = sector;
    }

    public String getTradeCode() {
        return TradeCode;
    }

    public void setTradeCode(String tradeCode) {
        TradeCode = tradeCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public float getAuthorizedCapital() {
        return authorizedCapital;
    }

    public void setAuthorizedCapital(float authorizedCapital) {
        this.authorizedCapital = authorizedCapital;
    }

    public float getPaidUpCapital() {
        return paidUpCapital;
    }

    public void setPaidUpCapital(float paidUpCapital) {
        this.paidUpCapital = paidUpCapital;
    }

    public float getOutstandingShareNum() {
        return outstandingShareNum;
    }

    public void setOutstandingShareNum(float outstandingShareNum) {
        this.outstandingShareNum = outstandingShareNum;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Company{" +
                "TradeCode='" + TradeCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", authorizedCapital=" + authorizedCapital +
                ", paidUpCapital=" + paidUpCapital +
                ", outstandingShareNum=" + outstandingShareNum +
                ", sector='" + sector + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Float.compare(company.authorizedCapital, authorizedCapital) == 0 &&
                Float.compare(company.paidUpCapital, paidUpCapital) == 0 &&
                Float.compare(company.outstandingShareNum, outstandingShareNum) == 0 &&
                Objects.equals(TradeCode, company.TradeCode) &&
                Objects.equals(companyName, company.companyName) &&
                Objects.equals(sector, company.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TradeCode, companyName, authorizedCapital, paidUpCapital, outstandingShareNum, sector);
    }
}
