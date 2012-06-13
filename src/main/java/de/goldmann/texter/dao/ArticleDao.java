package de.goldmann.texter.dao;

import java.util.List;

import de.goldmann.texter.model.Article;

/**
 * Data access object for {@link Article} operations.
 * 
 * @author goldmannm
 * 
 */
public interface ArticleDao extends GenericDao<Article, Long> {

	/**
	 * @param userId
	 *            of logged in user
	 * @return all available articles from logged in user
	 */
	public List<Article> getAllArticlesFromUser(Long userId);

	/**
	 * @param name
	 *            of author
	 * @return all available articles from author
	 */
	public List<Article> getAllArticlesFromAuthor(String name);

}
