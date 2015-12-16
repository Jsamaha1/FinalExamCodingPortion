package base;

import static org.junit.Assert.*;
import base.RateDAL;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RateDAL_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		int creditScore = 630;
		double interestrate = RateDAL.getRate(creditScore);
		
		
		assertEquals(interestrate, 5, 4);
		
		creditScore = 700;
		interestrate = RateDAL.getRate(creditScore);
		
		assertEquals(interestrate, 4, 3);
		
		System.out.println(interestrate);
		//fail("Not yet implemented");
	}

}
