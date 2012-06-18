package de.goldmann.texter.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.goldmann.texter.common.exception.NoUserFoundException;
import de.goldmann.texter.dao.UserDao;
import de.goldmann.texter.model.User;
import de.goldmann.texter.model.User_;

/**
 * Implementation of {@link UserDao}
 */
@Stateless
public class UserDaoImpl extends GenericJpaDao<User, Long> implements UserDao{

	public UserDaoImpl() {
		super(User.class);
	}

	/*
	 * (non-Javadoc)
	 * @see de.goldmann.texter.dao.UserDao#findUserByUsername()
	 */
	@Override
	public User findUserByUsername(String userName) throws NoUserFoundException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		
		Root<User> user = criteriaQuery.from(User.class);
		
		criteriaQuery.select(user).where(criteriaBuilder.equal(user.get(User_.userName), userName)).distinct(true);
		
		try{
			return getEntityManager().createQuery(criteriaQuery).getSingleResult();
		}catch (NoResultException e) {
			throw new NoUserFoundException();
		}
	}

}
