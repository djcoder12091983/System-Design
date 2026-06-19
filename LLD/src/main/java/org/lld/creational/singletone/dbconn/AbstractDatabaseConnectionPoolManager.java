package org.lld.creational.singletone.dbconn;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

// this class handles how connections are managed and created
// basically it manages lifecycle of database connections
public abstract class AbstractDatabaseConnectionPoolManager implements DatabaseConnectionPoolManager {

    // TODO this we many need to read from some external configuration file
    private static final int POOL_SIZE = 10;

    // this is supposed to be shared across multiple threads
    Queue<DatabaseConnection>  connectionQueue = new ConcurrentLinkedQueue<>();

    // constructor (create pool size)
    public AbstractDatabaseConnectionPoolManager() {
        createPool();
    }

    // create connection pool size
    void createPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            DatabaseConnection connection = new DatabaseConnection();
            connectionQueue.add(connection);
        }
    }

    public DatabaseConnection getConnection() {
        if (connectionQueue.isEmpty()) {
            return null; // either we can return null or can throw some exception
        }

        // TODO here is an issue when multiple threads are trying to get connection at the same time
        // and all threads will try to remove the same connection and end up with some exception null value
        // we need to think in some different way to solve this, we will think later on while we will learn threads
        return connectionQueue.remove();
    }

    public void releaseConnection(DatabaseConnection connection) {
        connectionQueue.add(connection); // adding back to connection queue
    }

    // TODO we can think of strategy when connection is not available
    //  we may need to wait and manage some queue with some threshold
}
