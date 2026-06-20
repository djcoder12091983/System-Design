package org.lld.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.creational.factory.logistic.*;

import static org.junit.jupiter.api.Assertions.*;

// test cases for logistics factory method
public class LogisticsFactoryTest {

    @Test
    @DisplayName("RoadLogistics should strictly instantiate a Truck instance")
    void testRoadLogisticsCreatesTruck() {
        Logistics roadLogistics = new RoadLogistics();
        Transport transport = roadLogistics.createTransport();

        assertNotNull(transport, "Transport instance should not be null");
        assertInstanceOf(Truck.class, transport, "RoadLogistics must manufacture a Truck");
    }

    @Test
    @DisplayName("SeaLogistics should strictly instantiate a Ship instance")
    void testSeaLogisticsCreatesShip() {
        Logistics seaLogistics = new SeaLogistics();
        Transport transport = seaLogistics.createTransport();

        assertNotNull(transport, "Transport instance should not be null");
        assertInstanceOf(Ship.class, transport, "SeaLogistics must manufacture a Ship");
    }

    @Test
    @DisplayName("AirLogistics should strictly instantiate a Plane instance")
    void testAirLogisticsCreatesPlane() {
        Logistics airLogistics = new AirLogistics();
        Transport transport = airLogistics.createTransport();

        assertNotNull(transport, "Transport instance should not be null");
        assertInstanceOf(Plane.class, transport, "AirLogistics must manufacture a Plane");
    }
}
