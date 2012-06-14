package de.goldmann.texter.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import de.goldmann.texter.dao.ArticleDao;
import de.goldmann.texter.model.Article;
import de.goldmann.texter.model.Article_;
import de.goldmann.texter.model.User_;

/**
 * Implementation of {@link ArticleDao}.
 */
@Stateless
public class ArticleDaoImpl extends GenericJpaDao<Article, Long> implements
		ArticleDao {

	public ArticleDaoImpl() {
		super(Article.class);
	}

	/*
	 * (non-Javadoc)
	 * @see de.goldmann.texter.dao.ArticleDao#getAllArticlesFromUser(java.lang.Long)
	 */
	@Override
	public List<Article> getAllArticlesFromUser(Long userId) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
		
		Root<Article> article = criteriaQuery.from(Article.class);
		
		Path<Long> path = article.join(Article_.author).get(User_.id);
		
		criteriaQuery.select(article).where(criteriaBuilder.equal(path, userId));
		
		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see de.goldmann.texter.dao.ArticleDao#getAllArticlesFromAuthor(java.lang.String)
	 */
	@Override
	public List<Article> getAllArticlesFromAuthor(String name) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
		
		Root<Article> article = criteriaQuery.from(Article.class);
		
		Path<String> path = article.join(Article_.author).get(User_.userName);
		
		criteriaQuery.select(article).where(criteriaBuilder.equal(path, name));
		
		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}
}
