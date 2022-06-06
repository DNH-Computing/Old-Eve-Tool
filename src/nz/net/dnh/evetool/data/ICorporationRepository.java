package nz.net.dnh.evetool.data;

import java.util.List;

import nz.net.dnh.evetool.entities.evetool.Corporation;

/**
 * Defines a corporation repository
 * 
 * @author danielh
 *
 */
public interface ICorporationRepository {
	/**
	 * Gets a corporation by it's unique ID
	 * 
	 * @param corpID	The corporation's unique ID
	 * @return	The corporation or null if none found
	 */
	public Corporation getCorporationByID(int corpID);
	
	/**
	 * Get's a corporation by it's unique name
	 * 
	 * @param name	The name of the corporation
	 * @return	The corporation or null if none found.
	 */
	public Corporation getCorporationByName(String name);

	/**
	 * Get a list of searchable corporations which start whose name starts with <code>namePrefix</code>
	 * 
	 * @param namePrefix	The prefix to search for
	 * @return	A list containing all the corporations found
	 */
	public List<? extends Corporation> getAllVisibleCorporationsStartingWith(String namePrefix);
}
