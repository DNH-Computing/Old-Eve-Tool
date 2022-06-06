package nz.net.dnh.evetool.hibernate.evetool;

import static org.junit.Assert.*;
import nz.net.dnh.evetool.Bootstrapper;
import nz.net.dnh.evetool.hibernate.evetool.Role;
import nz.net.dnh.evetool.hibernate.evetool.RoleHome;
import nz.net.dnh.evetool.hibernate.evetool.User;
import nz.net.dnh.evetool.hibernate.evetool.UserHome;
import nz.net.dnh.evetool.hibernate.evetool.UserId;

import org.junit.*;

public class UserTest {
	private UserHome u;
	
	@Before
	public void setUp() throws Exception {
		Bootstrapper.bootstrap();
		this.u = Bootstrapper.inject.getInstance(UserHome.class);
	}
	
	@After
	public void tearDown() {
		Bootstrapper.unbootstrap();
	}
	
	@Test
	public void testUserExists() {
		User u = this.u.findById(new UserId("ModuleTestUser", 0));
		
		assertNotNull(u);
	}
	
	@Test
	public void testManyManyUserRoleMapping() {
		User u = this.u.findById(new UserId("ModuleTestUser", 0));
		assertSame(0, u.getUserId());
		assertSame(RegistrationType.Registered, u.getRegistrationType());
		
		assertNotNull(u);
		assertNotNull(u.getUserRole());
		assertNotSame(0, u.getUserRole().size());
		
		Role guestRole = Bootstrapper.inject.getInstance(RoleHome.class).findById(1);
		Role securityVCheckRole = Bootstrapper.inject.getInstance(RoleHome.class).findById(2);
		
		assertSame(1, u.getUserRole().size());
		assertTrue(u.getUserRole().contains(securityVCheckRole));
		
		Role parent = u.getUserRole().iterator().next().getParent();
		assertNotNull(parent);
		
		assertTrue(parent.equals(guestRole));
		
		assertEquals("TestCorp1", u.getCorporation().getCorpName());
	}
	
	@Test
	public void testPassword() {
		User u = this.u.findById(new UserId("Daniel", 98013403));
		
		assertTrue(u.isPassword("foo"));
	}
}