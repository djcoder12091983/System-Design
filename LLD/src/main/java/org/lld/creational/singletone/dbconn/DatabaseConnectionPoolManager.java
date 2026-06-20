package org.lld.creational.singletone.dbconn;

// connection pool manager contract
public interface DatabaseConnectionPoolManager {

    // contract method to get connection
    DatabaseConnection getConnection();

    // contract method to release connection
    void releaseConnection(DatabaseConnection connection);

    // TODO may need to think of how to get current status of pool
    // like how many are in use and how many are available
}
