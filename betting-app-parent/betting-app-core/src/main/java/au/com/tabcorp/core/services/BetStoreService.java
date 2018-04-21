package au.com.tabcorp.core.services;

import au.com.tabcorp.core.dao.inmemory.InMemoryBetsDao;
import au.com.tabcorp.core.models.Bet;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * This class is a service class to store bets in in-memory data store
 */
public class BetStoreService {
    private InMemoryBetsDao inMemoryBetsDao;

    public BetStoreService() {
        this.inMemoryBetsDao = InMemoryBetsDao.getInstance();
    }

    /**
     * calls the InMemoryBetsDao to save bets into in-memory data store
     *
     * @param betList list of bets to be saved
     * @return boolean
     */
    public boolean saveBets(List<Bet> betList) {
        if (inMemoryBetsDao.saveBets(betList)) {
            Logger.getLogger(BetStoreService.class).info("Bests saved successfully");
            return true;
        } else {
            Logger.getLogger(BetStoreService.class).info("Best list is null, empty or has duplicate PropNumbers");
            return false;
        }
    }
}
