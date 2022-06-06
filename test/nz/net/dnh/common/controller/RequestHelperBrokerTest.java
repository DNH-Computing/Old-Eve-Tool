package nz.net.dnh.common.controller;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

public class RequestHelperBrokerTest {
	@Test(expected=IllegalArgumentException.class)
	public void testGetNonExistantHelperThrowsException() {
		RequestHelperBroker<ActionHelper> broker = 
			new RequestHelperBroker<ActionHelper>(new HashMap<Class<?>, HelperFactory<? extends ActionHelper>>(), null);
		
		broker.getHelper(new ActionHelper() {}.getClass());
	}
	
	@Test
	public void testGetHelperReturnsANewInstanceOnFirstRequest() {
		class A implements ActionHelper {}
		
		HelperFactory<A> factory = new HelperFactory<A>() {
			@Override public Class<? extends A> helperType() { return A.class; }
			@Override public A createHelper(ActionBeanContext context) { return new A(); }
		};
		HashMap<Class<?>, HelperFactory<? extends ActionHelper>> factories = 
			new HashMap<Class<?>, HelperFactory<? extends ActionHelper>>();
		factories.put(A.class, factory);
		RequestHelperBroker<ActionHelper> broker = new RequestHelperBroker<ActionHelper>(factories, null);
		
		assertTrue(broker.getHelper(A.class) instanceof A);
	}
	
	@Test
	public void testGetHelperReturnsTheSameObjectOnSubsequentRequests() {
		class A implements ActionHelper {}
		
		HelperFactory<A> factory = new HelperFactory<A>() {
			@Override public Class<? extends A> helperType() { return A.class; }
			@Override public A createHelper(ActionBeanContext context) { return new A(); }
		};
		HashMap<Class<?>, HelperFactory<? extends ActionHelper>> factories = 
			new HashMap<Class<?>, HelperFactory<? extends ActionHelper>>();
		factories.put(A.class, factory);
		RequestHelperBroker<ActionHelper> broker = new RequestHelperBroker<ActionHelper>(factories, null);
		
		A test = broker.getHelper(A.class);
		assertSame(test, broker.getHelper(A.class));
	}
}
