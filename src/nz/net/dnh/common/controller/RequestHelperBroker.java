package nz.net.dnh.common.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * A per request helper broker.
 * 
 * @author danielh
 *
 * @param <T>	The type of helper to be created
 */
public final class RequestHelperBroker<T extends Helper> {
	/**
	 * The mapping of helpers to helper objects
	 */
	private final Map<Class<?>, T> helpers = new HashMap<Class<?>, T>();
	
	/**
	 * All of the helpers that will allow the helpers to be constructed
	 */
	private final Map<Class<?>, HelperFactory<? extends T>> factories;
	
	/**
	 * The context of this request
	 */
	private final ActionBeanContext context;

	/**
	 * Private constructor to ensure we only ever have two of these
	 * 
	 * @param factories	The factories for this request 
	 * @param context	The context of the request
	 */
	RequestHelperBroker(Map<Class<?>, HelperFactory<? extends T>> factories, ActionBeanContext context) {
		this.factories = factories;
		this.context = context;
	}
	
	/**
	 * Get a helper object.
	 * 
	 * @param <O>	The type of the object 
	 * @param type	The type of the helper
	 * @return	The helper object
	 * @throws IllegalArgumentException	Thrown if the helper specified by <code>name</code> doesn't exist
	 */
	@SuppressWarnings("unchecked")
	public <O extends T> O getHelper(Class<O> type) {
		if (!this.factories.containsKey(type))
			throw new IllegalArgumentException("No helper of type "+type.getName()+" exists in the mapping");
		
		if (!this.helpers.containsKey(type))
			this.helpers.put(type, this.factories.get(type).createHelper(this.context));
		
		return (O) this.helpers.get(type);
	}
}
