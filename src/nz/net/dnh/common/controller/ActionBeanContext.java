package nz.net.dnh.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * A special subclass of {@link ActionBeanContext}
 * 
 * @author danielh
 * 
 */
public class ActionBeanContext extends net.sourceforge.stripes.action.ActionBeanContext {
	/**
	 * Set a key's value in the global namespace
	 * 
	 * @param key	The key of the object
	 * @param toStore	The object to store
	 */
	public void setInSessionNamespace(String key, Object toStore) {
		this.setInSessionNamespace(key, toStore, "global");
	}

	/**
	 * Store an object in a particular session namesapce
	 * 
	 * @param key	The key of the object
	 * @param toStore	The object to store
	 * @param namespace	The namespace to store it in
	 */
	@SuppressWarnings("unchecked")
	public void setInSessionNamespace(String key, Object toStore, String namespace) {
		Map<String, Map<String, Object>> namespaces;
		if ((namespaces = (Map<String, Map<String, Object>>) this.getRequest()
				.getSession().getAttribute("namespaces")) == null)
			this.getRequest()
					.getSession()
					.setAttribute("namespaces",
							namespaces = new HashMap<String, Map<String, Object>>());
		
		if (!namespaces.containsKey(namespace))
			namespaces.put(namespace, new HashMap<String, Object>());
		
		namespaces.get(namespace).put(key, toStore);
		
		this.getRequest().getSession().setAttribute("namespaces", namespaces);
	}
	
	/**
	 * Get an object in the global namespace
	 * 
	 * @param key	The key of the object
	 * @return	The object from the namespace or null if none found
	 */
	public Object getInSessionNamespace(String key) {
		return this.getInSessionNamespace(key, Object.class);
	}
	
	/**
	 * Get a typed object from the global session namespace
	 * 
	 * @param <T>	The type of the object
	 * @param key	The key of the object
	 * @param clazz	The class representing the type of the object
	 * @return	The object, or null if none found
	 * @see #getInSessionNamespace(String)
	 */
	public <T> T getInSessionNamespace(String key, Class<T> clazz) {
		return this.getInSessionNamespace(key, clazz, "global");
	}
	
	/**
	 * Get an object in a specific namespace.
	 * 
	 * @param key	The key of the object
	 * @param namespace	The namespace it is in.
	 * @return	The object or null if not found.
	 */
	public Object getInSessionNamespace(String key, String namespace) {
		return this.getInSessionNamespace(key, Object.class, namespace);
	}
	
	/**
	 * Get a typed object from a specific namespace
	 * 
	 * @param <T>	The type of the object
	 * @param key	The key of the object
	 * @param clazz	The class representing the type of the object
	 * @param namespace	The namespace where the object lives
	 * @return	The object or null if none found
	 */
	@SuppressWarnings("unchecked")
	public <T> T getInSessionNamespace(String key, Class<T> clazz, String namespace) {
		HttpSession session = this.getRequest().getSession();
		Map<String, Map<String, Object>> namespaces;
		
		if ((namespaces = (Map<String, Map<String, Object>>) session.getAttribute("namespaces")) == null)
			return null;
		
		if (!namespaces.containsKey(namespace))
			return null;
		
		Map<String, Object> namespaceData = namespaces.get(namespace);
		
		return (namespaceData.containsKey(key)) ? (T) namespaceData.get(key) : null;
	}
}
