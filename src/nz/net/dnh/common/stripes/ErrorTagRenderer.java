package nz.net.dnh.common.stripes;

import java.io.IOException;

import net.sourceforge.stripes.tag.InputTagSupport;
import net.sourceforge.stripes.tag.TagErrorRenderer;

/**
 * Puts a little exclamation mark after the field, with a relevant message
 * 
 * @author danielh
 * 
 */
public class ErrorTagRenderer implements TagErrorRenderer {
	/**
	 * The tag we are working on
	 */
	private InputTagSupport tag;

	@Override
	public void doAfterEndTag() {
		try {
			this.tag.getPageContext()
					.getOut()
					.write("<img src=\"${pageContext.request.contextPath}\"><stripes:errors field=\""
							+ this.tag.getName() + "\" /></img></div>");
		} catch (IOException e) {
			// Can't do anything here
			throw new RuntimeException(e);
		}
	}

	@Override
	public void doBeforeStartTag() {
		try {
			this.tag.getPageContext().getOut()
					.write("<div class=\"FieldError\">");
		} catch (IOException e) {
			// Can't do anything here
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init(InputTagSupport arg0) {
		this.tag = arg0;
	}

}
