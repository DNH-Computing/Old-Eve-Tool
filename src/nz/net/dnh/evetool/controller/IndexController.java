package nz.net.dnh.evetool.controller;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * The index controller
 * 
 * @author danielh
 *
 */
@UrlBinding("/")
public final class IndexController extends EveController {
	/**
	 * The index action
	 * 
	 * @return	A pointer to the index JSP page
	 */
	@DefaultHandler
	public Resolution indexAction() {
		return new ForwardResolution("/views/index/index.jsp");
	}
}
