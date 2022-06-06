package nz.net.dnh.evetool.entities.eve;

/**
 * Represents an EVE Item
 * 
 * @author danielh
 *
 */
public interface Item {
	/**
	 * Gets the type ID of this item
	 * 
	 * @return	The type ID
	 */
	public int getTypeID();
	
	/**
	 * Get the name of this type
	 * 
	 * @return	The name of the type
	 */
	public String getName();
	
	/**
	 * Get the mass-per-unit of this type
	 * 
	 * @return	The mass (Kg) per unit of this type
	 */
	public double getMass();
	
	/**
	 * Get the volume (m^3) of one unit of this type
	 * 
	 * @return	The volume of this type
	 */
	public double getVolume();
}
