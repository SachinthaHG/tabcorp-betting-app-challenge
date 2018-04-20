package au.com.tabcorp.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Bet {
    @JsonProperty("DateTime")
    private String dateTime;

    @JsonProperty("BetType")
    private BetType betType;

    @JsonProperty("PropNumber")
    private int propNumber;

    @JsonProperty("CustomerID")
    private int customerId;

    @JsonProperty("Investment")
    private double investment;

    public Bet() {

    }

    public Bet(String dateTime, BetType betType, int propNumber, int customerId, double investment) {
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

    public BetType getBetType() {
        return betType;
    }

    public void setBetType(BetType betType) {
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

    public int getHourOfTheDay() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTime);
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.HOUR_OF_DAY);
        } catch (ParseException e) {
            Logger.getLogger(Bet.class).error("data parse exception");
            return -1;
        }
    }
}
