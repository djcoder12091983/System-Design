package org.lld.creational.factory.dbconn;

// this enum will be used to define the type of creation of database connection manager/pool
// actually in production grade we will go with only one object creation strategy using singletone
// but here we have shown different ways to implement singletone and using factory we are creating connection pool
// suing different singletone strategy
public enum DBConnectionManagerCreationType {
    LAZY_INITIALIZATION,
    EAGER_INITIALIZATION,
    DOUBLE_CHECK_LOCKING,
    //BILL_PUGH_SINGLETON,
    ENUM_SINGLETON
}
