package org.lld.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.lld.creational.factory.dbconn.DBConnectionManagerCreationType;
import org.lld.creational.factory.dbconn.DBConnectionPoolFactory;
import org.lld.creational.singletone.dbconn.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

// unit testing for DBConnectionPool
public class DBConnectionPoolTest {

    private String expectedClassName(DBConnectionManagerCreationType type) {
        return switch(type) {
            case LAZY_INITIALIZATION -> DatabaseConnectionPoolLazy.class.getName();
            case EAGER_INITIALIZATION -> DatabaseConnectionPoolEager.class.getName();
            case DOUBLE_CHECK_LOCKING -> DatabaseConnectionPoolLazy1.class.getName();
            case ENUM_SINGLETON -> DatabaseConnectionPoolEnum.INSTANCE.getClass().getName();
        };
    }

    // this class will test the get connection method
    // also ensures single instance is created and as we are using singleton pattern
    // TODO need to think how this behaves in multiple threads
    //  i mean need to think how to check it behaves consistently in multiple threaded environment
    @ParameterizedTest(name = "Factory should correctly instantiate type: {0}")
    @EnumSource(DBConnectionManagerCreationType.class)
    @DisplayName("Should successfully build all connection pool types with uniform behavior")
    public void testGetConnection(DBConnectionManagerCreationType type) {
        // Act: Call the factory method for the current loop iteration
        DatabaseConnectionPoolManager connectionPoolManager = DBConnectionPoolFactory.getDataBaseConnectionPool(type);

        // Assert: Check that the returned instance is of the expected class
        assertThat(connectionPoolManager.getClass().getName()).isEqualTo(expectedClassName(type));

        // Assert: Check that the same instance is returned for each iteration
        assertSame(connectionPoolManager, DBConnectionPoolFactory.getDataBaseConnectionPool(type));
    }

    // it will accept connection pool and test the connection
    // whether it behaves accordingly or not
    private void testConnectPool(DatabaseConnectionPoolManager connectionPoolManager) {
        // TODO will implement later on, idea is like we will create connection and release it
        // and check if it is behaving accordingly, checking the pool size and available connections
    }
}
