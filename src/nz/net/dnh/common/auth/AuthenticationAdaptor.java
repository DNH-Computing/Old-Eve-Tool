package nz.net.dnh.common.auth;

/**
 * A base for all the authenticators
 * 
 * @author danielh
 *
 */
public interface AuthenticationAdaptor {
	/**
	 * Run the authentication routine and return a
	 * 
	 * @return	The result of the authentication
	 */
	public AuthenticationResult authenticate();
}
