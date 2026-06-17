package org.lld.creational.factory.dbconn;

import org.lld.creational.singletone.dbconn.*;

// this is factory to create database connection pool using different singletone strategy
public class DBConnectionPoolFactory {

    // this enum will be used to define the type of creation of database connection manager/pool
    // actually in production grade we will go with only one object creation strategy using singletone
    // but here we have shown different ways to implement singletone and using factory we are creating connection pool
    // suing different singletone strategy
    public static DatabaseConnectionPoolManager getDataBaseConnectionPool(DBConnectionManagerCreationType type) {
         if(type == null) {
             throw new IllegalArgumentException("Invalid type");
         }

         return switch(type) {
             case LAZY_INITIALIZATION -> DatabaseConnectionPoolLazy.getInstance();
             case EAGER_INITIALIZATION -> DatabaseConnectionPoolEager.getInstance();
             case DOUBLE_CHECK_LOCKING -> DatabaseConnectionPoolLazy1.getInstance();
             case ENUM_SINGLETON -> DatabaseConnectionPoolEnum.INSTANCE;
             default -> throw new IllegalArgumentException("Invalid type");
         };
     }
}
