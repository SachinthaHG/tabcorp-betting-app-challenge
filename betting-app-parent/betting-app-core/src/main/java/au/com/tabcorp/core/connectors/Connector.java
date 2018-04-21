package au.com.tabcorp.core.connectors;

/**
 * implement this interface to make connection with any data store (ex: mysql, in-memory data store, etc.)
 */
public interface Connector {
    /**
     * use this method to initialize the connection
     */
    public void init();
}
