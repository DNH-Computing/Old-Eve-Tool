package nz.net.dnh.evetool.entities.evetool;

/**
 * Defines the interface for a User in the system
 * 
 * @author danielh
 *
 */
public interface User {
	/**
	 * Determine if <code>password</code> is the user's password
	 * 
	 * @param password	The password to check
	 * @return	True iff the given string matches the user's password
	 */
	boolean isPassword(String password);
}
