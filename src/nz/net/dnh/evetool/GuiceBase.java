package nz.net.dnh.evetool;

/**
 * A base class for allowing easer injections with Guice
 * 
 * @author danielh
 *
 */
public abstract class GuiceBase {
	/**
	 * Inject this object
	 */
	protected GuiceBase() {
		Bootstrapper.inject.injectMembers(this);
	}
}
