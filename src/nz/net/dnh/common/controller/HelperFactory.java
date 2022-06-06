package nz.net.dnh.common.controller;


/**
 * A factory for creating helpers on a per request basis.
 * 
 * @author danielh
 *
 * @param <T>	The type of object this factory will be making.
 */
public interface HelperFactory<T extends Helper> {
	/**
	 * Creates a helper for the request. This will only be called once per request
	 * 
	 * @param context	The context the helper is running in
	 * @return	The new constructed helper
	 */
	T createHelper(ActionBeanContext context);
	
	/**
	 * Returns the type of the helper to be created
	 * 
	 * @return	The type of the helper object
	 */
	Class<? extends T> helperType();
}
