package UnitTests;

import static org.junit.Assert.*;
import ch.makery.address.view.MortgageController;
import ch.makery.address.model.Rate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MortgageTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		final Rate testingRate = new Rate(); 
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		final Rate testingRate = new Rate(); 
		double test1 = testingRate.CalculateMortage(5000000.0, 40.0, 700, 300000.0, 15);
		assertEquals(test1, 1432.5, 50);
		
		double test2 = testingRate.CalculateMortage(5600.0, 400.0, 700, 300000.0, 15);
		assertEquals(test2, 0, 0);
		
		double test3 = testingRate.CalculateMortage(5600.0, 400.0, 700, 50000000.0, 30);
		assertEquals(test3, 0, 0);
		
	}

}
