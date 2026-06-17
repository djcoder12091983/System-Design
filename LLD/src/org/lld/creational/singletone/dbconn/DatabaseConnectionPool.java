package org.lld.creational.singletone.dbconn;

// this database class is supposed to be singletone
// here we will explore different ways to implement singletone
public class DatabaseConnectionPool extends AbstractDatabaseConnectionPoolManager {

        private static DatabaseConnectionPool instance;

        private DatabaseConnectionPool() {
            // private constructor to prevent instantiation
        }

        // this is not thread safe
        public static DatabaseConnectionPool getInstance() {
            if (instance == null) {
                instance = new DatabaseConnectionPool();
            }
            return instance;
        }

        // other methods we will inherit from parent class
}
