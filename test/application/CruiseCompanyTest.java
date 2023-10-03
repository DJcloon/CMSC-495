package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CruiseCompanyTest {

    private CruiseCompany cruiseCompany;

    @BeforeEach
    public void setUp() {
        // Create an instance of CruiseCompany for testing
        cruiseCompany = new CruiseCompany("Company Name");
    }

    @Test
    public void testGetCompanyName() {
        // Test the getCompany method
        String companyName = cruiseCompany.getCompany();
        assertEquals("Company Name", companyName);
    }
}
