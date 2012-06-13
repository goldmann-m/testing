package de.goldmann.texter.services;

import java.util.List;

import de.goldmann.texter.model.Article;

/**
 * Service for article handling.
 * 
 * @author goldmannm
 * 
 */
public interface ArticleService {

	/**
	 * create new article.
	 * 
	 * @param article
	 */
	public void createArticle(Article article);

	/**
	 * edit existing article.
	 * 
	 * @param article
	 */
	public void editArticle(Article article);

	/**
	 * remove existing article
	 * 
	 * @param article
	 */
	public void removeArticle(Article article);

	/**
	 * @return all available articles
	 */
	public List<Article> getAllArticles();

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
