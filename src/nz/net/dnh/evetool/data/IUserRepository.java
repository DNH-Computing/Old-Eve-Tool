package nz.net.dnh.evetool.data;

import nz.net.dnh.evetool.entities.evetool.Corporation;
import nz.net.dnh.evetool.entities.evetool.User;

/**
 * Defines the interface for retrieving users from a persistant storage backend
 * 
 * @author danielh
 *
 */
public interface IUserRepository {
	/**
	 * Get the guest user for the system
	 * 
	 * @return	The guest user 
	 */
	public User getGuestUser();
	
	/**
	 * Get a unique user based on the username and the name of the corporation they belong to.
	 * 
	 * @param username	The username of the user.
	 * @param corpname	The name of the user's corporation
	 * @return	The unique user, or null if none found
	 */
	public User getUser(String username, String corpname);
	
	/**
	 * Get a unique user based on the user's username and corporation
	 * 
	 * @param username	The username of the user.
	 * @param corp	The corporation they belong to.
	 * @return	The unique user, or null if none found
	 */
	public User getUser(String username, Corporation corp);
}
