package org.lld.creational.singletone.dbconn;

// it is same as lazy but with different implementation (double check with synchronized block)
public class DatabaseConnectionPoolLazy1 extends AbstractDatabaseConnectionPoolManager {

    private static volatile DatabaseConnectionPoolLazy1 instance;

    // constructor
    private DatabaseConnectionPoolLazy1() {
        // private constructor
    }

    public static DatabaseConnectionPoolLazy1 getInstance() {
        if (instance == null) { // this helps to share this code across multiple threads easily
            synchronized (DatabaseConnectionPoolLazy1.class) {
                if (instance == null) {
                    // this is required in case of multiple threads on same condition
                    // so need to avoid duplicate object creation
                    instance = new DatabaseConnectionPoolLazy1();
                }
            }
        }
        return instance;
    }
}
