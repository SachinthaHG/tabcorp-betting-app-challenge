package au.com.tabcorp.core.memory;

import au.com.tabcorp.core.models.Bet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BetStoreTest {
    private BetStore betStore = new BetStore();

    @Test
    public void saveBets_EmptyList_False() {
        List<Bet> betList = new ArrayList<Bet>();
        assertFalse(betStore.addBets(betList));
    }

    @Test
    public void saveBets_NonEmptyList_True() {
        List<Bet> betList = new ArrayList<Bet>();
        betList.add(new Bet("2018-01-01 12:56", "WIN", 103333, 1081, 500.50));
        betList.add(new Bet("2018-01-01 14:56", "TRIFECTA", 104567, 1080, 100.00));

        assertTrue(betStore.addBets(betList));
    }

    @Test
    public void saveBets_NullList_False() {
        assertFalse(betStore.addBets(null));
    }
}
