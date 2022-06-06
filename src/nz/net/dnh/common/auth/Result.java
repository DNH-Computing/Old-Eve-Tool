package nz.net.dnh.common.auth;

/**
 * A list of valid result types
 * 
 * @author danielh
 *
 */
public enum Result {
	/**
	 * The authentication was successful
	 */
	SUCCESS,
	
	/**
	 * The authentication failed
	 */
	FAILURE,
	
	/**
	 * The authentication failed because the identity was not found
	 */
	FAILURE_IDENTITY_NOT_FOUND,
	
	/**
	 * The authentication failed because the identity was ambiguious
	 */
	FAILURE_IDENTITY_ANBIGUIOUS,
	
	/**
	 * The authentication failed because the supplied credential was invalid
	 */
	FAILURE_CREDENTIAL_INVALID,
	
	/**
	 * The authentication failed because of an uncatagorised error
	 */
	FAILURE_UNCATAGORISED;

	/**
	 * Determines if the authentication request was successfull
	 * 
	 * @return	True if the authentication was successful, false otherwise
	 */
	public boolean isValid() {
		return this == SUCCESS;
	}
}