package nz.net.dnh.common.auth;

import nz.net.dnh.common.controller.ActionBeanContext;
import nz.net.dnh.common.controller.ActionHelper;

/**
 * The DNH authenticator
 * 
 * @author danielh
 *
 */
public class Authenticator implements ActionHelper {
	/**
	 * The context this authenticator is running in
	 */
	private final ActionBeanContext context;
	
	/**
	 * Set the context for this request
	 * 
	 * @param context
	 */
	public Authenticator(ActionBeanContext context) {
		this.context = context;
	}
	
	/**
	 * Perform the authentication and return the result. If the authentication is
	 * successful put the identity into the session
	 * 
	 * @param adaptor	The adaptor to use
	 * @return	The status of the authentication operation
	 */
	public Result authenticate(AuthenticationAdaptor adaptor) {
		AuthenticationResult result = adaptor.authenticate();
		
		if (result.Result != Result.SUCCESS)
			return result.Result;
		
		this.context.setInSessionNamespace("identity", result.Identity, "nz.net.dnh.common.auth");
		
		return result.Result;
	}
	
	/**
	 * Determine if a successful authentication has occurred during this session.
	 * 
	 * @return	True if the user is logged in, false otherwise
	 */
	public boolean isLoggedIn() {
		return this.context.getInSessionNamespace("identity", "nz.net.dnh.common.auth") != null;
	}
	
	/**
	 * Clear the stored identity for this session. This is the quivilent to logout
	 */
	public void clearIdentity() {
		this.context.setInSessionNamespace("identity", null, "nz.net.dnh.common.auth");
	}
}
