/**
 * 
 */
package nz.net.dnh.common.tag;

import nz.net.dnh.common.controller.ActionHelper;

/**
 * Allows the setting of the page's title from other paces. Only once title can be in creation at a time.
 * 
 * @author danielh
 *
 */
public class HeadTitleHelper implements ActionHelper {
	/**
	 * The title of the page being rendered
	 */
	private StringBuilder text = new StringBuilder();
	
	/**
	 * Set the text of the title, disregarding whatever was there before
	 * 
	 * @param title	The new title text
	 */
	public void setTitle(String title) {
		text = new StringBuilder(title);
	}
	
	/**
	 * Append <code>title</code> to the current text being held.
	 * 
	 * @param title	The text to append to the title
	 */
	public void appendToTitle(String title) {
		text.append(title);
	}
	
	/**
	 * Get the title string
	 * 
	 * @return	The constructed title string
	 */
	public String getTitle() {
		return text.toString();
	}
}
