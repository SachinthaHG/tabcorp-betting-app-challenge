package au.com.tabcorp.core.dao.inmemory;

import au.com.tabcorp.core.connectors.InMemoryConnector;
import au.com.tabcorp.core.memory.BetStore;
import au.com.tabcorp.core.models.Bet;

import java.util.List;

/**
 * data access object for accessing in-memory data sotre
 */
public class InMemoryBetsDao {
    private BetStore betStore;

    /* maket this class singleton */
    private InMemoryBetsDao() {
        betStore = InMemoryConnector.getInstance().getBetStore();
    }

    private static class InMemoryBetsDaoHelperClass {
        private static final InMemoryBetsDao instance = new InMemoryBetsDao();
    }

    public static InMemoryBetsDao getInstance() {
        return InMemoryBetsDaoHelperClass.instance;
    }

    /**
     * save bets into the in-memory data store
     *
     * @param betList list of bets
     * @return boolean
     */
    public boolean saveBets(List<Bet> betList) {
        return betStore.addBets(betList);
    }

    /**
     * load all the bets saved in in-memory data store
     *
     * @return list of bets
     */
    public List<Bet> getAllBets() {
        return betStore.loadAllBets();
    }
}
