package au.com.tabcorp.core.models;

public class BetsSoldPerBetType {
    private BetType betType;
    private int betsSold;

    public BetsSoldPerBetType(BetType betType, int betsSold) {
        this.betType = betType;
        this.betsSold = betsSold;
    }

    public BetType getBetType() {
        return betType;
    }

    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    public int getBetsSold() {
        return betsSold;
    }

    public void setBetsSold(int betsSold) {
        this.betsSold = betsSold;
    }
}
