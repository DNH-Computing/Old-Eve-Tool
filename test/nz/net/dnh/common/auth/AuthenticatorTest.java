package nz.net.dnh.common.auth;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import nz.net.dnh.common.controller.ActionBeanContext;
import nz.net.dnh.common.controller.ActionHelper;
import nz.net.dnh.common.controller.HelperBroker;
import nz.net.dnh.common.controller.RequestHelperBroker;
import nz.net.dnh.evetool.Bootstrapper;

import org.junit.*;

public class AuthenticatorTest {
	@After public void tearDown() { Bootstrapper.unbootstrap(); }
	@Before	public void setUp() throws Exception { Bootstrapper.bootstrap(); }

	
	@Test
	public void testAuthenticator() {
		ActionBeanContext context = mock(ActionBeanContext.class);
		when(context.getRequest()).thenReturn(mock(HttpServletRequest.class));
		
		Authenticator a = new Authenticator(context);
		AuthenticationAdaptor adaptor = new AuthenticationAdaptor() {
			@Override
			public AuthenticationResult authenticate() {
				return new AuthenticationResult(Result.SUCCESS, "FOO");
			}
		};
		
		a.authenticate(adaptor);
		
		verify(context).setInSessionNamespace("identity", "FOO", "nz.net.dnh.common.auth");
	}
}
