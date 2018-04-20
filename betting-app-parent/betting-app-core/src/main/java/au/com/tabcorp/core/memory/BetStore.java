package au.com.tabcorp.core.memory;

import au.com.tabcorp.core.models.Bet;

import java.util.ArrayList;
import java.util.List;

public class BetStore {
    private List<Bet> betList;

    public BetStore() {
        this.betList = new ArrayList<Bet>();
    }

    public boolean addBets(List<Bet> betList) {
        if (betList != null && validate(betList)) {
            return this.betList.addAll(betList);
        } else {
            return false;
        }
    }

    public List<Bet> loadAllBets() {
        return betList;
    }

    private boolean validate(List<Bet> bets) {
        for (Bet newBet : bets) {
            for (Bet existingBet : betList) {
                if (newBet.getPropNumber() == existingBet.getPropNumber()) {
                    return false;
                }
            }
        }
        return true;
    }
}