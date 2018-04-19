package au.com.tabcorp.core.dao.inmemory;

import au.com.tabcorp.core.connectors.InMemoryConnector;
import au.com.tabcorp.core.memory.BetStore;
import au.com.tabcorp.core.models.Bet;

import java.util.List;

public class InMemoryBetsDao {
    private BetStore betStore;

    private InMemoryBetsDao() {
        betStore = InMemoryConnector.getInstance().getBetStore();
    }

    private static class InMemoryBetsDaoHelperClass {
        private static final InMemoryBetsDao instance = new InMemoryBetsDao();
    }

    public static InMemoryBetsDao getInstance() {
        return InMemoryBetsDaoHelperClass.instance;
    }

    public boolean saveBets(List<Bet> betList) {
        return betStore.addBets(betList);
    }

    public List<Bet> getAllBets(){
        return betStore.loadAllBets();
    }
}
