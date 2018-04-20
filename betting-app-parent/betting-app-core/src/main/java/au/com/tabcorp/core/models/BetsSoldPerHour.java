package au.com.tabcorp.core.models;

public class BetsSoldPerHour {
    int hourOfTheDay;
    int betsSold;

    public BetsSoldPerHour(int hourOfTheDay, int betsSold) {
        this.hourOfTheDay = hourOfTheDay;
        this.betsSold = betsSold;
    }

    public int getHourOfTheDay() {
        return hourOfTheDay;
    }

    public void setHourOfTheDay(int hourOfTheDay) {
        this.hourOfTheDay = hourOfTheDay;
    }

    public int getBetsSold() {
        return betsSold;
    }

    public void setBetsSold(int betsSold) {
        this.betsSold = betsSold;
    }
}
