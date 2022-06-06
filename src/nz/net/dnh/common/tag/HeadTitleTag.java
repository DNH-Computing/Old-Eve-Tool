package nz.net.dnh.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import nz.net.dnh.common.controller.HelperBroker;
import nz.net.dnh.common.controller.ViewHelper;

/**
 * Specifies the tag section of the HeadTitle action and view helper
 * 
 * @author danielh
 * 
 */
public class HeadTitleTag extends TagSupport implements ViewHelper {
	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -3604616412628653621L;

	/**
	 * Output the title tag
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		StringBuilder output = new StringBuilder();

		output.append("<title>");
		output.append(HelperBroker.ActionHelperBroker
				.getInstanceBroker(this.pageContext.getRequest())
				.getHelper(HeadTitleHelper.class).getTitle());
		output.append("</title>");

		try {
			this.pageContext.getOut().write(output.toString());
		} catch (IOException e) {
			throw new JspException("Could not write tag to output stream", e);
		}

		return EVAL_PAGE;
	}
}
