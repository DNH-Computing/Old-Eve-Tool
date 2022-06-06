package nz.net.dnh.evetool.entities.evetool;

/**
 * Represents a user's corporation
 * 
 * @author danielh
 *
 */
public interface Corporation {
	/**
	 * Get the ID of the corporation
	 * 
	 * @return	The ID of the corporation
	 */
	public int getCorpId();
	
	/**
	 * Get the name of the corporation
	 * 
	 * @return	The corporations's name
	 */
	public String getCorpName();
}
