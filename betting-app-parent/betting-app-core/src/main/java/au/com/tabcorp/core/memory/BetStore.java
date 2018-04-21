package au.com.tabcorp.core.memory;

import au.com.tabcorp.core.models.Bet;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used as an in-memory data store to bet data
 */
public class BetStore {
    private List<Bet> betList;

    public BetStore() {
        this.betList = new ArrayList<>();
    }

    /**
     * validate and save a bet list
     *
     * @param betList list of bets to be saved
     * @return boolean
     */
    public boolean addBets(List<Bet> betList) {
        /* validate and check whether  */
        if (validate(betList)) {
            return this.betList.addAll(betList);
        } else {
            return false;
        }
    }

    /**
     * load all the saved bets
     *
     * @return list of bets
     */
    public List<Bet> loadAllBets() {
        return betList;
    }

    /**
     * check whether the bet list is null or contains bets with same propNumber
     *
     * @param bets list of bets to be validated
     * @return boolean
     */
    private boolean validate(List<Bet> bets) {
        if (bets == null) {
            return false;
        } else {
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
}