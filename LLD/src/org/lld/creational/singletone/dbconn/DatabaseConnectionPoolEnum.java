package org.lld.creational.singletone.dbconn;

// enum avoids reflection and all other ways of creating instance
public enum DatabaseConnectionPoolEnum implements DatabaseConnectionPoolManager {

    INSTANCE;

    private class DatabaseConnectionPoolManagerHelper extends AbstractDatabaseConnectionPoolManager {
        // we will not implement anything here
    }

    // this will delegate actual operations
    private DatabaseConnectionPoolManager delegate = new DatabaseConnectionPoolManagerHelper();

    // constructor
    private DatabaseConnectionPoolEnum() {
        // private constructor
    }

    @Override
    public DatabaseConnection getConnection() {
        return delegate.getConnection();
    }

    @Override
    public void releaseConnection(DatabaseConnection connection) {
        delegate.releaseConnection(connection);
    }
}
