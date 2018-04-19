package au.com.tabcorp.core.services;

import au.com.tabcorp.core.dao.inmemory.InMemoryBetsDao;
import au.com.tabcorp.core.models.Bet;

import java.util.List;

public class BetStoreService {
    private InMemoryBetsDao inMemoryBetsDao;

    public BetStoreService() {
        this.inMemoryBetsDao = InMemoryBetsDao.getInstance();
    }

    public boolean saveBets(List<Bet> betList) {
        return inMemoryBetsDao.saveBets(betList);
    }
}
