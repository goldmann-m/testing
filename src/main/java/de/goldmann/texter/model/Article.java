package de.goldmann.texter.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * Model for articles.
 * 
 * @author goldmannm
 * 
 */
@Entity
public class Article extends AbstractEntitiy {

	private static final long serialVersionUID = -7684245505708160216L;

	private String title;
	
	@Lob
	private String content;

	private Date creation;

	@OneToOne(cascade = CascadeType.REFRESH)
	private User author;

	/**
	 * @return title
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
	 * @return content
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

	/**
	 * @return creation date
	 */
	public Date getCreation() {
		return creation;
	}

	/**
	 * @param creation
	 * 			 - creation date to set
	 */
	public void setCreation(Date creation) {
		this.creation = creation;
	}

	/**
	 * @return {@link User} author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author
	 * 			- {@link User} author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

}
