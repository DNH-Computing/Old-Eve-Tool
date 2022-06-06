package nz.net.dnh.common.auth;

/**
 * Holds the result of an authentication
 * 
 * @author danielh
 *
 */
public class AuthenticationResult {
	/**
	 * The result of the authentication
	 */
	public final Result Result;
	
	/**
	 * The identity if the authentication was successful
	 */
	public final Object Identity;
	
	/**
	 * Create a new result
	 * 
	 * @param r	The result of the authentication
	 * @param identity	The identity
	 */
	public AuthenticationResult(Result r, Object identity) {
		this.Result = r;
		this.Identity = identity;
	}
}