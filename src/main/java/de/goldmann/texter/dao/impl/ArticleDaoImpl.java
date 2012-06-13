package de.goldmann.texter.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.goldmann.texter.dao.ArticleDao;
import de.goldmann.texter.model.Article;
import de.goldmann.texter.model.Article_;
import de.goldmann.texter.model.User;
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
		
		//criteriaQuery.select(article).where(criteriaBuilder.equal(article.get(Article_.author), )).distinct(true);
		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		 CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//		 Root<Country> s = cq.from(Country.class);
//
//		 ListJoin<Country, State> cs= s.join(Country_.states);
//		 ListJoin<State, District> sd = cs.join(State_.disctricts);
//		 ListJoin<District, City> dc = sd.join(District_.cities);
//		 ListJoin<City, Camping> cc = dc.join(City_.campings);
//
//		 cq.select(cb.count(cc)).where(cb.equal(s.get(Country_.isoCountryCode), country.getIsoCountryCode()));
//
//		 try {
//		     return em.createQuery(cq).getSingleResult();
//		 } catch (NoResultException nre) {
//		     nre.printStackTrace();
//		 }
//
//		 return 0L;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see de.goldmann.texter.dao.ArticleDao#getAllArticlesFromAuthor(java.lang.String)
	 */
	@Override
	public List<Article> getAllArticlesFromAuthor(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
