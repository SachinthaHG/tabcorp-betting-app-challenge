package au.com.tabcorp.core.models;

public class InvestmentPerCustomer {
    private int customerId;
    private double totalInvestment;

    public InvestmentPerCustomer(int customerId, double totalInvestment) {
        this.customerId = customerId;
        this.totalInvestment = totalInvestment;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }
}
