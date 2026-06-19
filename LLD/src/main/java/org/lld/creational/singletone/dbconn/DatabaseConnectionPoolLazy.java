package org.lld.creational.singletone.dbconn;

// this class will take care of loading connection pool on demand so in case of eager it might not be used after initialization
// but here in case of lazy we will obtain the pool whenever we need
public class DatabaseConnectionPoolLazy extends AbstractDatabaseConnectionPoolManager {

    public static DatabaseConnectionPoolLazy getInstance() {
        return LazyHolder.INSTANCE;
    }

    private DatabaseConnectionPoolLazy() {
        // private constructor to prevent instantiation
    }

    // static inner class to hold the singleton instance
    private static class LazyHolder {
        private static final DatabaseConnectionPoolLazy INSTANCE = new DatabaseConnectionPoolLazy();
    }

    // other methods we will inherit from parent class
}
