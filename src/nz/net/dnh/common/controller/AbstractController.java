/**
 * 
 */
package nz.net.dnh.common.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.ActionBean;

/**
 * An abstract base for controllers providing a lot of useful methods.
 * 
 * @author danielh
 *
 */
public abstract class AbstractController implements ActionBean {
	/**
	 * The context that this controller is running in
	 */
	private ActionBeanContext context;
	
	/**
	 * The session data
	 */
	private HttpSession session;
	
	/**
	 * The action helper broker for this request
	 */
	private RequestHelperBroker<ActionHelper> actionHelperBroker;

	/**
	 * Set the context for this request
	 * 
	 * @param context	The context of the request
	 * @see net.sourceforge.stripes.action.ActionBean#setContext(net.sourceforge.stripes.action.ActionBeanContext)
	 */
	public final void setContext(net.sourceforge.stripes.action.ActionBeanContext context) {
		this.context = (ActionBeanContext) context;
		this.actionHelperBroker =  HelperBroker.ActionHelperBroker.getInstanceBroker(this.context.getRequest(), this.context);
		this.session = context.getRequest().getSession();
	}

	/**
	 * Get the current request context
	 * 
	 * @see net.sourceforge.stripes.action.ActionBean#getContext()
	 */
	@Override
	public final net.sourceforge.stripes.action.ActionBeanContext getContext() {
		return this.context;
	}
	
	/**
	 * Redirect the current reqiest to <code>locationURI</code>
	 * 
	 * @param locationURI	The location to redirect to
	 */
	protected void redirect(String locationURI) {
		try {
			this.context.getResponse().sendRedirect(locationURI);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Get's a registered action helper
	 * 
	 * @param <T>	The type of the object
	 * @param name	The name of the action helper
	 * @return	The action helper
	 */
	protected <T extends ActionHelper> T actionHelper(Class<T> name) {
		return this.actionHelperBroker.getHelper(name);
	}
	
	/**
	 * Get an object out of the session
	 * 
	 * @param <T>	The type of the object
	 * @param name	The name of the object
	 * @param type	The type of the object
	 * @return	The object from the session or null if none was found
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getSessionattribute(String name, Class<T> type) {
		return (T) this.session.getAttribute(name);
	}
}