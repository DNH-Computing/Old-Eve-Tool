package nz.net.dnh.evetool.controller;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import nz.net.dnh.common.auth.Authenticator;
import nz.net.dnh.common.controller.AbstractController;
import nz.net.dnh.common.tag.HeadTitleHelper;
import nz.net.dnh.evetool.Bootstrapper;

/**
 * Set up EVE specific controller setting
 * 
 * @author danielh
 *
 */
public abstract class EveController extends AbstractController {
	/**
	 * Set the title base for all EVE controllers
	 */
	@Before(stages={LifecycleStage.EventHandling})
	public final void setTitleBase() {
		this.actionHelper(HeadTitleHelper.class).setTitle("EVE Tool");
	}
	
	/**
	 * Inject's this bean with Guice.
	 */
	@Before(stages={LifecycleStage.HandlerResolution})
	public final void injectThis() {
		Bootstrapper.inject.injectMembers(this);
	}
	
	/**
	 * Determine if there is a logged in user
	 * 
	 * @return	True if there is a logged in user, false otherwise
	 */
	public final boolean isLoggedIn() {
		return this.actionHelper(Authenticator.class).isLoggedIn();
	}
}
