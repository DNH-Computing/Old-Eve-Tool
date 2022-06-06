package nz.net.dnh.common.tag;

import nz.net.dnh.common.controller.ActionBeanContext;
import nz.net.dnh.common.controller.HelperFactory;

/**
 * A factory for the {@link HeadTitleHelper} class
 * 
 * @author danielh
 *
 */
public class HeadTitleFactory implements HelperFactory<HeadTitleHelper> {
	/**
	 * Creates a new head title object
	 * 
	 * @see HelperFactory#createHelper(nz.net.dnh.common.controller.ActionBeanContext)
	 */
	@Override
	public HeadTitleHelper createHelper(ActionBeanContext context) {
		return new HeadTitleHelper();
	}

	/**
	 * Returns the type of the {@link HeadTitleHelper} object
	 * 
	 * @see HelperFactory#helperType()
	 */
	@Override
	public Class<HeadTitleHelper> helperType() {
		return HeadTitleHelper.class;
	}

}
