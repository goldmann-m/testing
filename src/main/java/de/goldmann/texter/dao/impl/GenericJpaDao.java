package de.goldmann.texter.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import de.goldmann.texter.dao.GenericDao;


/**
 * The Class GenericJpaDao.
 * 
 * @param <T>
 *            the entity class
 * @param <PK>
 *            the class of the id field
 * @author ronded
 */
public class GenericJpaDao<T, PK extends Serializable> implements
		GenericDao<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	
	/**
	 * Entity manager
	 */
	@PersistenceContext(unitName = "myPersistenceUnit")
	private EntityManager entityManager;

	/** The entity manager factory. */
	@PersistenceUnit(unitName = "myPersistenceUnit")
	private EntityManagerFactory entityManagerFactory;

	/** The persistent class. */
	private final Class<T> persistentClass;

	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * Use this constructor when subclassing or using dependency injection.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericJpaDao(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * Gets the entity manager factory.
	 * 
	 * @return the entity manager factory
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * Use this constructor when subclassing or using dependency injection.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 * @param entityManager
	 *            the configured EntityManager for JPA implementation.
	 */
	public GenericJpaDao(final Class<T> persistentClass,
			EntityManager entityManager) {
		this.persistentClass = persistentClass;
		this.entityManager = entityManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(PK id) {
		T entity = this.entityManager.find(this.persistentClass, id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(PK id) {
		T entity = this.entityManager.find(this.persistentClass, id);

		if (entity == null) {
			String msg = "Uh oh, '" + this.persistentClass
					+ "' object with id '" + id + "' not found...";
			// TODO: Auf SLF4J Umbauen
			// this.log.warn(msg);
			throw new EntityNotFoundException(msg);
		}

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return this.entityManager.createQuery(
				"select obj from " + this.persistentClass.getName() + " obj")
				.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> getAllDistinct() {
		Collection<T> result = new LinkedHashSet<T>(this.getAll());
		return new ArrayList<T>(result);
	}

	/**
	 * Gets the log variable for all child classes.
	 * 
	 * @return the log variable for all child classes
	 */
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * Gets the persistent class.
	 * 
	 * @return the persistent class
	 */
	protected Class<T> getPersistentClass() {
		return this.persistentClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(PK id) {
		this.entityManager.remove(this.get(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T save(T object) {
		return this.entityManager.merge(object);
	}

	/**
	 * @see de.subreport.persistence.services.IGenericDao#clearCache()
	 */
	@Override
	public void clearCache() {
		getEntityManager().clear();
	}
}
