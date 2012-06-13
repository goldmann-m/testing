package de.goldmann.texter.dao.impl;

import javax.ejb.Stateless;

import de.goldmann.texter.dao.ArticleDao;
import de.goldmann.texter.model.Article;

/**
 * Implementation of {@link ArticleDao}.
 */
@Stateless
public class ArticleDaoImpl extends GenericJpaDao<Article, Long> implements
		ArticleDao {

	public ArticleDaoImpl() {
		super(Article.class);
	}
}
