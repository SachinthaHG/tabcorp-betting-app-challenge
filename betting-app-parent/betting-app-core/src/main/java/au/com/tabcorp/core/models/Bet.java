package au.com.tabcorp.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bet {
    @JsonProperty("DateTime")
    private String dateTime;

    @JsonProperty("BetType")
    private String betType;

    @JsonProperty("PropNumber")
    private int propNumber;

    @JsonProperty("CustomerID")
    private int customerId;

    @JsonProperty("Investment")
    private double investment;

    public Bet() {

    }

    public Bet(String dateTime, String betType, int propNumber, int customerId, double investment) {
        this.dateTime = dateTime;
        this.betType = betType;
        this.propNumber = propNumber;
        this.customerId = customerId;
        this.investment = investment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public int getPropNumber() {
        return propNumber;
    }

    public void setPropNumber(int propNumber) {
        this.propNumber = propNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getInvestment() {
        return investment;
    }

    public void setInvestment(double investment) {
        this.investment = investment;
    }
}
