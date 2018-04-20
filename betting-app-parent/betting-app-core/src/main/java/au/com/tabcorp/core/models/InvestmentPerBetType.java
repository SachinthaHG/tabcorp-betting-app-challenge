package au.com.tabcorp.core.models;

public class InvestmentPerBetType {
    private BetType betType;
    private double totalInvestment;

    public InvestmentPerBetType(BetType betType, double totalInvestment) {
        this.betType = betType;
        this.totalInvestment = totalInvestment;
    }

    public BetType getBetType() {
        return betType;
    }

    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    public double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }
}
