package de.goldmann.texter.model;

import javax.persistence.Entity;

/**
 * Model for text.
 * 
 * @author goldmannm
 * 
 */
@Entity
public class Text extends AbstractEntitiy {

	private static final long serialVersionUID = -8401808080605279422L;

	private String title;
	private String content;

	/**
	 * @return title of text
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            - title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return content of text
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            - content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
