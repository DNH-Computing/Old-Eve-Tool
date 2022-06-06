package nz.net.dnh.common;

/**
 * Global helper functions
 * 
 * @author danielh
 *
 */
public class Globals {
	/**
	 * The hex charset
	 */
	private static final String HEXES = "0123456789ABCDEF";
	
	/**
	 * Get the hex representation of an array of bytes 
	 * 
	 * @param raw	The bytes to encode
	 * @return	The hex string. All letters are upper case
	 */
	public static String getHex( byte [] raw ) {
	    if ( raw == null ) {
	      return null;
	    }
	    final StringBuilder hex = new StringBuilder( 2 * raw.length );
	    for ( final byte b : raw ) {
	      hex.append(HEXES.charAt((b & 0xF0) >> 4))
	         .append(HEXES.charAt((b & 0x0F)));
	    }
	    return hex.toString();
	}
}
