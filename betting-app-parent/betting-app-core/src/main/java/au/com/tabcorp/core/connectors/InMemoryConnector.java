package au.com.tabcorp.core.connectors;

import au.com.tabcorp.core.memory.BetStore;

/**
 * This class is used to initialize the connections with an in-memory data store
 */
public class InMemoryConnector implements Connector {

    private BetStore betStore;

    /* make this class singleton */
    private InMemoryConnector() {
        init();
    }

    private static class InMemoryConnectorHelperClass {
        private static final InMemoryConnector instance = new InMemoryConnector();
    }

    public static InMemoryConnector getInstance() {
        return InMemoryConnectorHelperClass.instance;
    }

    /**
     * initializing the in-memory data store
     */
    public void init() {
        betStore = new BetStore();
    }

    public BetStore getBetStore() {
        return betStore;
    }
}
