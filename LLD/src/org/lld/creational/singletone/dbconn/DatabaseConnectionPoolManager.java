package org.lld.creational.singletone.dbconn;

// connection pool manager contract
public interface DatabaseConnectionPoolManager {

    // contract method to get connection
    public DatabaseConnection getConnection();

    // contract method to release connection
    public void releaseConnection(DatabaseConnection connection);
}
