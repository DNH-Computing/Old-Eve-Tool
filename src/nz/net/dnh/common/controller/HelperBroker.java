package nz.net.dnh.common.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletRequest;

/**
 * Broker's requests for given helper components, and routes them to the
 * appropreate instances
 * 
 * @author danielh
 * @param <T>
 *            The type of object this helper resolves
 * 
 */
public final class HelperBroker<T extends Helper> {
	/**
	 * The mapping of helpers to helper objects
	 */
	private final Map<Class<?>, HelperFactory<? extends T>> factories = new ConcurrentHashMap<Class<?>, HelperFactory<? extends T>>();
	
	/**
	 * The key that should be used
	 */
	private final String requestParamKey;
	
	/**
	 * Creates a new HelperBroker with the given key paramater.
	 * 
	 * @param key	The paramater that should be used when storing the key in the request object
	 */
	public HelperBroker(String key) {
		this.requestParamKey = key;
	}

	/**
	 * Registers a helper with this helper broker. Note that this makes the
	 * helper avaliable to all current and future requests
	 * 
	 * @param helper
	 *            The helper to register
	 * @return Returns <code>this</code> for method chaining
	 * @throws IllegalArgumentException
	 *             Thrown if the name of the helper is a duplicate
	 */
	public HelperBroker<T> addHelper(HelperFactory<? extends T> helper) {
		if (this.factories.containsKey(helper.helperType()))
			throw new IllegalArgumentException(
					"The helper mapper already contains a helper of type"
							+ helper.getClass().getName());

		this.factories.put(helper.helperType(), helper);

		return this;
	}

	/**
	 * Create a new helper broker for a specific request. This ensures there is
	 * no leakage from one request to another
	 * 
	 * @param request	The request
	 * @param context	The context of the request
	 * @return A new per-request helper broker
	 */
	@SuppressWarnings("unchecked")
	public RequestHelperBroker<T> getInstanceBroker(ServletRequest request, ActionBeanContext context) {
		if (request.getAttribute(this.requestParamKey)==null) {
			if (context != null)
				request.setAttribute(this.requestParamKey, new RequestHelperBroker<T>(this.factories, context));
			else
				throw new IllegalArgumentException("There is no existing helper broker for this request. The first request for a helper broker must contain a context");
		}
		
		return (RequestHelperBroker<T>) request.getAttribute(this.requestParamKey);
	}
	
	/**
	 * Gets an instance broker based on just this request. This assumes that a request broker has already
	 * been created.
	 * 
	 * @param request	The request
	 * @return	The per-request helper broker
	 */
	public RequestHelperBroker<T> getInstanceBroker(ServletRequest request) {
		return this.getInstanceBroker(request, null);
	}
	
	/**
	 * Remove all helpers from this mapping
	 */
	public void clear() {
		this.factories.clear();
	}

	// -------------

	/**
	 * A helper for {@link ActionHelper} objects
	 */
	public static final HelperBroker<ActionHelper> ActionHelperBroker = new HelperBroker<ActionHelper>("ActionHelperBroker");

	/**
	 * A helper for {@link ViewHelper} objects
	 */
	public static final HelperBroker<ViewHelper> ViewHelperBroker = new HelperBroker<ViewHelper>("ViewHelperBroker");
}
