package org.lld.creational.singletone.dbconn;

// this is another connection pool singletone which will be initialized eagerly where threads will not wait
public class DatabaseConnectionPoolEager extends AbstractDatabaseConnectionPoolManager {
    // eager initialization
    private static final DatabaseConnectionPoolEager instance = new DatabaseConnectionPoolEager();

    private DatabaseConnectionPoolEager() {
        // private constructor to prevent instantiation
    }

    public static DatabaseConnectionPoolEager getInstance() {
        return instance;
    }

     // other methods we will inherit from parent class
}
