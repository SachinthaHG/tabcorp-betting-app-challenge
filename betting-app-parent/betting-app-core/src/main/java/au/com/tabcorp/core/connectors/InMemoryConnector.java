package au.com.tabcorp.core.connectors;

import au.com.tabcorp.core.memory.BetStore;


public class InMemoryConnector implements Connector {

    private BetStore betStore;

    private InMemoryConnector() {
        init();
    }

    private static class InMemoryConnectorHelperClass {
        private static final InMemoryConnector instance = new InMemoryConnector();
    }

    public static InMemoryConnector getInstance() {
        return InMemoryConnectorHelperClass.instance;
    }

    public void init() {
        betStore = new BetStore();
    }

    public BetStore getBetStore() {
        return betStore;
    }
}
