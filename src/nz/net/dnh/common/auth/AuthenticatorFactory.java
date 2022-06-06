package nz.net.dnh.common.auth;

import nz.net.dnh.common.controller.ActionBeanContext;
import nz.net.dnh.common.controller.HelperFactory;

/**
 * A per request factory for authenticators
 * 
 * @author danielh
 *
 */
public class AuthenticatorFactory implements HelperFactory<Authenticator> {
	/**
	 * @see nz.net.dnh.common.controller.HelperFactory#createHelper(ActionBeanContext)
	 */
	@Override
	public Authenticator createHelper(ActionBeanContext context) {
		return new Authenticator(context);
	}

	/**
	 * @see nz.net.dnh.common.controller.HelperFactory#helperType()
	 */
	@Override
	public Class<? extends Authenticator> helperType() {
		return Authenticator.class;
	}

}
