package nz.net.dnh.evetool.hibernate.eve;

import static org.junit.Assert.*;

import nz.net.dnh.evetool.Bootstrapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	private ItemHome i;
	
	@Before
	public void setUp() throws Exception {
		Bootstrapper.bootstrap();
		this.i = Bootstrapper.inject.getInstance(ItemHome.class);
	}
	
	@After
	public void tearDown() {
		Bootstrapper.unbootstrap();
	}

	@Test
	public void testItemGet() {
		Item i = this.i.findItemByID(34);
		assertNotNull(i);
		
		assertEquals("Tritanium", i.getName());
	}
}
