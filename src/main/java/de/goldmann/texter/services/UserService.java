package de.goldmann.texter.services;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.goldmann.texter.model.User;
import de.goldmann.texter.model.User_;

/**
 * Service for user handling.
 * 
 * @author goldmannm
 *
 */
@RequestScoped
@Named
public class UserService {
	
	@PersistenceContext(unitName = "myPersistenceUnit")
	private EntityManager em;
	
	private CriteriaBuilder criteriaBuilder;
	
	/**
	 * init method
	 */
	@PostConstruct
	public void init(){
		criteriaBuilder = em.getCriteriaBuilder();
	}
	
	/**
	 * find user by username
	 * 
	 * @param userName
	 * @return {@link User}
	 * @throws NoResultException
	 */
	public User findUserByUsername(String userName) throws NoResultException{
		
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		
		Root<User> user = criteriaQuery.from(User.class);
		
		criteriaQuery.select(user).where(criteriaBuilder.equal(user.get(User_.userName), userName));
		
		try {
			return em.createQuery(criteriaQuery).getSingleResult();
		} catch (NoResultException e) {
			throw new NoResultException();
		}
	}
	
	public void saveUser(User user){
		em.merge(user);
	}

}
