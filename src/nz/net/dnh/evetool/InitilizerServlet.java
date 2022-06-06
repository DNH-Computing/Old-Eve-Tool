package nz.net.dnh.evetool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Initilize the static components of the website.
 * 
 * @author danielh
 *
 */
public class InitilizerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3392926477656695613L;

	/**
	 * Initilize the static web components
	 * 
	 * @throws ServletException	Never thrown
	 */
	public void init() throws ServletException {
		Bootstrapper.bootstrap();
	}
}
