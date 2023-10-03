package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CruiseShipTest {

    private CruiseShip cruiseShip;

    @BeforeEach
    public void setUp() {
        // Create an instance of CruiseShip for testing
        cruiseShip = new CruiseShip(
                1, "ShipName", "Company", "Location", 7, 100, 2020, 3, 500, "Origin",
                "FinalDestination", "Destination1", "Destination2", "Destination3", "Destination4", "Destination5");
    }

    @Test
    public void testGetShipID() {
        assertEquals(1, cruiseShip.getShipID());
    }

    @Test
    public void testGetName() {
        assertEquals("ShipName", cruiseShip.getName());
    }

    @Test
    public void testGetCompany() {
        assertEquals("Company", cruiseShip.getCompany());
    }

    @Test
    public void testGetLocation() {
        assertEquals("Location", cruiseShip.getLocation());
    }

    @Test
    public void testGetTripLength() {
        assertEquals(7, cruiseShip.getTripLength());
    }

    @Test
    public void testGetNumCabins() {
        assertEquals(100, cruiseShip.getNumCabins());
    }

    @Test
    public void testGetYearOfBuild() {
        assertEquals(2020, cruiseShip.getYearOfBuild());
    }

    @Test
    public void testGetMaintenance() {
        assertEquals(3, cruiseShip.getMaintenance());
    }

    @Test
    public void testGetMaxCapacity() {
        assertEquals(500, cruiseShip.getMaxCapacity());
    }

    @Test
    public void testGetOrigin() {
        assertEquals("Origin", cruiseShip.getorigin());
    }

    @Test
    public void testSetMaintenance() {
        cruiseShip.setMaintenance(5);
        assertEquals(5, cruiseShip.getMaintenance());
    }
}
