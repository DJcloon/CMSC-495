package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CabinTest {

	private Cabin standardCabin;
	private Cabin deluxeCabin;
	private Cabin premiumCabin;
	private Cabin spaCabin;

	@BeforeEach
	public void setUp() {
		// Initialize the instances of the subclasses through the parent class reference
		standardCabin = new StandardCabin();
		deluxeCabin = new DeluxeCabin();
		premiumCabin = new PremiumCabin();
		spaCabin = new SpaCabin();
	}

	@Test
	public void testStandardCabinAmenities() {
		// StandardCabin is supposed to have "Porthole, Toilet," amenities.
		assertEquals("Porthole, Toilet,", standardCabin.getAmenities());
	}

	@Test
	public void testDeluxeCabinAmenities() {
		// DeluxeCabin is supposed to have "Window, Toilet" amenities.
		assertEquals("Window, Toilet", deluxeCabin.getAmenities());
	}

	@Test
	public void testPremiumCabinAmenities() {
		// PremiumCabin is supposed to have "Balcony, Bath, Toilet" amenities.
		assertEquals("Balcony, Bath, Toilet", premiumCabin.getAmenities());
	}

	@Test
	public void testSpaCabinAmenities() {
		// SpaCabin is supposed to have "Balcony, Bath, Toilet, Storage Closet"
		// amenities.
		assertEquals("Balcony, Bath, Toilet, Storage Closet", spaCabin.getAmenities());
	}
}
