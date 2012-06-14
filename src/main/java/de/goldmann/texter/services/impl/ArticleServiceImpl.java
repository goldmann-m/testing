package de.goldmann.texter.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import de.goldmann.texter.dao.ArticleDao;
import de.goldmann.texter.model.Article;
import de.goldmann.texter.services.ArticleService;

@RequestScoped
public class ArticleServiceImpl implements Serializable, ArticleService {

	private static final long serialVersionUID = 1026643269785571583L;

	@Inject
	private ArticleDao articleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.goldmann.texter.services.ArticleService#createArticle(de.goldmann.
	 * texter.model.Article)
	 */
	@Override
	public void createArticle(Article article) {
		articleDao.save(article);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.goldmann.texter.services.ArticleService#editArticle(de.goldmann.texter
	 * .model.Article)
	 */
	@Override
	public void editArticle(Article article) {
		articleDao.save(article);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.goldmann.texter.services.ArticleService#removeArticle(de.goldmann.
	 * texter.model.Article)
	 */
	@Override
	public void removeArticle(Article article) {
		articleDao.remove(article.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.goldmann.texter.services.ArticleService#getAllArticles()
	 */
	@Override
	public List<Article> getAllArticles() {
		return articleDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.goldmann.texter.services.ArticleService#getAllArticlesFromUser(java
	 * .lang.Long)
	 */
	@Override
	public List<Article> getAllArticlesFromUser(Long userId) {
		return articleDao.getAllArticlesFromUser(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.goldmann.texter.services.ArticleService#getAllArticlesFromAuthor(java
	 * .lang.String)
	 */
	@Override
	public List<Article> getAllArticlesFromAuthor(String name) {
		return articleDao.getAllArticlesFromAuthor(name);
	}

}
