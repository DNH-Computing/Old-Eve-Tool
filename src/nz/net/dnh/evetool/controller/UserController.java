package nz.net.dnh.evetool.controller;

import com.google.inject.Inject;

import flexjson.JSONSerializer;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import nz.net.dnh.common.auth.Authenticator;
import nz.net.dnh.evetool.auth.BasicAuthenticationAdaptor;
import nz.net.dnh.evetool.data.ICorporationRepository;

/**
 * A controller for actions relating to users.
 * 
 * @author danielh
 * 
 */
@UrlBinding("/user/{$event}")
public final class UserController extends EveController {
	/**
	 * The corporation repo
	 */
	@Inject
	private ICorporationRepository corpRepo;

	/**
	 * The JSON seriliser
	 */
	@Inject
	private JSONSerializer jsonHandler;

	/**
	 * Returns the login page
	 * 
	 * @return The forward resolution for the login page
	 */
	@DefaultHandler
	@HandlesEvent("login")
	public Resolution loginAction() {
		if (this.getContext().getRequest().getParameter("login") != null)
			if (this.actionHelper(Authenticator.class).authenticate(
					new BasicAuthenticationAdaptor(
							(String) this.getContext().getRequest().getParameter("username"), 
							(String) this.getContext().getRequest().getParameter("password"),
							(String) this.getContext().getRequest().getParameter("corporation"))).isValid())
				return new RedirectResolution("/");
		
		return new ForwardResolution("/views/user/login.jsp");
	}

	/**
	 * Create the list of available corporations from the given prefix
	 * 
	 * @return The forward resolution for the output page
	 */
	@HandlesEvent("corporations")
	public Resolution getVisiableCorporations() {
		this.getContext().getResponse().setContentType("application/json");

		return new ForwardResolution("/views/user/corporations.jsp");
	}
	
	/**
	 * Log out a user
	 * 
	 * @return	Redirect to /
	 */
	@HandlesEvent("logout")
	public Resolution logoutAction() {
		this.actionHelper(Authenticator.class).clearIdentity();
		
		return new RedirectResolution("/");
	}

	/**
	 * Get the avaliab corporations
	 * 
	 * @return A list of the avaliable corporations
	 */
	public String getAvaliableCorps() {
		return this.jsonHandler
				.include("corpName", "corpId")
				.exclude("*")
				.serialize(
						this.corpRepo
								.getAllVisibleCorporationsStartingWith(this
										.getContext().getRequest()
										.getParameter("term")));
	}
}
