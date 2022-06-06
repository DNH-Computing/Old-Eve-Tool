package nz.net.dnh.evetool.hibernate.evetool;


import nz.net.dnh.evetool.Bootstrapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CorporationTest {
	private CorporationHome home;
	
	@Before
	public void setUp() throws Exception {
		Bootstrapper.bootstrap();
		
		this.home = Bootstrapper.inject.getInstance(CorporationHome.class);
	}

	@After
	public void tearDown() throws Exception {
		Bootstrapper.unbootstrap();
	}
	
	@Test
	public void testGetTestCorpByName() {
		Corporation c = this.home.findByName("TestCorp1");
		assertNotNull(c);
		
		assertSame(0, c.getCorpId());
	}

}
